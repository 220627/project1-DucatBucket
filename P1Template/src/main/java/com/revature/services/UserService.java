package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.models.User;

public class UserService {
	UserDAO uDAO = new UserDAO();
	public User login(String username, String password) {
		
		if(uDAO.login(username, password) != null) {
			return uDAO.login(username, password);
		}
		
		return null;
	}
}
