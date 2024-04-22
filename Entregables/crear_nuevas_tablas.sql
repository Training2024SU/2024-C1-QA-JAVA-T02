-- crear tabla registros_creados
CREATE TABLE `registros_creados` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `fecha_registro` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

-- crear tabla cancion
CREATE TABLE `cancion` (
  `titulo` varchar(100) NOT NULL,
  `artista` varchar(45) NOT NULL,
  `album` varchar(45) NOT NULL,
  `duracion` varchar(45) NOT NULL,
  `cantidad_ejemplares` int NOT NULL,
  `cantidad_prestado` int NOT NULL,
  `cantidad_disponible` int NOT NULL,
  PRIMARY KEY (`titulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

-- crear tabla ensayo_tesis
CREATE TABLE `ensayo_tesis` (
  `titulo` varchar(100) NOT NULL,
  `autor` varchar(45) NOT NULL,
  `numero_paginas` int NOT NULL,
  `cantidad_ejemplares` int NOT NULL,
  `cantidad_prestado` int NOT NULL,
  `cantidad_disponible` int DEFAULT NULL,
  PRIMARY KEY (`titulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

-- crear tabla videograbacion
CREATE TABLE `videograbacion` (
  `titulo` varchar(100) NOT NULL,
  `director` varchar(45) NOT NULL,
  `duracion` varchar(45) NOT NULL,
  `cantidad_ejemplares` int NOT NULL,
  `cantidad_prestado` int NOT NULL,
  `cantidad_disponible` int NOT NULL,
  PRIMARY KEY (`titulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

-- crear tabla administrador
CREATE TABLE `administrador` (
  `id` int NOT NULL,
  `nombre` varchar(70) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `contrasenia` varchar(45) DEFAULT NULL,
  `departamentoAdministrado` varchar(70) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci