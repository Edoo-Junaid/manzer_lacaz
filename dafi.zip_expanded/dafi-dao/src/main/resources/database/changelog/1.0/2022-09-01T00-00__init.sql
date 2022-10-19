CREATE SEQUENCE role_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;
/


CREATE SEQUENCE utilisateur_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;
/



CREATE TABLE "role" (
    id bigserial NOT NULL,
    created timestamp(6) NULL,
    lastmodified timestamp(6) NULL,
    nom varchar(255) NULL,
    CONSTRAINT role_pkey PRIMARY KEY (id)
);
/



CREATE TABLE utilisateur (
    id bigserial NOT NULL,
    created timestamp(6) NULL,
    lastmodified timestamp(6) NULL,
    accountnonexpired bool NOT NULL,
    accountnonlocked bool NOT NULL,
    credentialsnonexpired bool NOT NULL,
    enabled bool NOT NULL,
    "password" varchar(255) NULL,
    username varchar(255) NULL,
    role_id int8 NULL,
    CONSTRAINT uk_66vu1vfh4m2fw682xmd4lobqy UNIQUE (username),
    CONSTRAINT utilisateur_pkey PRIMARY KEY (id),
    CONSTRAINT fk4gj83vcjpifherbm85but3cco FOREIGN KEY (role_id) REFERENCES "role"(id),
);
/
