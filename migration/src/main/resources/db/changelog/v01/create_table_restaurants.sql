create sequence if not exists restaurant_seq;

create table if not exists restaurants
(
    restaurant_id bigint primary key default nextval('restaurant_seq'),
    address varchar (255) not null,
    name varchar (255) not null,
    status varchar(255) not null
    );

comment on table restaurants is 'Рестораны';