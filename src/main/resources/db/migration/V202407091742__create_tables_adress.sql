create table adress (
	id			serial,
	company_id  integer,
	people_id   integer,
	adress		varchar(125),
	number      integer,
	district    varchar(125),
	cep         integer,
	city        varchar(50),
	uf          varchar(2),
	CONSTRAINT adress_id_pk primary key (id),
	CONSTRAINT company_id_in_adress_fk foreign key (company_id) references company (id),
	CONSTRAINT people_id_in_adress_fk foreign key (people_id) references people (id),
	constraint region_id_in_adress_fk foreign key (uf) references country_states (state_code)
);