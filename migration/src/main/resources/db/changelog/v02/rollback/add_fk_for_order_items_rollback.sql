 alter table order_items
  drop foreign key fk_order_items_order,
  drop foreign key fk_order_items_restaurant_menu_item;
