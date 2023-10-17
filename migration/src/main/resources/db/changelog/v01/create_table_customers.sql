
create table if not exists customers
(
    customer_id bigserial primary key,
    phone varchar(12) not null ,
    email varchar(255) not null,
    address varchar(255) not null
    );

comment on table customers is 'Заказчики';
