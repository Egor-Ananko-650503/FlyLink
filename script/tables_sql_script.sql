-- Database: flylinkdb

-- DROP DATABASE flylinkdb;

CREATE DATABASE flylinkdb
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Russian_Russia.1251'
       LC_CTYPE = 'Russian_Russia.1251'
       CONNECTION LIMIT = -1;

-- Table: public."user"

-- DROP TABLE public."user";

CREATE TABLE public."user"
(
  id integer NOT NULL DEFAULT nextval('user_id_seq'::regclass),
  name character varying(80),
  email character varying(50),
  login character varying(20),
  password character varying(20),
  CONSTRAINT id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public."user"
  OWNER TO postgres;

-- Table: public.file

-- DROP TABLE public.file;

CREATE TABLE public.file
(
  id integer NOT NULL DEFAULT nextval('file_id_seq'::regclass),
  name character varying(80),
  type character varying(20),
  size bigint,
  path character varying(100),
  uploader_id integer NOT NULL DEFAULT nextval('file_uploader_id_seq'::regclass),
  CONSTRAINT file_id PRIMARY KEY (id),
  CONSTRAINT fk_user_id FOREIGN KEY (uploader_id)
      REFERENCES public."user" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.file
  OWNER TO postgres;
