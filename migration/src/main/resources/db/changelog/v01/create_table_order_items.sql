create sequence if not exists order_item_seq;

create table if not exists order_items
(
    order_item_id bigint primary key default nextval('order_item_seq'),
    order_id bigint,
    restaurant_menu_item_id bigint,
    price numeric not null,
    quantity int not null
    );

comment on table order_items is 'Заказанные блюда';

