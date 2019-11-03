-- Creació de la base de dades
CREATE DATABASE aplicacion;
use aplicacion;
-- Creació de l'usuari
CREATE USER IF NOT EXISTS 'tofol'@'localhost' IDENTIFIED BY '1234';

-- Otorgació de permisos a l'usuari
GRANT ALL PRIVILEGES ON aplicacion.* TO 'tofol'@'localhost' WITH GRANT OPTION;

-- Creació de la taula usuario
CREATE TABLE IF NOT EXISTS usuario ( 
    id INT AUTO_INCREMENT PRIMARY KEY,   
    nombre VARCHAR(255) NOT NULL,
    password varchar(255) NOT NULL,
    imgPerfil varchar(255)
)  ENGINE=INNODB;

-- Creació de la taula hipoteca
CREATE TABLE IF NOT EXISTS hipoteca ( 
    id INT AUTO_INCREMENT PRIMARY KEY,   
    fecha datetime NOT NULL,
    prestamo double NOT NULL,
    interes float NOT NULL,
    plazo int NOT NULL,
    periodicidad int NOT NULL,
    idUsuario int NOT NULL,
    foreign key (idUsuario)
		references usuario (id)
        on delete cascade
        on update restrict
)  ENGINE=INNODB;

-- Inserció de usuaris a la taula
INSERT INTO usuario (nombre, password) VALUES ('Tomas','abretesesamo');
INSERT INTO usuario (nombre, password) VALUES ('Tomenos','alohomora');
INSERT INTO usuario (nombre, password) VALUES ('Namas','swordfish');
INSERT INTO usuario (nombre, password) VALUES ('Namenos','password');

-- Inserció de hipoteques a la taula
INSERT INTO hipoteca (fecha, prestamo, interes, plazo, periodicidad, idUsuario) VALUES ('2019-03-05 23:59:59',69420.45,3.2,50,0,1);
