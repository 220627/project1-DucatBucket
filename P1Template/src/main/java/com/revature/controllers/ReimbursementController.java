package com.revature.controllers;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.revature.daos.ReimbursementDAO;
import com.revature.models.Reimbursement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.javalin.http.Handler;
import java.sql.Timestamp;
import java.time.Instant;

public class ReimbursementController {
	ReimbursementDAO rDAO = new ReimbursementDAO();
	public static Logger log = LogManager.getLogger();
	
	public Handler insertReimbursement = (ctx) -> {
		String body = ctx.body();
		
		Gson gson = new Gson();
		Reimbursement newReimb = gson.fromJson(body, Reimbursement.class);
		
		//Getting time submission was made
		Timestamp ts = Timestamp.from(Instant.now());
		newReimb.setReimb_submitted(ts);
		
		if(rDAO.insertReimbursement(newReimb)) {
			log.info("Successful reimbursement insertion");
			ctx.status(202);
		}
		else {
			log.warn("Unuccessful reimbursement insertion");
			ctx.status(406);
		}
	};
	
	//Handler for getting all reimbursements
	public Handler selectAll = (ctx) -> {
		ArrayList<Reimbursement> reimbursements = rDAO.selectAll();
		Gson gson = new Gson();
		String JSONreimbursements = gson.toJson(reimbursements);
		ctx.result(JSONreimbursements);
		ctx.status(200);
		log.info("Successfully returned all reimbursements");
	};
	
	//Handler for getting all reimbursements from an author
	public Handler selectByAuthor = (ctx) -> {
		ArrayList<Reimbursement> reimbursements = rDAO.selectByAuthor(Integer.parseInt(ctx.pathParam("id")));
		Gson gson = new Gson();
		String JSONreimbursements = gson.toJson(reimbursements);
		ctx.result(JSONreimbursements);
		ctx.status(200);
		log.info("Successfully returned reimbursements of " + ctx.pathParam("id"));
	};
	
	//Handler for getting all reimbursements of a certain status i.e. pending/approved/denied
	public Handler selectByStatus = (ctx) -> {
		ArrayList<Reimbursement> reimbursements = rDAO.selectByStatus(Integer.parseInt(ctx.pathParam("status_id")));
		Gson gson = new Gson();
		String JSONreimbursements = gson.toJson(reimbursements);
		ctx.result(JSONreimbursements);
		ctx.status(200);
		log.info("Successfully returned reimbursements of status " + ctx.pathParam("status_id"));
	};
	
	//Handler for changing status of a reimbursement
	public Handler updateStatus = (ctx) -> {
		int reimb_id = Integer.parseInt(ctx.pathParam("id"));
		String body = ctx.body();
		
		Gson gson = new Gson();
		Reimbursement newReimb = gson.fromJson(body, Reimbursement.class);
		newReimb.setReimb_id(reimb_id);
		
		if(rDAO.updateStatus(newReimb)) {
			log.info("Successful status update");
			ctx.status(202);
		} else {
			log.warn("Unuccessful status update");
			ctx.status(406);
		}
		
	};
}
