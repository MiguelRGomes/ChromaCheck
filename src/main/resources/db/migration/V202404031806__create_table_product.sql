create table product (
	id			serial,
	name		varchar(125),
	description     	varchar(125),
	constraint product_id_pk primary key (id)
);