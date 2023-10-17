create table if not exists restaurants
(
    restaurant_id bigserial primary key,
    address varchar (255) not null,
    status varchar(255) not null
    );

comment on table restaurants is 'Рестораны';