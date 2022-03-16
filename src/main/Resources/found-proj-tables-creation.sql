--drop them just in case we need to wipe it all and start over
--while building/setting up the tables
DROP TABLE IF exists ers_reimbursements;
DROP TABLE IF EXISTS ers_users;
DROP TABLE IF EXISTS ers_user_roles;
DROP TABLE IF EXISTS ers_reimbursement_statuses;
DROP TABLE IF EXISTS ers_reimbursement_types;

--start creating tables 
--ers_user_roles
create table ers_user_roles(
	role_id varchar,
	role 	varchar unique,
	
	constraint ers_user_roles_pk
	primary key (role_id)
);

--ers_users
create table ers_users(
	user_id 	varchar,
	username 	varchar unique not null,
	email 		varchar unique not null,
	password 	varchar not null,
	first_name 	varchar not null,
	last_name 	varchar not null,
	is_active 	boolean,
	role_id 	varchar,
	
	constraint ers_users_pk
	primary key (user_id),
	
	constraint ers_users_role_id_fk
	foreign key (role_id) 
	references  ers_user_roles (role_id)
	
);

--ers_reimbursement_types
create table ers_reimbursement_types (
	type_id varchar,
	type 	varchar unique,
	
	constraint reimbursement_types_pk
	primary key (type_id)
);

--ers_reimbursement_statuses
create table ers_reimbursement_statuses(
	status_id 	varchar,
	status		varchar unique,
	
	constraint reimbursement_statuses_pk
	primary key (status_id)
);


--ers-reimbursements
create table ers_reimbursements(
	reimb_id 	varchar, 
	amount 		numeric(6, 2) not null, 
	submitted 	timestamp not null,
	resolved 	timestamp,
	description varchar not null,
	--receipt 	blob, blob type doesn't exist in postgres
	payment_id 	varchar,
	author_id 	varchar not null,
	resolver_id varchar not null,
	status_id 	varchar not null,
	type_id 	varchar not null,
		
	constraint ers_reimbursements_pk
	primary key (reimb_id),
	
	constraint author_id_fk
	foreign key (author_id)
	references ers_users (user_id),
	
	constraint resolver_id_fk
	foreign key (resolver_id)
	references ers_users (user_id),
	
    constraint status_id_fk
    foreign key (status_id)
    references ers_reimbursement_statuses (status_id),
    
    constraint type_id_fk
    foreign key (type_id)
    references ers_reimbursement_types (type_id)
);


insert into "ers_user_roles"("role_id", "role")
				values ('0', 'Admin'),
				('1', 'Finance Manager'),
				('2', 'Employee');

insert into "ers_reimbursement_statuses"("status_id", "status")
				values ('0', 'pending'),
				('1', 'approved'),
				('2', 'denied');

insert into "ers_reimbursement_types"("type_id", "type")
				values ('0', 'Lodging'),
				('1', 'Travel'),
				('2', 'Food'),
				('3', 'Other');

select * from ers_users;

select * from ers_user_roles;

select * from ers_reimbursement_types;

select * from ers_reimbursement_statuses;

select * from ers_reimbursements;






