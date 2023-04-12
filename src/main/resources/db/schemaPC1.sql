CREATE TABLE IF NOT EXISTS postgres.public.users
(
    id integer,
    first_name character varying(25),
    last_name character varying(25),
    email character varying(25) NOT NULL,
    password text NOT NULL,
    roles character varying(25) NOT NULL DEFAULT 'PATIENT',
    joined_on timestamp with time zone,
    validity boolean NOT NULL DEFAULT 'TRUE',
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS postgres.public.operation
(
    id integer,
    name character varying(25),
    description text,
    price double precision,
    validity boolean NOT NULL DEFAULT 'TRUE',
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS postgres.public.appointment
(
    id integer,
    patient_id integer NOT NULL,
    doctor_id integer NOT NULL,
    operation_id integer NOT NULL,
    start_date_time timestamp with time zone NOT NULL,
    end_date_time timestamp with time zone NOT NULL,
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