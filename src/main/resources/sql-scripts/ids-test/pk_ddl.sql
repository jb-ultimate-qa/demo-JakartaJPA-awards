-- sequences
create sequence auto_id_entity_seq
    increment by 50;

alter sequence auto_id_entity_seq owner to postgres;

create sequence seq_by_orm_id_entity_seq
    increment by 50;

alter sequence seq_by_orm_id_entity_seq owner to postgres;

create sequence seq_gen_gen_id_entity_seq
    start with 5
    increment by 2;

alter sequence seq_gen_gen_id_entity_seq owner to postgres;

create sequence seqgenidentity_seq
    increment by 50;

alter sequence seqgenidentity_seq owner to postgres;

-- tables
create table auto_id_entity
(
    id integer not null
        primary key
);

alter table auto_id_entity owner to postgres;

create table auto_uuid_entity
(
    id uuid not null
        primary key
);

alter table auto_uuid_entity owner to postgres;

create table identity_id_entity
(
    id serial
        primary key
);

alter table identity_id_entity owner to postgres;

create table plain_id_entity
(
    id integer not null
        primary key
);

alter table plain_id_entity owner to postgres;

create table seq_by_orm_id_entity
(
    id integer not null
        primary key
);

alter table seq_by_orm_id_entity owner to postgres;

create table seq_gen_gen_id_entity
(
    id bigint not null
        primary key
);

alter table seq_gen_gen_id_entity owner to postgres;

create table seq_gen_id_entity
(
    id integer not null
        primary key
);

alter table seq_gen_id_entity owner to postgres;

create table table_id_entity
(
    id integer not null
        primary key
);

alter table table_id_entity owner to postgres;

create table table_ids
(
    seq_value bigint,
    seq_id    varchar(255) not null
        primary key
);

alter table table_ids owner to postgres;

create table uuid_entity
(
    id uuid not null
        primary key
);

alter table uuid_entity owner to postgres;

