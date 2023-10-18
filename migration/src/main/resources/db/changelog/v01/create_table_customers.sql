create sequence if not exists customer_seq;

create table if not exists customers
(
    customer_id bigint primary key default nextval('customer_seq'),
    phone varchar(12) not null unique,
    email varchar(255) not null unique,
    address varchar(255) not null
    );

comment on table customers is 'Заказчики';
