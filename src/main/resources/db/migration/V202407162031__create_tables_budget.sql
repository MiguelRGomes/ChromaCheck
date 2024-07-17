create table budget (
	id			    serial,
	people_id       integer,
	adress_id       integer,
	company_id      integer,
	creation_date   date,
	expiration_date date,
	approval        boolean,
	total           decimal(10, 2),
	CONSTRAINT budget_id_pk primary key (id),
	CONSTRAINT people_id_in_budget_fk foreign key (people_id) references people (id),
	CONSTRAINT adress_id_in_budget_fk foreign key (adress_id) references adress (id),
	CONSTRAINT company_id_in_budget_fk foreign key (company_id) references company (id)
);