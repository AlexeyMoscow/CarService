CREATE TABLE service_centers
(
    id         UUID                     DEFAULT RANDOM_UUID() PRIMARY KEY,
    name       VARCHAR(255)             NOT NULL,
    address    VARCHAR(255)             NOT NULL,
    phone      VARCHAR(255),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);
