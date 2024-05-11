DROP TABLE IF EXISTS cargo;
DROP TABLE IF EXISTS Atleta;
DROP TABLE IF EXISTS utilizador;
DROP TABLE IF EXISTS eventos;

CREATE TABLE utilizador (
  username varchar(30) NOT NULL,
  password varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  PRIMARY KEY (username)
);

CREATE TABLE cargo (
  username varchar(30) NOT NULL,
  user_role varchar(15) NOT NULL,
  FOREIGN KEY (username) REFERENCES utilizador (username)
);

CREATE TABLE eventos (
    eID SERIAL PRIMARY KEY,
    ename VARCHAR(30) NOT NULL,
    edate DATE NOT NULL,
    eprice FLOAT NOT NULL,
    edescription VARCHAR(255) NOT NULL
);

CREATE TABLE Atleta (
    Dorsal INT NOT NULL,
    idEvento INT NOT NULL,
    username VARCHAR(30) NOT NULL,
    nomeAtleta VARCHAR(255) NOT NULL,
    nif INT NOT NULL ,
    genero VARCHAR(10) NOT NULL,
    escalao VARCHAR(50) NOT NULL,
    paystatus BOOLEAN NOT NULL,
    start TIMESTAMP,
    P1 TIMESTAMP,
    P2 TIMESTAMP,
    P3 TIMESTAMP,
    finish TIMESTAMP,
    ttime BIGINT,
    entity int ,
    ref int ,
    PRIMARY KEY (nif, idEvento),
    FOREIGN KEY (username) REFERENCES utilizador(username),
    FOREIGN KEY (idEvento) REFERENCES eventos(eID)
);
-- utilizador
-- admin / teste
INSERT INTO utilizador (username, password, email)
VALUES ('admin', '$2a$12$uOozNp2mINbR62VPGrUcLefxHzLH4X/ykKD9ilMYTTGvcezXhIBIy', 'admin@gmail.com');

-- bolt / teste
INSERT INTO utilizador (username, password, email)
VALUES ('bolt', '$2a$12$uOozNp2mINbR62VPGrUcLefxHzLH4X/ykKD9ilMYTTGvcezXhIBIy', 'bolt@hotmail.com');
-- gatlin / teste
INSERT INTO utilizador (username, password, email)
VALUES ('gatlin', 'teste', 'gatlin@example.com');

-- cargos
INSERT INTO cargo (username, user_role) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO cargo (username, user_role) VALUES ('bolt', 'ROLE_USER');

-- eventos
INSERT INTO eventos (ename, edate, eprice, edescription) VALUES('Maratona Veloz', '2023-03-10', 15, 'Uma maratona emocionante para os amantes da velocidade.');
INSERT INTO eventos (ename, edate, eprice, edescription) VALUES('Corrida Noturna', '2023-04-20', 25, 'Uma corrida sob as estrelas para os entusiastas da noite.');
INSERT INTO eventos (ename, edate, eprice, edescription) VALUES('Desafio Trail Run', '2024-03-05', 12, 'Uma corrida de trail desafiadora para os amantes da natureza.');
INSERT INTO eventos (ename, edate, eprice, edescription) VALUES('Sprint Rápido', '2024-02-15', 18, 'Uma corrida curtae e intensa, para testar sua velocidade.');

-- Atletas
INSERT INTO Atleta (Dorsal, idEvento, username, nomeAtleta, nif,genero, escalao, paystatus)
VALUES
((SELECT COUNT(*) AS n FROM Atleta where idEvento =1)+1,1, 'bolt', 'Atleta 1','111', 'M', 'sénior', false),
((SELECT COUNT(*) as m FROM Atleta where idEvento =2)+1, 2, 'gatlin', 'Atleta 3','222',  'M', 'sénior', false);

INSERT INTO Atleta (Dorsal, idEvento, username, nomeAtleta, nif, genero, escalao, paystatus, start, P1, P2, P3, finish)
VALUES
  ((SELECT COUNT(*) AS n FROM Atleta WHERE idEvento = 1) + 1, 1, 'gatlin', 'Atleta 2', '333', 'F', 'Vet35', true, '2024-01-01 22:00:01', '2024-01-01 22:13:02', '2024-01-01 22:18:22', '2024-01-01 22:22:22', '2024-01-01 22:27:22'),
  ((SELECT COUNT(*) AS m FROM Atleta WHERE idEvento = 2) + 1, 2, 'bolt', 'Atleta 4', '444', 'F', 'Vet35', false, '2024-01-01 22:00:01', '2024-01-01 22:13:02', '2024-01-01 22:18:22', '2024-01-01 22:22:22', '2024-01-01 22:27:22');
