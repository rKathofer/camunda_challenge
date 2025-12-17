CREATE TABLE animal_images (
    id UUID PRIMARY KEY,
    data BYTEA NOT NULL,
    content_type VARCHAR(100) NOT NULL,
    animal_type VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL
);
