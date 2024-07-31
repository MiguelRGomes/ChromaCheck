create table budget_product (
	id			    serial,
	budget_id       integer,
	product_id      integer,
	quantity        decimal(10, 2),
	unit_price      decimal(10, 2),
	total           decimal(10, 2),
	approval        boolean,
	CONSTRAINT budget_product_id_pk primary key (id),
	CONSTRAINT budget_id_in_budget_product_fk foreign key (budget_id) references budget (id),
	CONSTRAINT product_id_in_budget_product_fk foreign key (product_id) references product (id)
);