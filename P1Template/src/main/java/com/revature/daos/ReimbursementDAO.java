package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.models.ReimbStatus;
import com.revature.models.ReimbType;
import com.revature.models.Reimbursement;
import com.revature.utils.ConnectionUtil;

public class ReimbursementDAO {
	
	public boolean insertReimbursement(Reimbursement reimbursement) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "insert into ers_reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_author_fk, reimb_resolver_fk, reimb_status_id_fk, reimb_type_id_fk) values ( ?, ?, ?, ?, null, ?, ?);";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setFloat(1, reimbursement.getReimb_amount());
			ps.setTimestamp(2, reimbursement.getReimb_submitted());
			ps.setTimestamp(3, reimbursement.getReimb_resolved());
			ps.setInt(4, reimbursement.getReimb_author_fk());
			ps.setInt(5, reimbursement.getReimb_status_id_fk());
			ps.setInt(6, reimbursement.getReimb_type_id_fk());
			
			ps.executeUpdate();
			return true;
		}
		catch (SQLException e) { 
			System.out.println("Insert Reimbursement Failed"); 
			e.printStackTrace(); 
		}
		
		return false;
	}
	
	public ArrayList<Reimbursement> selectAll() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "select * from ers_reimbursement"
					+ " left join ers_reimbursement_status"
					+ " on ers_reimbursement.reimb_status_id_fk = ers_reimbursement_status.reimb_status_id"
					+ " left join ers_reimbursement_type"
					+ " on ers_reimbursement.reimb_type_id_fk = ers_reimbursement_type.reimb_type_id;";
			
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			ArrayList<Reimbursement> reimbList = new ArrayList<>();
			while(rs.next()) {
				Reimbursement r = new Reimbursement(
							rs.getInt("reimb_id"),
							rs.getFloat("reimb_amount"),
							rs.getTimestamp("reimb_submitted"),
							rs.getTimestamp("reimb_resolved"),
							rs.getString("reimb_description"),
							rs.getInt("reimb_author_fk"),
							rs.getInt("reimb_resolver_fk"),
							null,
							null
						);
				
				r.setReimb_status(new ReimbStatus(rs.getInt("reimb_status_id"), rs.getString("reimb_status")));
				r.setReimb_type(new ReimbType(rs.getInt("reimb_type_id"), rs.getString("reimb_type")));
				
				reimbList.add(r);
			}
			return reimbList;
			
		}catch (SQLException e) { 
			System.out.println("Select All Failed"); 
			e.printStackTrace(); 
		}
		
		return null;
	}
	
	public ArrayList<Reimbursement> selectByAuthor(int reimb_author) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "select * from ers_reimbursement"
					+ " left join ers_reimbursement_status"
					+ " on ers_reimbursement.reimb_status_id_fk = ers_reimbursement_status.reimb_status_id"
					+ " left join ers_reimbursement_type"
					+ " on ers_reimbursement.reimb_type_id_fk = ers_reimbursement_type.reimb_type_id"
					+ " where reimb_author_fk = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, reimb_author);
			ResultSet rs = ps.executeQuery();
			ArrayList<Reimbursement> reimbList = new ArrayList<>();
			while(rs.next()) {
				Reimbursement r = new Reimbursement(
							rs.getInt("reimb_id"),
							rs.getFloat("reimb_amount"),
							rs.getTimestamp("reimb_submitted"),
							rs.getTimestamp("reimb_resolved"),
							rs.getString("reimb_description"),
							rs.getInt("reimb_author_fk"),
							rs.getInt("reimb_resolver_fk"),
							null,
							null
						);
				
				r.setReimb_status(new ReimbStatus(rs.getInt("reimb_status_id"), rs.getString("reimb_status")));
				r.setReimb_type(new ReimbType(rs.getInt("reimb_type_id"), rs.getString("reimb_type")));
				
				reimbList.add(r);
			}
			return reimbList;
			
		}catch (SQLException e) { 
			System.out.println("Select By Author Failed"); 
			e.printStackTrace(); 
		}
		
		
		return null;
	}
	
	public ArrayList<Reimbursement> selectByStatus(int status) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "select * from ers_reimbursement"
					+ " left join ers_reimbursement_status"
					+ " on ers_reimbursement.reimb_status_id_fk = ers_reimbursement_status.reimb_status_id"
					+ " left join ers_reimbursement_type"
					+ " on ers_reimbursement.reimb_type_id_fk = ers_reimbursement_type.reimb_type_id"
					+ " where reimb_status_id_fk = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, status);
			ResultSet rs = ps.executeQuery();
			ArrayList<Reimbursement> reimbList = new ArrayList<>();
			while(rs.next()) {
				Reimbursement r = new Reimbursement(
							rs.getInt("reimb_id"),
							rs.getFloat("reimb_amount"),
							rs.getTimestamp("reimb_submitted"),
							rs.getTimestamp("reimb_resolved"),
							rs.getString("reimb_description"),
							rs.getInt("reimb_author_fk"),
							rs.getInt("reimb_resolver_fk"),
							null,
							null
						);
				
				r.setReimb_status(new ReimbStatus(rs.getInt("reimb_status_id"), rs.getString("reimb_status")));
				r.setReimb_type(new ReimbType(rs.getInt("reimb_type_id"), rs.getString("reimb_type")));
				
				reimbList.add(r);
			}
			return reimbList;
			
		}catch (SQLException e) { 
			System.out.println("Select By Status Failed"); 
			e.printStackTrace(); 
		}
		
		return null;
	}
	
	public boolean updateStatus(Reimbursement reimbursement) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "update ers_reimbursement set reimb_status_id_fk = ?, reimb_resolver_fk = ? where reimb_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, reimbursement.getReimb_status_id_fk());
			ps.setInt(2, reimbursement.getReimb_resolver_fk());
			ps.setInt(3, reimbursement.getReimb_id());
			ps.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			System.out.println("Failed Status Update");
			e.printStackTrace();
		}
		return false;
	}
}
