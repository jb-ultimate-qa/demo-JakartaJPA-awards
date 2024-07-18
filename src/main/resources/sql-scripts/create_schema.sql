create database awards;

create table award
(
    id    integer      not null
        primary key,
    year  smallint     not null,
    title varchar(255) not null
);