create
extension if not exists "uuid-ossp";
create table if not exists restaurants
(
    restaurant_id uuid primary key default uuid_generate_v4
(
),
    address varchar
(
    255
) not null,
    name varchar
(
    255
) not null,
    status varchar
(
    255
) not null
    );

comment
on table restaurants is 'Рестораны';
