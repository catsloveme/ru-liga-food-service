create sequence if not exists order_seq;

create table if not exists orders
(   order_id bigint primary key default nextval('order_seq'),
    status varchar(255) not null,
    courier_id bigint,
    restaurant_id bigint,
    customer_id bigint,
    timestamp timestamptz not null default now()
    );

comment on table orders is 'Заказы';
