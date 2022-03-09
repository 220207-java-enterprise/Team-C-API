-- do not RUN THIS WHOLE FILE as ONE THING without first RUNNING found-proj-tables-creation.sql

insert into "ers_user_roles"("role_id", "role")
				values ('0', 'Admin'),
				('1', 'Finance Manager'),
				('2', 'Employee');

select * from "ers_user_roles";

insert into "ers_users"("user_id", "username", "email", "password", "first_name", "last_name", "is_active", "role_id")
				values (gen_random_uuid(), 'iamtheadmin', 'admin@gmail.com', 'password', 'Bob', 'Samolian', true, '0');

insert into "ers_users"("user_id", "username", "email", "password", "first_name", "last_name", "is_active", "role_id")
				values (gen_random_uuid(), 'finance_manager_1', 'financier@gmail.com', 'fin_password', 'Bill', 'Anderson', true, '1'),
					   (gen_random_uuid(), 'employee_1', 'an_employee@gmail.com', 'emp_password', 'Phil', 'Jones', true, '2'),
					   (gen_random_uuid(), 'new_employee', 'new_employee@gmail.com', 'new_password', 'Jim', 'Stark', false, '2'),
					   (gen_random_uuid(), 'old_employee', 'old@gmail.com', 'old_password', 'Old', 'Guy', true, '2'),
					   (gen_random_uuid(), 'new_finance_guy', 'new_fin_guy@gmail.com', 'new_fin_guy_pass', 'New', 'Finance', false, '1');

select * from "ers_users";

insert into "ers_reimbursment_types"("type_id", "type")
				values ('0', 'Lodging'),
				('1', 'Travel'),
				('2', 'Food'),
				('3', 'Other');

select * from "ers_reimbursment_types";

insert into "ers_reimbursment_statuses"("status_id", "status")
				values ('0', 'pending'),
				('1', 'approved'),
				('2', 'denied');

select * from "ers_reimbursment_statuses";

insert into "ers_reimbursments"("reimb_id", "amount", "submitted", "resolved",
"description", "payment_id", "author_id", "resolver_id", "status_id", "type_id")
				values (gen_random_uuid(), 1000, CURRENT_TIMESTAMP, null, 'this was for the conference last month', null,
				(select "user_id" from "ers_users" where "username" = 'old_employee'),
				(select "user_id" from "ers_users" where "username" = 'finance_manager_1'),
				(select "status_id" from "ers_reimbursment_statuses"  where "status_id" = '0'),
				(select "type_id" from "ers_reimbursment_types"  where "type_id" = '0'));

insert into "ers_reimbursments"("reimb_id", "amount", "submitted", "resolved",
"description", "payment_id", "author_id", "resolver_id", "status_id", "type_id")
				values (gen_random_uuid(), 10, CURRENT_TIMESTAMP, null, 'this was for the new training program', null,
				(select "user_id" from "ers_users" where "username" = 'old_employee'),
				(select "user_id" from "ers_users" where "username" = 'finance_manager_1'),
				(select "status_id" from "ers_reimbursment_statuses"  where "status_id" = '1'),
				(select "type_id" from "ers_reimbursment_types"  where "type_id" = '3'));

insert into "ers_reimbursments"("reimb_id", "amount", "submitted", "resolved",
"description", "payment_id", "author_id", "resolver_id", "status_id", "type_id")
				values (gen_random_uuid(), 9999, CURRENT_TIMESTAMP, null, 'this was for the paid vacation I won at the annual raffle', null,
				(select "user_id" from "ers_users" where "username" = 'old_employee'),
				(select "user_id" from "ers_users" where "username" = 'finance_manager_1'),
				(select "status_id" from "ers_reimbursment_statuses"  where "status_id" = '1'),
				(select "type_id" from "ers_reimbursment_types"  where "type_id" = '3'));

select * from "ers_reimbursments";

