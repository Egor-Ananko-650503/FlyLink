-- Scripts for creating all needed entities for application.
-- Requirements: [Database: flylinkdb; Role: flylinkdb; Schema: public]

create sequence fl_file_id_seq;

alter sequence fl_file_id_seq owner to flylinkdb;

create sequence fl_user_id_seq;

alter sequence fl_user_id_seq owner to flylinkdb;

create table if not exists fl_user
(
    id integer default nextval('fl_user_id_seq'::regclass) not null
        constraint fl_user_id
            primary key,
    name varchar(80),
    email varchar(50),
    login varchar(20),
    password varchar(128)
);

alter table fl_user owner to flylinkdb;

create table if not exists fl_file
(
    id integer default nextval('fl_file_id_seq'::regclass) not null
        constraint fl_file_id
            primary key,
    name varchar(80),
    type varchar(40),
    size bigint,
    path varchar(100),
    uploader_id integer
        constraint fk_fl_user_id
            references fl_user (id),
    upload_date timestamp with time zone default CURRENT_TIMESTAMP not null
);

alter table fl_file owner to flylinkdb;

create table if not exists fl_role
(
    id serial not null
        constraint fl_role_pk
            primary key,
    name varchar(21) not null
);

alter table fl_role owner to flylinkdb;

create unique index if not exists fl_role_id_uindex
    on fl_role (id);

create table if not exists fl_user_role
(
    id serial not null
        constraint fl_user_role_pk
            primary key,
    user_id integer not null
        constraint fk_fl_user_id
            references fl_user
            on update cascade on delete cascade,
    role_id integer not null
        constraint fk_fl_role_id
            references fl_role
            on update cascade on delete cascade
);

alter table fl_user_role owner to flylinkdb;

create unique index if not exists user_role_id_uindex
    on fl_user_role (id);

