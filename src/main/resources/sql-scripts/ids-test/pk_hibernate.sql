create sequence auto_id_entity_SEQ start with 1 increment by 50

create sequence seq_by_orm_id_entity_SEQ start with 1 increment by 50

create sequence SeqGenIdEntity_SEQ start with 1 increment by 50

create table auto_id_entity (
                                id integer not null,
                                primary key (id)
)

create table identity_id_entity (
                                    id serial not null,
                                    primary key (id)
)

create table plain_id_entity (
                                 id integer not null,
                                 primary key (id)
)

create table seq_by_orm_id_entity (
                                      id integer not null,
                                      primary key (id)
)

create table seq_gen_id_entity (
                                   id integer not null,
                                   primary key (id)
)