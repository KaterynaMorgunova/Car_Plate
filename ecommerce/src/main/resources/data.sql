delete from Cart_Article;
delete from Card_Order;
delete from Customer_Delivery;
delete from Customer;
delete from Article;

insert into Article (id, name, type)
                values (1L, 'Article 1', 'Desc 1', 'images/image1.png', 1.0F, 'USD', 1);
insert into Article (id, name, type)
                values (2L, 'Article 2', 'Desc 2', 'images/image2.png', 2.0F, 'USD', 1);
insert into Article (id, name, type)
                values (3L, 'Article 3', 'Desc 3', 'images/image3.png', 3.0F, 'USD', 1);
insert into Article (id, name, type)
                values (4L, 'Article 4', 'Desc 4', 'images/image4.png', 4.0F, 'USD', 1);
insert into Article (id, name, type)
                values (5L, 'Article 5', 'Desc 5', 'images/image5.png', 5.0F, 'USD', 1);
