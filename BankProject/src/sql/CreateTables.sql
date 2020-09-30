DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS roles CASCADE;


CREATE TABLE roles (
	roleId int primary key,
	roleName varchar(60) not null unique
);

-- Insert seed data into the roles table
insert into roles( roleId, roleName ) values
	( 1, 'Admin' ),
	( 2, 'Employee' ),
	( 3, 'Standard' ),
	( 4, 'Premium' );

CREATE TABLE users (
	userId serial primary key,
	username varchar(60) NOT null unique,
	password varchar(60) NOT null,
	firstName varchar(60) NOT null,
	lastName varchar(60) NOT null,
	email varchar(60) NOT null,
	roleId int references roles(roleId)
);

-- Insert test data
-- REMOVE later
-- Insert seed data into the roles table
insert into users( username, password, firstname, lastName, email, roleId ) values
	( 'cpenza', 'XYZ', 'Christine', 'Penza', 'christinepenza@revature.com', 3 ),
	( 'spage', 'ABC', 'Steven', 'Page', 'spage@revature.com', 2 )
;
