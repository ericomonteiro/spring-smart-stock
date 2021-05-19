create table photo
(
    id uuid not null
        constraint photo_pkey
            primary key,
    data oid,
    file_type varchar(255),
    main boolean,
    product_id bigint not null
        constraint photo_product_fk
            references product
);