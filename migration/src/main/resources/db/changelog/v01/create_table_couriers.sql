create sequence if not exists courier_seq;

create table if not exists couriers
(
    courier_id bigint primary key default nextval('courier_seq'),
    phone varchar(12) not null unique ,
    status varchar(255) not null,
    coordinates varchar(255) not null
    );

comment on table couriers is 'Курьеры';
