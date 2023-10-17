create sequence if not exists restaurant_menu_item_seq;

alter table restaurant_menu_items
    add constraint default_restaurant_menu_item_id default nextval('restaurant_menu_item_seq') for restaurant_menu_item_id;


