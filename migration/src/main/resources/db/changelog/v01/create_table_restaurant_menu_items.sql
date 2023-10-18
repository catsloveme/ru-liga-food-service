create sequence if not exists restaurant_menu_item_seq;

create table if not exists restaurant_menu_items
(
    restaurant_menu_item_id bigint primary key default nextval('restaurant_menu_item_seq') ,
    restaurant_id bigint,
    name varchar(255) unique not null,
    price money not null,
    image varchar(255) not null,
    description varchar(255) not null
    );

comment on table restaurant_menu_items is 'Блюда из меню';
