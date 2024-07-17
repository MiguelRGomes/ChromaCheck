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