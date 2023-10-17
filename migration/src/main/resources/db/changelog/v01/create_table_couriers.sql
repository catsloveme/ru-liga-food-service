create table if not exists couriers
(
    courier_id bigserial primary key,
    phone varchar(12) not null ,
    status varchar(255) not null,
    coordinates json not null
    );

comment on table couriers is 'Курьеры';
