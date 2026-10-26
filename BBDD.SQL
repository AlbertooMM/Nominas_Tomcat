CREATE DATABASE IF NOT EXISTS GestionNomina;

USE gestionnomina;

CREATE TABLE IF NOT EXISTS Empleados (
                                         nombre VARCHAR(100) NOT NULL,
    dni VARCHAR(20) NOT NULL,
    sexo CHAR(1) NOT NULL,
    categoria INT NOT NULL,
    anyos INT NOT NULL,
    PRIMARY KEY (dni)
    );

CREATE TABLE IF NOT EXISTS Nominas (
                                       dni VARCHAR(20) NOT NULL,
    sueldo INT NOT NULL,
    PRIMARY KEY (dni),
    FOREIGN KEY (dni) REFERENCES Empleados(dni)
    ON UPDATE CASCADE
    ON DELETE CASCADE
    );

INSERT INTO Empleados (nombre, dni, sexo, categoria, anyos)
VALUES
    ('James Gosling', '32000032G', 'm', 4, 7),
    ('Ada Lovelace', '32000031R', 'f', 1, 0);

INSERT INTO Nominas (dni, sueldo)
VALUES
    ('32000032G', 0),
    ('32000031R', 0);