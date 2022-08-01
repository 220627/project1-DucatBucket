CREATE TABLE ers_reimbursement_status(
	reimb_status_id serial PRIMARY KEY,
	reimb_status varchar(10)
);

CREATE TABLE ers_reimbursement_type(
	reimb_type_id serial PRIMARY KEY,
	reimb_type varchar(10)
);

CREATE TABLE ers_user_roles(
	ers_user_role_id serial PRIMARY KEY,
	user_role varchar(10)
);

CREATE TABLE ers_users(
	ers_users_id serial PRIMARY KEY,
	ers_username varchar(50) unique,
	ers_password varchar(50),
	user_first_name varchar(100),
	user_last_name varchar(100),
	user_email varchar(150) unique,
	user_role_id_fk int REFERENCES ers_user_roles(ers_user_role_id)
);

CREATE TABLE ers_reimbursement (
	reimb_id serial PRIMARY KEY,
	reimb_amount int,
	reimb_submitted timestamp,
	reimb_resolved timestamp,
	reimb_description varchar(250),
	reimb_author_fk int REFERENCES ers_users(ers_users_id),
	reimb_resolver_fk int REFERENCES ers_users(ers_users_id),
	reimb_status_id_fk int REFERENCES ers_reimbursement_status(reimb_status_id),
	reimb_type_id_fk int REFERENCES ers_reimbursement_type(reimb_type_id)
);
