-- liquibase formatted sql

-- changeset alex turaev:1
-- drop table if exists items;
-- drop table if exists users;

-- товары
create table if not exists items(
    id bigserial primary key,
    title varchar(255) not null,
    description text,
    price numeric(10, 2),
    quantity int
    );

-- картинки товаров
create table if not exists item_pics(
    item_id bigint,
    image_type varchar(50),
    image bytea,

    foreign key (item_id) references items(id) on delete cascade
    );

-- пользователи
create table if not exists users(
    id bigserial primary key,
    login varchar(255)
    );

insert into users (login) values('user1'), ('user2');

-- корзина
create table if not exists users_items(
    item_id bigint,
    count_item int,
    user_id bigint,

    foreign key (item_id) references items(id) on delete cascade,
    foreign key (user_id) references users(id) on delete cascade
    );

-- заказы
create table if not exists orders(
    order_id bigint,
    item_id bigint,
    count_item int,
    price numeric(10, 2),
    user_id bigint,

    foreign key (item_id) references items(id),
    foreign key (user_id) references users(id)
    );