create table people (
	id			serial,
	company_id  integer,
	type		varchar(1),
	name		varchar(125),
	cpf_cnpj   	varchar(14),
	fone        varchar(15),
	email       varchar(50),
	CONSTRAINT people_id_pk primary key (id),
	CONSTRAINT company_id_in_people_fk foreign key (company_id) references company (id)
);