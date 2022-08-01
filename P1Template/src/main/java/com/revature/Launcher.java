package com.revature;

import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UserController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.javalin.Javalin;

public class Launcher {
	public static Logger log = LogManager.getLogger();

	public static void main(String[] args) {
			
		Javalin app = Javalin.create(
				config -> {
					config.enableCorsForAllOrigins();
				}).start(3000);
		
		log.info("Started App");
		
		ReimbursementController rc = new ReimbursementController();
		UserController uc = new UserController();
		app.get("/reimbursements", rc.selectAll);
		
		app.post("/login", uc.loginHandler);
		
		app.post("/insert", rc.insertReimbursement);
		
		app.get("/reimbursements/:id", rc.selectByAuthor);
		
		app.get("/status/:status_id", rc.selectByStatus);
		
		app.put("/status/:id", rc.updateStatus);
		
	}
	
}
