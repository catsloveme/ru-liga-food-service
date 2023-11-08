create
extension if not exists "uuid-ossp";
create table if not exists orders
(
    order_id uuid primary key default uuid_generate_v4
(
),
    status varchar
(
    255
) not null,
    courier_id uuid,
    restaurant_id uuid,
    customer_id uuid,
    timestamp timestamptz not null default now
(
)
    );

comment
on table orders is 'Заказы';
