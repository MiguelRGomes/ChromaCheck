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

create table budget_product (
	id			    serial,
	budget_id       integer,
	product_id      integer,
	quantity        decimal(10, 2),
	creation_date   date,
	expiration_date date,
	approval        boolean,
	total           decimal(10, 2),
	CONSTRAINT budget_product_id_pk primary key (id),
	CONSTRAINT budget_id_in_budget_product_fk foreign key (budget_id) references budget (id),
	CONSTRAINT product_id_in_budget_product_fk foreign key (product_id) references product (id)
);

create table budget_service (
	id			    serial,
	budget_id       integer,
	service_id      integer,
	prices_id       integer,
	quantity        decimal(10, 2),
	discount        decimal(10, 2),
	CONSTRAINT budget_service_id_pk primary key (id),
	CONSTRAINT budget_id_in_budget_service_fk foreign key (budget_id) references budget (id),
	CONSTRAINT service_id_in_budget_service_fk foreign key (service_id) references services (id),
	CONSTRAINT prices_id_in_budget_service_fk foreign key (prices_id) references prices (id)
);