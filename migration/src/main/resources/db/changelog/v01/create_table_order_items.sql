create
extension if not exists "uuid-ossp";
create table if not exists order_items
(
    order_item_id uuid primary key default uuid_generate_v4
(
),
    order_id uuid,
    restaurant_menu_item_id uuid,
    price numeric not null,
    quantity int not null
    );

comment
on table order_items is 'Заказанные блюда';

