CREATE TABLE prices (
    id serial,
    company_id integer,
    name       varchar(125),
    square_meter decimal(10, 2),
    fixed_price decimal(10, 2),
    CONSTRAINT prices_id_pk PRIMARY KEY (id),
    CONSTRAINT company_id_in_prices_fk foreign key (company_id) references company (id)
);