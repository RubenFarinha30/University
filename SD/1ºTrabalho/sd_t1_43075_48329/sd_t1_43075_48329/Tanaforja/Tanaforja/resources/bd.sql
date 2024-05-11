CREATE TABLE artista (
    artist_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    artist_type VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    is_acting BOOLEAN NOT NULL,
    approval_status VARCHAR(20) DEFAULT 'Não Aprovado' CHECK (approval_status IN ('Aprovado', 'Não Aprovado')),
);

CREATE TABLE donation (
    donation_id SERIAL PRIMARY KEY,
    id INTEGER REFERENCES artista(artist_id),
    donation_amount DECIMAL(10,2) DEFAULT 0.00,
);
