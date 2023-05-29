create table Article (
  id INTEGER IDENTITY(1,1) PRIMARY KEY,
  created_at datetime not null,
  name varchar(50) not null,
  description varchar(255) not null,
  image_url varchar(255) not null,
  price decimal(12,2) not null,
  currency varchar(3) not null,
  available_quantity int not null
);

create table Customer (
  name varchar(50) PRIMARY KEY,
  created_at datetime not null,
  phone varchar(255),
  email varchar(255),
  password varchar(50) not null
);

create table Customer_Delivery (
  id INTEGER IDENTITY(1,1) PRIMARY KEY,
  delivery_name varchar(50) not null,
  delivery_street varchar(50) not null,
  delivery_city varchar(50) not null,
  delivery_state varchar(2) not null,
  delivery_zip varchar(10) not null,
  cc_number varchar(16) not null,
  cc_expiration varchar(5) not null,
  cc_cvv varchar(3) not null,
  created_at datetime not null,
  customer varchar(50) not null
);

create table Cart_Order (
  id INTEGER IDENTITY(1,1) PRIMARY KEY,
  total_price decimal(12,2) not null,
  currency varchar(3) not null,
  customer_delivery INTEGER not null,
  created_at datetime not null
);

create table Cart_Article (
  id INTEGER IDENTITY(1,1) PRIMARY KEY,
  created_at datetime not null,
  customer varchar(50) not null,
  article INTEGER not null,
  quantity int not null,
  cart_order INTEGER
);

alter table Customer_Delivery
    add foreign key (customer) references Customer(name);

alter table Cart_Order
    add foreign key (customer_delivery) references Customer_Delivery(id);

alter table Cart_Article
    add foreign key (article) references Article(id);
alter table Cart_Article
    add foreign key (customer) references Customer(name);
alter table Cart_Article
    add foreign key (cart_order) references Cart_Order(id);

insert into Customer (created_at, name, password, email)
                values (CURRENT_TIMESTAMP, 'admin', 'admin', 'kateamorh@gmail.com');

SET IDENTITY_INSERT Article ON;

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

SET IDENTITY_INSERT Article OFF;