create table product (
	id			serial,
	company_id integer,
	name		varchar(125),
	description     	varchar(125),
	CONSTRAINT product_id_pk primary key (id),
	CONSTRAINT company_id_in_product_fk foreign key (company_id) references company (id)
);