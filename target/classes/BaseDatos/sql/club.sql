DROP DATABASE IF EXISTS club_montana;
CREATE DATABASE club_montana CHARACTER SET utf8mb4;
USE club_montana;
CREATE TABLE socio (
                       idsocio INT AUTO_INCREMENT PRIMARY KEY,
                       nombresocio VARCHAR(60) NOT NULL,
                       telefonosocio VARCHAR(15),
                       fecha_alta DATE NOT NULL
);
CREATE TABLE ruta (
                      idruta INT AUTO_INCREMENT PRIMARY KEY,
                      nombreruta VARCHAR(80) NOT NULL,
                      dificultad ENUM('BAJA','MEDIA','ALTA') NOT NULL,
                      idsocio INT NOT NULL,
                      FOREIGN KEY (idsocio) REFERENCES socio(idsocio)
);
CREATE TABLE participante (
                              idparticipante INT AUTO_INCREMENT PRIMARY KEY,
                              nombreparticipante VARCHAR(60) NOT NULL,
                              telefonoparticipante VARCHAR(15),
                              idruta INT NOT NULL,
                              FOREIGN KEY (idruta) REFERENCES ruta(idruta) ON DELETE CASCADE
);
INSERT INTO socio (nombresocio, telefonosocio, fecha_alta) VALUES
                                                               ('Marta García Fernández', '600 11 32 08', '2019-03-14'),
                                                               ('Juan Pérez Lago', '600 24 18 77', '2020-09-05'),
                                                               ('Lucía Alonso Rey', '600 35 90 41', '2021-01-22'),
                                                               ('Ana Domínguez Vila', '600 48 27 13', '2022-06-10'),
                                                               ('Carmen Vilas Alonso', '600 52 66 90', '2024-02-03'),
                                                               ('Hugo Rodríguez Sousa', '600 63 71 25', '2025-09-19');
INSERT INTO ruta (nombreruta, dificultad, idsocio) VALUES
                                                       ('Ruta del Cares - Picos de Europa', 'MEDIA', 1),
                                                       ('Lagos de Covadonga - Picos de Europa', 'BAJA', 3),
                                                       ('Cola de Caballo - Pirineos', 'BAJA', 2),
                                                       ('Ibón de Anayet - Pirineos', 'MEDIA', 4),
                                                       ('Ascensión al Mulhacén desde Capileira - Sierra Nevada', 'ALTA', 1),
                                                       ('Vereda de la Estrella - Sierra Nevada', 'ALTA', 6);
INSERT INTO participante (nombreparticipante, telefonoparticipante, idruta) VALUES
                                                                                ('Pablo Iglesias Mora', '610 20 30 01', 1),
                                                                                ('Sara Núñez Pardo', '610 20 30 02', 1),
                                                                                ('Diego Castro Lema', '610 20 30 03', 1),
                                                                                ('Elena Rivas Couto', NULL, 1),
                                                                                ('Iván Prado Seoane', '610 20 30 05', 2),
                                                                                ('Noa Barreiro Gil', '610 20 30 06', 2),
                                                                                ('Óscar Lage Tato', NULL, 2),
                                                                                ('Paula Vázquez Rey', '610 20 30 08', 3),
                                                                                ('Martín Ferro Dopico', '610 20 30 09', 3),
                                                                                ('Irene Souto Vidal', '610 20 30 10', 3),
                                                                                ('Álvaro Cid Arca', '610 20 30 11', 3),
                                                                                ('Lola Mariño Pena', '610 20 30 12', 4),
                                                                                ('Raúl Otero Blanco', NULL, 4),
                                                                                ('Cris Fraga Abal', '610 20 30 14', 5),
                                                                                ('Adrián Louro Neira', '610 20 30 15', 5),
                                                                                ('Sofía Tilve Camba', '610 20 30 16', 5),
                                                                                ('Mario Lorenzo Pita', '610 20 30 17', 6),
                                                                                ('Alba Quintela Roca', '610 20 30 18', 6);