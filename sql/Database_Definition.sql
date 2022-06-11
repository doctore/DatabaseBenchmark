create user benchmark with encrypted password 'benchmark';

create database benchmark with owner benchmark;

-- To connect from Linux console using the new user
psql -d benchmark -U benchmark


create table ingredient(
   id     smallserial   not null   constraint ingredient_pk primary key,
   name   varchar(64)   not null
);

create unique index ingredient_name_uindex on ingredient (name);


create table pizza(
   id     smallserial        not null   constraint pizza_pk primary key,
   name   varchar(64),
   cost   double precision   not null
);

create unique index pizza_name_uindex on pizza (name);


create table pizza_ingredient(
   pizza_id        smallint   not null   constraint pizza_ingredient_pizza_id_fk references pizza,
   ingredient_id   smallint   not null   constraint pizza_ingredient_ingredient_id_fk references ingredient,
   constraint pizza_ingredient_pk primary key (pizza_id, ingredient_id)
);


create table order(
   id        serial                        not null   constraint order_pk primary key,
   code      varchar(64)                   not null,
   created   timestamp without time zone   not null
);

create unique index order_code_uindex on order (code);


create table order_line(
   id         serial             not null    constraint order_line_pk primary key,
   order_id   int                not null    constraint order_line_order_id_fk references order,
   pizza_id   smallint           not null    constraint order_line_pizza_id_fk references pizza,
   cost       double precision   not null,
   amount     smallint           not null
);
