create
extension if not exists "uuid-ossp";
create table if not exists restaurant_menu_items
(
    restaurant_menu_item_id uuid primary key default uuid_generate_v4
(
),
    restaurant_id uuid,
    name varchar
(
    255
) not null,
    price numeric not null,
    image varchar
(
    255
) not null,
    description varchar
(
    255
) not null
    );

comment on table restaurant_menu_items is 'Блюда из меню';
