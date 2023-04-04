CREATE TABLE IF NOT EXISTS dentalt.users
(
    id integer NOT NULL,
    first_name character varying(25),
    last_name character varying(25),
    email character varying(25) NOT NULL,
    password text NOT NULL,
    roles character varying(25) NOT NULL DEFAULT 'PATIENT',
    joined_on timestamp with time zone,
                            validity boolean NOT NULL DEFAULT 'TRUE',
                            CONSTRAINT users_pkey PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS dentalt.operation
(
    id integer NOT NULL,
    name character varying(25),
    description text,
    price double precision,
    validity boolean NOT NULL DEFAULT 'TRUE',
    CONSTRAINT operation_pkey PRIMARY KEY (id)
    );

CREATE TABLE dentalt.appointment
(
    id integer NOT NULL,
    patient_id integer NOT NULL,
    doctor_id integer NOT NULL,
    operation_id integer NOT NULL,
    start_date_time timestamp with time zone NOT NULL,
    end_date_time timestamp with time zone NOT NULL,
    completion_status character varying(25) NOT NULL DEFAULT 'UNCOMPLETED',
    payment_status character varying(25) NOT NULL DEFAULT 'UNPAID',
    validity boolean NOT NULL DEFAULT 'TRUE',
    CONSTRAINT appointment_pkey PRIMARY KEY (id),
    FOREIGN KEY(patient_id) REFERENCES dentalt.users(id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    FOREIGN KEY(doctor_id) REFERENCES dentalt.users(id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    FOREIGN KEY(operation_id) REFERENCES dentalt.operation(id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);