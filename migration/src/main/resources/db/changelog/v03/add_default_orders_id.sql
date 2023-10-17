create sequence if not exists order_seq;

alter table orders
    add constraint default_order_id default nextval('order_seq') for order_id;


