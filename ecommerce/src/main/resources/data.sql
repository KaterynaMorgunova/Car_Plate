-- H2 embedded SQL schema

--delete from Cart_Article;
--delete from Cart_Order;
--delete from Customer_Delivery;
--delete from Customer;
--delete from Article;

insert into Customer (created_at, name, password, email)
                values (CURRENT_TIMESTAMP, 'admin', 'admin', 'kateamorh@gmail.com');

insert into Article (id, created_at, name, description, image_url, price, currency, available_quantity)
                values (1, CURRENT_TIMESTAMP, 'Red Bull', 'T-Short XL', '/images/rb.png', 1.0, 'USD', 1);
insert into Article (id, created_at, name, description, image_url, price, currency, available_quantity)
                values (2, CURRENT_TIMESTAMP, 'Mercedes Petronas', 'T-Short L', '/images/mp.png', 2.0, 'USD', 2);
insert into Article (id, created_at, name, description, image_url, price, currency, available_quantity)
                values (3, CURRENT_TIMESTAMP, 'Alpine', 'T-Short M', '/images/a.png', 3.0, 'USD', 3);
insert into Article (id, created_at, name, description, image_url, price, currency, available_quantity)
                values (4, CURRENT_TIMESTAMP, 'Ferrary', 'T-Short XL', '/images/f.png', 4.0, 'USD', 4);
insert into Article (id, created_at, name, description, image_url, price, currency, available_quantity)
                values (5, CURRENT_TIMESTAMP, 'Aston Martin', 'T-Short XXL', '/images/am.png', 5.0, 'USD', 5);
insert into Article (id, created_at, name, description, image_url, price, currency, available_quantity)
                values (6, CURRENT_TIMESTAMP, 'McLaren', 'T-Short L', '/images/ml.png', 6.0, 'USD', 6);
insert into Article (id, created_at, name, description, image_url, price, currency, available_quantity)
                values (7, CURRENT_TIMESTAMP, 'Haas', 'T-Short S', '/images/h.png', 7.0, 'USD', 7);
insert into Article (id, created_at, name, description, image_url, price, currency, available_quantity)
                values (8, CURRENT_TIMESTAMP, 'Alpha Taury', 'T-Short M', '/images/at.png', 8.0, 'USD', 8);
insert into Article (id, created_at, name, description, image_url, price, currency, available_quantity)
                values (9, CURRENT_TIMESTAMP, 'Alpha Romeo', 'T-Short M', '/images/ar.png', 9.0, 'USD', 9);
insert into Article (id, created_at, name, description, image_url, price, currency, available_quantity)
                values (10, CURRENT_TIMESTAMP, 'Williams', 'T-Short XS', '/images/w.png', 10.0, 'USD', 10);

