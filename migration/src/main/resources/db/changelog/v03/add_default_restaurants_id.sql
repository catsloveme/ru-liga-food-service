create sequence if not exists restaurant_seq;

alter table restaurants
    add constraint default_restaurant_id default nextval('restaurant_seq') for restaurant_id;


