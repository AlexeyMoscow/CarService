CREATE TABLE car_services
(
    id                UUID                     DEFAULT RANDOM_UUID() PRIMARY KEY,
    car_id            UUID                     NOT NULL,
    service_center_id UUID,
    service_date      TIMESTAMP WITH TIME ZONE NOT NULL,
    mileage           INTEGER,
    description       VARCHAR(255),
    cost              DECIMAL(19, 2),
    created_at        TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at        TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,

    CONSTRAINT fk_car_services_car
        FOREIGN KEY (car_id) REFERENCES cars (id),
    CONSTRAINT fk_car_services_service_center
        FOREIGN KEY (service_center_id) REFERENCES service_centers (id),
    CONSTRAINT chk_car_services_mileage_non_negative
        CHECK (mileage IS NULL OR mileage >= 0),
    CONSTRAINT chk_car_services_cost_non_negative
        CHECK (cost IS NULL OR cost >= 0)
);
