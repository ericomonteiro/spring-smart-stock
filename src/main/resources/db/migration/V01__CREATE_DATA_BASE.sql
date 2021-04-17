create table product
(
    id bigserial not null
        constraint product_pkey
            primary key,
    details text not null,
    name varchar(255) not null,
    price real not null,
    stock bigint not null
);

create table stock_history
(
    id bigserial not null
        constraint stock_history_pkey
            primary key,
    date date not null,
    quantity bigint not null,
    type varchar(255) not null,
    product_id bigint not null
        constraint product_fk
            references product
);

