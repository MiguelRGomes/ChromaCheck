CREATE TABLE services (
    id serial,
    company_id  integer,
    name        varchar(125),
    CONSTRAINT services_id_pk PRIMARY KEY (id),
    CONSTRAINT company_id_in_services_fk foreign key (company_id) references company (id)
);