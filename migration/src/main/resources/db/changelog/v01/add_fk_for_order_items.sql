alter table order_items
  add constraint fk_order_items_order foreign key (order_id)
  references orders(order_id),
  add constraint fk_order_items_restaurant_menu_item
  foreign key (restaurant_menu_item_id)
  references restaurant_menu_items(restaurant_menu_item_id);