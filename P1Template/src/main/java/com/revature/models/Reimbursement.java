package com.revature.models;

import java.sql.Timestamp;

public class Reimbursement {
	private int reimb_id;
	private float reimb_amount;
	private Timestamp reimb_submitted;
	private Timestamp reimb_resolved;
	private String reimb_description;
	private Integer reimb_author_fk;
	private Integer reimb_resolver_fk;
	private ReimbStatus reimb_status;
	private Integer reimb_status_id_fk;
	private ReimbType reimb_type;
	private Integer reimb_type_id_fk;
	
	public Reimbursement() {
		super();
		
	}
	
	public Reimbursement(float reimb_amount, String reimb_description, int reimb_author_fk, 
			int reimb_resolver_fk, int reimb_status_id_fk, int reimb_type_id_fk) {
		super();
		this.reimb_amount = reimb_amount;
		this.reimb_author_fk = reimb_author_fk;
		this.reimb_resolver_fk = reimb_resolver_fk;
		this.reimb_status_id_fk = reimb_status_id_fk;
		this.reimb_type_id_fk = reimb_type_id_fk;
	}



	public Reimbursement(int reimb_id, float reimb_amount, Timestamp reimb_submitted, Timestamp reimb_resolved,
			String reimb_description, int reimb_author_fk, int reimb_resolver_fk, ReimbStatus reimb_status,
			ReimbType reimb_type) {
		super();
		this.reimb_id = reimb_id;
		this.reimb_amount = reimb_amount;
		this.reimb_submitted = reimb_submitted;
		this.reimb_resolved = reimb_resolved;
		this.reimb_description = reimb_description;
		this.reimb_author_fk = reimb_author_fk;
		this.reimb_resolver_fk = reimb_resolver_fk;
		this.reimb_status = reimb_status;
		this.reimb_type = reimb_type;
	}


	public int getReimb_id() {
		return reimb_id;
	}
	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}
	public float getReimb_amount() {
		return reimb_amount;
	}
	public void setReimb_amount(float reimb_amount) {
		this.reimb_amount = reimb_amount;
	}
	public Timestamp getReimb_submitted() {
		return reimb_submitted;
	}
	public void setReimb_submitted(Timestamp reimb_submitted) {
		this.reimb_submitted = reimb_submitted;
	}
	public Timestamp getReimb_resolved() {
		return reimb_resolved;
	}
	public void setReimb_resolved(Timestamp reimb_resolved) {
		this.reimb_resolved = reimb_resolved;
	}
	public String getReimb_description() {
		return reimb_description;
	}
	public void setReimb_description(String reimb_description) {
		this.reimb_description = reimb_description;
	}
	public Integer getReimb_author_fk() {
		return reimb_author_fk;
	}
	public void setReimb_author_fk(Integer reimb_author_fk) {
		this.reimb_author_fk = reimb_author_fk;
	}
	public Integer getReimb_resolver_fk() {
		return reimb_resolver_fk;
	}
	public void setReimb_resolver_fk(Integer reimb_resolver_fk) {
		this.reimb_resolver_fk = reimb_resolver_fk;
	}
	public ReimbStatus getReimb_status() {
		return reimb_status;
	}
	public void setReimb_status(ReimbStatus reimb_status) {
		this.reimb_status = reimb_status;
	}
	public ReimbType getReimb_type() {
		return reimb_type;
	}
	public void setReimb_type(ReimbType reimb_type) {
		this.reimb_type = reimb_type;
	}

	public Integer getReimb_status_id_fk() {
		return reimb_status_id_fk;
	}

	public void setReimb_status_id_fk(Integer reimb_status_id_fk) {
		this.reimb_status_id_fk = reimb_status_id_fk;
	}

	public Integer getReimb_type_id_fk() {
		return reimb_type_id_fk;
	}

	public void setReimb_type_id_fk(Integer reimb_type_id_fk) {
		this.reimb_type_id_fk = reimb_type_id_fk;
	}
	
	
}
