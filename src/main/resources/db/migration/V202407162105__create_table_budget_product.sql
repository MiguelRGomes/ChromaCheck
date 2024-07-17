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