create table people (
	id			serial,
	name		varchar(125),
	cpf     	varchar(14),
	age     	integer,
	constraint people_id_pk primary key (id)
);