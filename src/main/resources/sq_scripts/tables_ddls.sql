
-- Warning: You can generate script only for one table/view at a time in your Free plan
--
-- *************** SqlDBM: PostgreSQL ****************;
-- ***************************************************;


-- ************************************** "public".PARTY

CREATE TABLE "data_capture".PARTY
(
 "id"        bigint NOT NULL,
 title     character varying(50) NOT NULL,
 name      character varying(50) NOT NULL,
 surname   character varying(50) NOT NULL,
 gender    character varying(50) NOT NULL,
 id_number character varying(50) NOT NULL,
 CONSTRAINT PK_party PRIMARY KEY ( "id" )
);


-- *************** SqlDBM: PostgreSQL ****************;
-- ***************************************************;


-- ************************************** "public".EMPLOYMENT

CREATE TABLE "data_capture".EMPLOYMENT
(
 "id"               bigint NOT NULL,
 are_you_employed char(50) NOT NULL,
 name_of_employer character varying(50) NOT NULL,
 job_title        character varying(50) NOT NULL,
 party_id         bigint NOT NULL,
 CONSTRAINT PK_employment PRIMARY KEY ( "id" ),
 CONSTRAINT FK_91 FOREIGN KEY ( party_id ) REFERENCES "data_capture".PARTY ( "id" )
);

CREATE INDEX fkIdx_92 ON "data_capture".EMPLOYMENT
(
 party_id
);


-- *************** SqlDBM: PostgreSQL ****************;
-- ***************************************************;


-- ************************************** "public".ADDRESS

CREATE TABLE "data_capture".ADDRESS
(
 "id"            bigint NOT NULL,
 address1      character varying(50) NOT NULL,
 address2      character varying(50) NULL,
 street        character varying(50) NULL,
 house         character varying(50) NULL,
 postalcode    character varying(50) NOT NULL,
 city          character varying(50) NOT NULL,
 province      character varying(50) NOT NULL,
 country       character varying(50) NOT NULL,
 type          character varying(50) NOT NULL,
 party_id      bigint NULL,
 kind          character varying(50) NOT NULL,
 employment_id bigint NULL,
 CONSTRAINT PK_address PRIMARY KEY ( "id" ),
 CONSTRAINT FK_66 FOREIGN KEY ( party_id ) REFERENCES "data_capture".PARTY ( "id" ),
 CONSTRAINT FK_94 FOREIGN KEY ( employment_id ) REFERENCES "data_capture".EMPLOYMENT ( "id" )
);

CREATE INDEX fkIdx_67 ON "data_capture".ADDRESS
(
 party_id
);

CREATE INDEX fkIdx_95 ON "data_capture".ADDRESS
(
 employment_id
);



-- *************** SqlDBM: PostgreSQL ****************;
-- ***************************************************;


-- ************************************** "public".CONTACT_POINT

CREATE TABLE "data_capture".CONTACT_POINT
(
 "id"        bigint NOT NULL,
 telephone bigint NOT NULL,
 email     bigint NOT NULL,
 party_id      bigint NOT NULL,
 CONSTRAINT PK_contact_point PRIMARY KEY ( "id" ),
 CONSTRAINT FK_49 FOREIGN KEY ( party_id ) REFERENCES "data_capture".PARTY ( "id" )
);

CREATE INDEX fkIdx_50 ON "data_capture".CONTACT_POINT
(
 party_id
);


-- *************** SqlDBM: PostgreSQL ****************;
-- ***************************************************;


-- ************************************** "public".TELEPHONE

CREATE TABLE "data_capture".TELEPHONE
(
 "id"            bigint NOT NULL,
 tel1          character varying(50) NOT NULL,
 tel2          character varying(50) NULL,
 contact_point bigint NOT NULL,
 CONSTRAINT PK_telephone PRIMARY KEY ( "id" ),
 CONSTRAINT FK_44 FOREIGN KEY ( contact_point ) REFERENCES "data_capture".CONTACT_POINT ( "id" )
);

CREATE INDEX fkIdx_45 ON "data_capture".TELEPHONE
(
 contact_point
);


-- *************** SqlDBM: PostgreSQL ****************;
-- ***************************************************;


-- ************************************** "public".EMAIL

CREATE TABLE "data_capture".EMAIL
(
 "id"            bigint NOT NULL,
 email1        character varying(50) NOT NULL,
 email2        character varying(50) NULL,
 contact_point bigint NOT NULL,
 CONSTRAINT PK_email PRIMARY KEY ( "id" ),
 CONSTRAINT FK_41 FOREIGN KEY ( contact_point ) REFERENCES "data_capture".CONTACT_POINT ( "id" )
);

CREATE INDEX fkIdx_42 ON "data_capture".EMAIL
(
 contact_point
);

-- *************** SqlDBM: PostgreSQL ****************;
-- ***************************************************;


-- ************************************** "public".EDUCATION

CREATE TABLE "data_capture".EDUCATION
(
 "id"                    bigint NOT NULL,
 institution_name      character varying(50) NOT NULL,
 highest_qualification character varying(50) NOT NULL,
 qualification_name    character varying(50) NOT NULL,
 date_started          character varying(50) NOT NULL,
 date_ended            character varying(50) NOT NULL,
 party_id              bigint NOT NULL,
 CONSTRAINT PK_education PRIMARY KEY ( "id" ),
 CONSTRAINT FK_78 FOREIGN KEY ( party_id ) REFERENCES "data_capture".PARTY ( "id" )
);

CREATE INDEX fkIdx_79 ON "data_capture".EDUCATION
(
 party_id
);







