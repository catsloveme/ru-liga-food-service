alter table orders
 add constraint fk_orders_couriers foreign key (courier_id)
 references couriers(courier_id),
 add constraint fk_orders_restaurant foreign key (restaurant_id)
 references restaurants(restaurant_id),
 add constraint fk_orders_customer foreign key (customer_id)
 references customers(customer_id);
