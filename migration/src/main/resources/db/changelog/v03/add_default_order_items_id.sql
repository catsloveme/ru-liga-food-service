create sequence if not exists order_item_seq;

alter table order_items
    add constraint default_order_item_id default nextval('order_item_seq') for order_item_id;


