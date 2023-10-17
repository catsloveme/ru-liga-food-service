create table if not exists order_items
(
    order_item_id bigserial primary key,
    order_id bigint,
    restaurant_menu_item_id bigint,
    price money not null,
    quantity int not null
    );

comment on table order_items is 'Заказанные блюда';

