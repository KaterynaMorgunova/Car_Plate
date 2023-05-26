create table if not exists Article (
  id bigint not null,
  created_at timestamp not null,
  name varchar(50) not null,
  description varchar(255) not null,
  image_url varchar(255) not null,
  price decimal(12,2) not null,
  currency varchar(3) not null,
  available_quantity int not null
);

create table if not exists Customer (
  created_at timestamp not null,
  name varchar(50) not null,
  phone varchar(255),
  email varchar(255),
  password varchar(50) not null
);

create table if not exists Customer_Delivery (
  id identity,
  delivery_name varchar(50) not null,
  delivery_street varchar(50) not null,
  delivery_city varchar(50) not null,
  delivery_state varchar(2) not null,
  delivery_zip varchar(10) not null,
  cc_number varchar(16) not null,
  cc_expiration varchar(5) not null,
  cc_cvv varchar(3) not null,
  created_at timestamp not null,
  customer varchar(50) not null
);

create table if not exists Cart_Order (
  id identity,
  total_price decimal(12,2) not null,
  currency varchar(3) not null,
  customer_delivery bigint not null,
  created_at timestamp not null
);

create table if not exists Cart_Article (
  id identity,
  created_at timestamp not null,
  customer varchar(50) not null,
  article bigint not null,
  quantity int not null,
  cart_order bigint
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

