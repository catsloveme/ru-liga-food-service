create sequence if not exists courier_seq;

alter table couriers
    add constraint default_courier_id default nextval('couriers_seq') for courier_id;


