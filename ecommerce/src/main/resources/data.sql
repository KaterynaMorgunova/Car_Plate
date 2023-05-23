delete from Cart_Article;
delete from Cart_Order;
delete from Customer_Delivery;
delete from Customer;
delete from Article;

insert into Customer (created_at, name, password, email)
                values (CURRENT_TIMESTAMP, 'admin', 'admin', 'kateamorh@gmail.com');

insert into Article (id, created_at, name, description, image_url, price, currency, available_quantity)
                values (1, CURRENT_TIMESTAMP, 'Article 1', 'Desc 1', 'images/image1.png', 1.0, 'USD', 1);
insert into Article (id, created_at, name, description, image_url, price, currency, available_quantity)
                values (2, CURRENT_TIMESTAMP, 'Article 2', 'Desc 2', 'images/image2.png', 2.0, 'USD', 2);
insert into Article (id, created_at, name, description, image_url, price, currency, available_quantity)
                values (3, CURRENT_TIMESTAMP, 'Article 3', 'Desc 3', 'images/image3.png', 3.0, 'USD', 3);
insert into Article (id, created_at, name, description, image_url, price, currency, available_quantity)
                values (4, CURRENT_TIMESTAMP, 'Article 4', 'Desc 4', 'images/image4.png', 4.0, 'USD', 4);
insert into Article (id, created_at, name, description, image_url, price, currency, available_quantity)
                values (5, CURRENT_TIMESTAMP, 'Article 5', 'Desc 5', 'images/image5.png', 5.0, 'USD', 5);

