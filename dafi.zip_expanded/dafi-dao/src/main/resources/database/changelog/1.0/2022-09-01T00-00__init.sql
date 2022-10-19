CREATE SEQUENCE businessunit_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;
/

CREATE SEQUENCE collaborateur_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;
/

CREATE SEQUENCE contrat_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;
/

CREATE SEQUENCE contratcategorie_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;
/

CREATE SEQUENCE contratstatut_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;
/

CREATE SEQUENCE devise_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;
/



CREATE SEQUENCE etablissement_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;
/

CREATE SEQUENCE filiale_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;
/

CREATE SEQUENCE parametragecollaborateur_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;
/

CREATE SEQUENCE parametragecontrat_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;
/

CREATE SEQUENCE periodeessai_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;
/

CREATE SEQUENCE poste_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;
/

CREATE SEQUENCE role_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;
/

CREATE SEQUENCE service_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;
/

CREATE SEQUENCE societe_id_seq
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
    
CREATE TABLE businessunit (
    id bigserial NOT NULL,
    created timestamp(6) NULL,
    lastmodified timestamp(6) NULL,
    nom varchar(255) NULL,
    CONSTRAINT businessunit_pkey PRIMARY KEY (id)
);
/


CREATE TABLE collaborateur (
    id bigserial NOT NULL,
    created timestamp(6) NULL,
    lastmodified timestamp(6) NULL,
    adresseval varchar(255) NULL,
    codepostal varchar(255) NULL,
    complementadresse varchar(255) NULL,
    complementnoadresse varchar(255) NULL,
    noadresse varchar(255) NULL,
    pays varchar(255) NULL,
    ville varchar(255) NULL,
    civilite varchar(255) NULL,
    emailperso varchar(255) NULL,
    emailpro varchar(255) NULL,
    datenaissance date NULL,
    departementnaissance varchar(255) NULL,
    paysnaissance varchar(255) NULL,
    villenaissance varchar(255) NULL,
    nom varchar(255) NULL,
    nommarital varchar(255) NULL,
    prenoms varchar(255) NULL,
    telfixeperso varchar(255) NULL,
    telfixepro varchar(255) NULL,
    telmobperso varchar(255) NULL,
    telmobpro varchar(255) NULL,
    CONSTRAINT collaborateur_pkey PRIMARY KEY (id)
);
/


CREATE TABLE contratcategorie (
    id bigserial NOT NULL,
    created timestamp(6) NULL,
    lastmodified timestamp(6) NULL,
    categorie varchar(255) NULL,
    CONSTRAINT contratcategorie_pkey PRIMARY KEY (id)
);
/


CREATE TABLE contratstatut (
    id bigserial NOT NULL,
    created timestamp(6) NULL,
    lastmodified timestamp(6) NULL,
    statut varchar(255) NULL,
    CONSTRAINT contratstatut_pkey PRIMARY KEY (id)
);
/


CREATE TABLE devise (
    id bigserial NOT NULL,
    created timestamp(6) NULL,
    lastmodified timestamp(6) NULL,
    nom varchar(255) NULL,
    CONSTRAINT devise_pkey PRIMARY KEY (id)
);
/






CREATE TABLE etablissement (
    id bigserial NOT NULL,
    created timestamp(6) NULL,
    lastmodified timestamp(6) NULL,
    nom varchar(255) NULL,
    CONSTRAINT etablissement_pkey PRIMARY KEY (id)
);
/



CREATE TABLE filiale (
    id bigserial NOT NULL,
    created timestamp(6) NULL,
    lastmodified timestamp(6) NULL,
    nom varchar(255) NULL,
    CONSTRAINT filiale_pkey PRIMARY KEY (id)
);
/



CREATE TABLE poste (
    id bigserial NOT NULL,
    created timestamp(6) NULL,
    lastmodified timestamp(6) NULL,
    nom varchar(255) NULL,
    CONSTRAINT poste_pkey PRIMARY KEY (id)
);
/



CREATE TABLE "role" (
    id bigserial NOT NULL,
    created timestamp(6) NULL,
    lastmodified timestamp(6) NULL,
    nom varchar(255) NULL,
    CONSTRAINT role_pkey PRIMARY KEY (id)
);
/


CREATE TABLE service (
    id bigserial NOT NULL,
    created timestamp(6) NULL,
    lastmodified timestamp(6) NULL,
    nom varchar(255) NULL,
    CONSTRAINT service_pkey PRIMARY KEY (id)
);
/


CREATE TABLE societe (
    id bigserial NOT NULL,
    created timestamp(6) NULL,
    lastmodified timestamp(6) NULL,
    nom varchar(255) NULL,
    CONSTRAINT societe_pkey PRIMARY KEY (id)
);
/


CREATE TABLE contrat (
    id bigserial NOT NULL,
    created timestamp(6) NULL,
    lastmodified timestamp(6) NULL,
    codebic varchar(255) NULL,
    debut date NULL,
    domiciliationbancaire varchar(255) NULL,
    fin date NULL,
    heureshebdo int4 NULL,
    iban varchar(255) NULL,
    renumeration int4 NULL,
    typerecrutementrh varchar(255) NULL,
    bu_recrutement_id int8 NULL,
    contratcategorie_id int8 NULL,
    contratstatut_id int8 NULL,
    devise_id int8 NULL,
    etablissement_id int8 NULL,
    filiale_id int8 NULL,
    manager_id int8 NULL,
    poste_id int8 NULL,
    rh_recruteur_id int8 NULL,
    service_id int8 NULL,
    societe_id int8 NULL,
    CONSTRAINT contrat_pkey PRIMARY KEY (id),
    CONSTRAINT fk5woepsjhl157033c4cxybstsf FOREIGN KEY (service_id) REFERENCES service(id),
    CONSTRAINT fk68pbrot8h3plsm93aj3b6gqgc FOREIGN KEY (bu_recrutement_id) REFERENCES businessunit(id),
    CONSTRAINT fk96rtqi2ya94vl8sr8f5tatur4 FOREIGN KEY (devise_id) REFERENCES devise(id),
    CONSTRAINT fkci3n6np8fp36ag2c8lfvaddbo FOREIGN KEY (poste_id) REFERENCES poste(id),
    CONSTRAINT fkd715hjldloruihg6w1rduj9lx FOREIGN KEY (contratcategorie_id) REFERENCES contratcategorie(id),
    CONSTRAINT fkib2u7m8bveuru71rpukh5nx9g FOREIGN KEY (contratstatut_id) REFERENCES contratstatut(id),
    CONSTRAINT fkjqg5osn00plow6uprdquyf2ah FOREIGN KEY (filiale_id) REFERENCES filiale(id),
    CONSTRAINT fkjscgkmf65ldeavovh490kti8q FOREIGN KEY (societe_id) REFERENCES societe(id),
    CONSTRAINT fkka15a6tc65t3p3lmyg73pa20i FOREIGN KEY (manager_id) REFERENCES collaborateur(id),
    CONSTRAINT fkkhtd3i1i4wwaxm8l5mstt5uaj FOREIGN KEY (rh_recruteur_id) REFERENCES collaborateur(id),
    CONSTRAINT fkphlgjuepct62pieyn3vwt7h0n FOREIGN KEY (etablissement_id) REFERENCES etablissement(id)
);
/



CREATE TABLE parametragecollaborateur (
    id bigserial NOT NULL,
    created timestamp(6) NULL,
    lastmodified timestamp(6) NULL,
    cle varchar(255) NULL,
    datetimevalue timestamp(6) NULL,
    datevalue date NULL,
    groupe varchar(255) NULL,
    numbervalue float8 NULL,
    stringvalue varchar(255) NULL,
    collaborateur_id int8 NULL,
    CONSTRAINT parametragecollaborateur_pkey PRIMARY KEY (id),
    CONSTRAINT fkh0ketq9hqq2n0c1cxbeaqxuwj FOREIGN KEY (collaborateur_id) REFERENCES collaborateur(id)
);
/


CREATE TABLE parametragecontrat (
    id bigserial NOT NULL,
    created timestamp(6) NULL,
    lastmodified timestamp(6) NULL,
    cle varchar(255) NULL,
    datetimevalue timestamp(6) NULL,
    datevalue date NULL,
    groupe varchar(255) NULL,
    numbervalue float8 NULL,
    stringvalue varchar(255) NULL,
    contrat_id int8 NULL,
    CONSTRAINT parametragecontrat_pkey PRIMARY KEY (id),
    CONSTRAINT fk4ce88xgl59s1hoq40hcn535bc FOREIGN KEY (contrat_id) REFERENCES contrat(id)
);
/



CREATE TABLE periodeessai (
    id bigserial NOT NULL,
    created timestamp(6) NULL,
    lastmodified timestamp(6) NULL,
    debut date NULL,
    fin date NULL,
    contrat_id int8 NULL,
    CONSTRAINT periodeessai_pkey PRIMARY KEY (id),
    CONSTRAINT fkln84cxd1fmqn6y7ixvv2hnh73 FOREIGN KEY (contrat_id) REFERENCES contrat(id)
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
    collaborateur_id int8 NULL,
    role_id int8 NULL,
    CONSTRAINT uk_66vu1vfh4m2fw682xmd4lobqy UNIQUE (username),
    CONSTRAINT utilisateur_pkey PRIMARY KEY (id),
    CONSTRAINT fk4gj83vcjpifherbm85but3cco FOREIGN KEY (role_id) REFERENCES "role"(id),
    CONSTRAINT fkap00alpvvj138oq6453a50u6i FOREIGN KEY (collaborateur_id) REFERENCES collaborateur(id)
);
/
