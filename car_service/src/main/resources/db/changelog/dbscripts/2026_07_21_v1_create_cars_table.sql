CREATE TABLE cars
(
    id              UUID                     DEFAULT RANDOM_UUID() PRIMARY KEY,
    vin             VARCHAR(17)              NOT NULL,
    reg_number      VARCHAR(20),
    production_year INTEGER,
    mileage         INTEGER                  NOT NULL,
    manufacturer    VARCHAR(100)             NOT NULL,
    model           VARCHAR(100)             NOT NULL,
    owner_id        UUID                     NOT NULL,
    created_at      TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at      TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,

    CONSTRAINT uk_cars_vin UNIQUE (vin),
    CONSTRAINT chk_cars_mileage_non_negative CHECK (mileage >= 0),
    CONSTRAINT chk_cars_production_year
        CHECK (production_year IS NULL OR production_year >= 1886)
);
