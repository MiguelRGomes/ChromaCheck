create table company (
    id          serial,
	cnpj        varchar(14),
	name		varchar(125),
	address     varchar(125),
	number      integer,
	district    varchar(125),
	cep         varchar(10),
	city        varchar(125),
	uf          varchar(2),
	fone        varchar(15),
	email       varchar(125),
	constraint company_id_pk primary key (id),
	constraint region_id_in_freight_route_fk foreign key (uf) references country_states (state_code)
);
