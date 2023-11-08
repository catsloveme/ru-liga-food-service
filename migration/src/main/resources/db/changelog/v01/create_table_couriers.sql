create
extension if not exists "uuid-ossp";
create table if not exists couriers
(
    courier_id uuid primary key default uuid_generate_v4
(
),
    phone varchar
(
    12
) not null unique ,
    status varchar
(
    255
) not null,
    coordinates varchar
(
    255
) not null
    );

comment
on table couriers is 'Курьеры';
