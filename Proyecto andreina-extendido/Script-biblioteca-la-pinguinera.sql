CREATE SCHEMA IF NOT EXISTS `biblioteca-la-pinguinera-nueva`;

USE `biblioteca-la-pinguinera-nueva`;

CREATE TABLE `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contrasena` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `rol` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `libro` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `autor` varchar(255) DEFAULT NULL,
  `cantidadDisponible` int NOT NULL,
  `cantidadEjemplares` int NOT NULL,
  `cantidadPrestados` int NOT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `areaDelConocimiento` varchar(255) DEFAULT NULL,
  `numeroDePaginas` int NOT NULL,
  `fueBorrado` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `novela` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `autor` varchar(255) DEFAULT NULL,
  `cantidadDisponible` int NOT NULL,
  `cantidadEjemplares` int NOT NULL,
  `cantidadPrestados` int NOT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `edadDeLecturaSugerida` int NOT NULL,
  `genero` varchar(255) DEFAULT NULL,
  `fueBorrado` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `prestamo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `estadoPrestamo` varchar(255) DEFAULT NULL,
  `feachaDeEntrega` datetime(6) DEFAULT NULL,
  `fechaDePrestamo` datetime(6) DEFAULT NULL,
  `material_id` bigint DEFAULT NULL,
  `usuario_id` bigint NOT NULL,
  `fechaDeEntrega` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbivngnhldmsxy0069wr5u9a06` (`usuario_id`),
  CONSTRAINT `FKbivngnhldmsxy0069wr5u9a06` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;