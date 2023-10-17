alter table customers
    add constraint unique_phone_customers unique (phone),
    add constraint unique_email_customers unique (email);

