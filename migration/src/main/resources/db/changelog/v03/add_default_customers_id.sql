create sequence if not exists customer_seq;

alter table customers
    add constraint default_customer_id default nextval('customer_seq') for customer_id;


