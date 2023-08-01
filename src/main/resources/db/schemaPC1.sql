CREATE SEQUENCE users_id_seq START 1;
CREATE SEQUENCE operation_id_seq START 1;
CREATE SEQUENCE appointment_id_seq START 1;
CREATE SEQUENCE category_id_seq START 1;
CREATE SEQUENCE offers_id_seq START 1;

CREATE TABLE IF NOT EXISTS postgres.public.users
(
    id integer NOT NULL nextval('users_id_seq'),
    first_name character varying(25),
    last_name character varying(25),
    email character varying(50) NOT NULL UNIQUE,
    password text NOT NULL,
    roles character varying(25) NOT NULL DEFAULT 'PATIENT',
    joined_on timestamp without time zone DEFAULT NOW(),
    validity boolean NOT NULL DEFAULT 'TRUE',
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS postgres.public.operation
(
    id integer NOT NULL DEFAULT nextval('operation_id_seq'),
    name character varying(25),
    description text,
    price double precision,
    validity boolean NOT NULL DEFAULT 'TRUE',
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS postgres.public.appointment
(
    id integer NOT NULL DEFAULT nextval('appointment_id_seq'),
    patient_id integer NOT NULL,
    doctor_id integer NOT NULL,
    operation_id integer NOT NULL,
    start_date_time timestamp without time zone NOT NULL,
    end_date_time timestamp without time zone NOT NULL,
    completion_status character varying(25) NOT NULL DEFAULT 'UNCOMPLETED',
    payment_status character varying(25) NOT NULL DEFAULT 'UNPAID',
    validity boolean NOT NULL DEFAULT 'TRUE',
    PRIMARY KEY (id),
    FOREIGN KEY(patient_id) REFERENCES postgres.public.users(id)
                            ON UPDATE NO ACTION
                            ON DELETE NO ACTION,
    FOREIGN KEY(doctor_id) REFERENCES postgres.public.users(id)
                            ON UPDATE NO ACTION
                            ON DELETE NO ACTION,
    FOREIGN KEY(operation_id) REFERENCES postgres.public.operation(id)
                            ON UPDATE NO ACTION
                            ON DELETE NO ACTION
    );

CREATE TABLE IF NOT EXISTS postgres.public.category
(
    id integer NOT NULL DEFAULT nextval('category_id_seq'),
    name character varying(25),
    discount_percentage double precision,
    validity boolean NOT NULL DEFAULT 'TRUE',
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS postgres.public.offers
(
    id integer NOT NULL DEFAULT nextval('offers_id_seq'),
    name character varying(25),
    begin timestamp without time zone NOT NULL,
    finish timestamp without time zone NOT NULL,
    price double precision,
    category_id integer NOT NULL,
    validity boolean NOT NULL DEFAULT 'TRUE',
    PRIMARY KEY (id),
    FOREIGN KEY(category_id) REFERENCES postgres.public.category(id)
                    ON UPDATE NO ACTION
                    ON DELETE NO ACTION
    );

CREATE TABLE IF NOT EXISTS postgres.public.offers_operations
(
    offers_id integer NOT NULL,
    operation_id integer NOT NULL,
    FOREIGN KEY(operation_id) REFERENCES postgres.public.operation(id)
                    ON UPDATE NO ACTION
                    ON DELETE NO ACTION,
    FOREIGN KEY(offers_id) REFERENCES postgres.public.offers(id)
                    ON UPDATE NO ACTION
                    ON DELETE NO ACTION,
    );

CREATE TABLE IF NOT EXISTS postgres.public.token
(
    id integer NOT NULL,
    expired boolean NOT NULL,
    revoked boolean NOT NULL,
    token text NOT NULL,
    token_type character varying(25) NOT NULL,
    user_id integer NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY(user_id) REFERENCES postgres.public.users(id)
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    ):