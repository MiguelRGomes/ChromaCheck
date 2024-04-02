CREATE TABLE prices (
    id serial,
    name varchar(125),
    square_meter decimal(10, 2),
    fixed_price decimal(10, 2),
    CONSTRAINT prices_id_pk PRIMARY KEY (id)
);
