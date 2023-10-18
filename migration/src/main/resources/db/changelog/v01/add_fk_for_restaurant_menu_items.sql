alter table restaurant_menu_items
  add constraint fk_restaurant_menu_items_restaurant
  foreign key (restaurant_id) references restaurants(restaurant_id);