package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.User;
import com.revature.models.UserRoles;
import com.revature.utils.ConnectionUtil;

public class UserDAO {
	
	public User login(String username, String password) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "select * from ers_users"
					+ " left join ers_user_roles"
					+ " on ers_users.user_role_id_fk = ers_user_roles.ers_user_role_id"
					+ " where ers_username = ? and ers_password = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				//Initializing returned user data
				User u = new User(
						rs.getInt("ers_users_id"),
						rs.getString("ers_username"), null, null, null, null
						);
				
				u.setUser_role(new UserRoles(rs.getInt("ers_user_role_id"), rs.getString("user_role")));
				return u;
			}
			
		} catch(SQLException e) {
			System.out.println("LOGIN FAILED");
			e.printStackTrace();
		}
		return null;
	}
	
}
