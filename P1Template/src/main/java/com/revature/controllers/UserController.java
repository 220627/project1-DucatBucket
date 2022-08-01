package com.revature.controllers;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.revature.models.LoginDTO;
import com.revature.models.User;
import com.revature.services.UserService;

import io.javalin.http.Handler;

public class UserController {
	public static Logger log = LogManager.getLogger();
	UserService us = new UserService();
	public static HttpSession ses;
	
	public Handler loginHandler = (ctx) -> {
		
		String body = ctx.body();
		
		Gson gson = new Gson();
		
		LoginDTO lDTO = gson.fromJson(body, LoginDTO.class);
		
		User user = us.login(lDTO.getUsername(), lDTO.getPassword()); 
		
		if(user != null) { 
			
			ses = ctx.req.getSession();
			
			String userJSON = gson.toJson(user); 
			
			log.info("Log in by: " + lDTO.getUsername());
			System.out.println("Loggin Successful");
			ctx.result(userJSON);			ctx.status(202);
			
		} else {
			log.warn("Loggin Unsuccessful");
			System.out.println("Loggin unsuccessful");
			
			ctx.status(401);
		} 
	};
}
