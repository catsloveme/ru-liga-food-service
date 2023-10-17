create table if not exists orders
(   order_id bigserial primary key,
    status varchar(255) not null,
    courier_id bigint,
    restaurant_id bigint,
    customer_id bigint,
    timestamp timestamptz not null default now()
    );

comment on table orders is 'Заказы';
