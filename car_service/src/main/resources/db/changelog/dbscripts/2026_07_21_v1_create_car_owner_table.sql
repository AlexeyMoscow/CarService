CREATE TABLE car_owners
(
    id                UUID                     DEFAULT RANDOM_UUID() PRIMARY KEY,
    full_name         VARCHAR(255)             NOT NULL,
    phone             VARCHAR(255),
    email             VARCHAR(255),
    record_created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    record_updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

ALTER TABLE cars
    ADD CONSTRAINT fk_cars_owner
        FOREIGN KEY (owner_id) REFERENCES car_owners (id);
