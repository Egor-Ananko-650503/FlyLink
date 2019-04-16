-- Database: flylinkdb

-- DROP DATABASE flylinkdb;

CREATE DATABASE flylinkdb
  WITH OWNER = flylinkdb
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       CONNECTION LIMIT = -1;


-- SEQUENCE: public.file_id_seq

-- DROP SEQUENCE public.file_id_seq;

CREATE SEQUENCE public.file_id_seq;

ALTER SEQUENCE public.file_id_seq
    OWNER TO flylinkdb;

-- SEQUENCE: public.user_id_seq

-- DROP SEQUENCE public.user_id_seq;

CREATE SEQUENCE public.user_id_seq;

ALTER SEQUENCE public.user_id_seq
    OWNER TO flylinkdb;	   

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
    OWNER TO flylinkdb;

-- Table: public.file

-- DROP TABLE public.file;

CREATE TABLE public.file
(
  id integer NOT NULL DEFAULT nextval('file_id_seq'::regclass),
  name character varying(80),
  type character varying(40),
  size bigint,
  path character varying(100),
  uploader_id integer,
  upload_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
  CONSTRAINT file_id PRIMARY KEY (id),
  CONSTRAINT fk_user_id FOREIGN KEY (uploader_id)
      REFERENCES public."user" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.file
    OWNER TO flylinkdb;
