create
extension if not exists "uuid-ossp";
create table if not exists customers
(
    customer_id uuid primary key default uuid_generate_v4
(
),
    phone varchar
(
    12
) not null unique,
    email varchar
(
    255
) not null unique,
    address varchar
(
    255
) not null
    );

comment
on table customers is 'Заказчики';
