DROP TABLE IF EXISTS accounttype CASCADE;
DROP TABLE IF EXISTS accountstatus CASCADE;
DROP TABLE IF EXISTS accounts CASCADE;
DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE accounttype (
	typeid int primary key,
	type varchar(60) not null unique
);

-- Insert seed data into the table
INSERT INTO accounttype( typeid, type ) values
	( 1, 'Checking' ),
	( 2, 'Savings' );

CREATE TABLE accountstatus (
	statusid int primary key,
	status varchar(60) not null unique
);

-- Insert seed data into the table
INSERT INTO accountstatus( statusid, status ) values
	( 1, 'Pending' ),
	( 2, 'Open' ),
	( 3, 'Closed' ),
	( 4, 'Denied' );

CREATE TABLE accounts (
	accountid int primary key,
	balance numeric(10, 2) not null,
	status int references accountstatus(statusid),
	type int references accounttype(typeid)
);
	
CREATE TABLE roles (
	roleid int primary key,
	rolename varchar(60) not null unique
);

-- Insert seed data into the table
INSERT INTO roles( roleid, rolename ) values
	( 1, 'Admin' ),
	( 2, 'Employee' ),
	( 3, 'Standard' ),
	( 4, 'Premium' );

CREATE TABLE users (
	userid serial primary key,
	username varchar(60) NOT null unique,
	password varchar(60) NOT null,
	firstname varchar(60) NOT null,
	lastname varchar(60) NOT null,
	email varchar(60) NOT null,
	roleid int references roles(roleid)
);

-- Insert test data
-- REMOVE later
-- Insert seed data into the roles table
INSERT INTO users( username, password, firstname, lastname, email, roleid ) values
	( 'cpenza', 'XYZ', 'Christine', 'Penza', 'christinepenza@revature.com', 3 ),
	( 'tbrown', '12345', 'Tony', 'Brown', 'tbrown@revature.com', 1 ),
	( 'pdiddy', 'puff', 'Patrick', 'Diddy', 'pdado@revature.com', 4 ),
	( 'spage', 'ABC', 'Steven', 'Page', 'spage@revature.com', 2 )
;
