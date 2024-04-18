-- Configuración inicial
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- Creación del esquema `pinguinera`
CREATE SCHEMA IF NOT EXISTS `pinguinera` DEFAULT CHARACTER SET utf8;
USE `pinguinera`;

-- Tabla `Empleado`
CREATE TABLE IF NOT EXISTS `pinguinera`.`Empleado` (
    `idEmpleado` INT NOT NULL AUTO_INCREMENT,
    `Nombre` VARCHAR(100) NULL,
    `Contraseña` VARCHAR(100) NULL,
    `Correo` VARCHAR(100) NULL,
    `Rol` ENUM('ADMINISTRATIVO', 'ASISTENTE') NOT NULL,
    `EsAdministrativo` TINYINT NOT NULL DEFAULT 0,
    PRIMARY KEY (`idEmpleado`),
    UNIQUE KEY `uk_Empleado_Correo` (`Correo`),
    UNIQUE KEY `uk_administrativo` (`EsAdministrativo`)
) ENGINE=InnoDB;

-- Tabla `Usuario`
CREATE TABLE IF NOT EXISTS `pinguinera`.`Usuario` (
    `idUsuario` INT AUTO_INCREMENT NOT NULL, 
    `Correo` VARCHAR(100) NOT NULL,
    `Nombre` VARCHAR(100) NULL,
    `Contraseña` VARCHAR(100) NULL,
    PRIMARY KEY (`idUsuario`), 
    UNIQUE (`Correo`) 
) ENGINE=InnoDB;

-- Tabla `Publicacion`
CREATE TABLE IF NOT EXISTS `pinguinera`.`Publicacion` (
    `idPublicacion` INT AUTO_INCREMENT NOT NULL, 
    `Titulo` VARCHAR(100) NOT NULL,
    `tipo_publicacion` ENUM('LIBRO', 'NOVELA') DEFAULT NULL,
    `autor` VARCHAR(100) DEFAULT NULL,
    `num_paginas` INT DEFAULT NULL,
    `cant_ejemplares` INT DEFAULT NULL,
    `cant_prestados` INT DEFAULT NULL,
    `cant_disponible` INT GENERATED ALWAYS AS (cant_ejemplares - cant_prestados) STORED,
    PRIMARY KEY (`idPublicacion`), 
    UNIQUE (`Titulo`)
) ENGINE=InnoDB;

-- Tabla `Prestamo`
CREATE TABLE IF NOT EXISTS `pinguinera`.`Prestamo` (
    `idPrestamo` INT NOT NULL AUTO_INCREMENT,
    `Fecha_prestamo` DATE NULL,
    `Fecha_devolucion` DATE NULL,
    `Estado` ENUM('SOLICITADO', 'REALIZADO', 'FINALIZADO') NOT NULL,
    `idUsuario` INT NOT NULL, 
    `idPublicacion` INT NOT NULL, 
    PRIMARY KEY (`idPrestamo`),
    INDEX `fk_idUsuario_idx` (`idUsuario` ASC), 
    INDEX `fk_idPublicacion_idx` (`idPublicacion` ASC), 
    CONSTRAINT `fk_idUsuario`
        FOREIGN KEY (`idUsuario`)
        REFERENCES `pinguinera`.`Usuario` (`idUsuario`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT `fk_idPublicacion`
        FOREIGN KEY (`idPublicacion`)
        REFERENCES `pinguinera`.`Publicacion` (`idPublicacion`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE=InnoDB;

-- Tabla `Area_genero`
CREATE TABLE IF NOT EXISTS `pinguinera`.`Area_genero` (
    `idPublicacion` INT NOT NULL, 
    `Area_genero` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`idPublicacion`, `Area_genero`),
    CONSTRAINT `fk_Area_genero_1`
        FOREIGN KEY (`idPublicacion`)
        REFERENCES `pinguinera`.`Publicacion` (`idPublicacion`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE=InnoDB;

-- Tabla `edad_sugerida`
CREATE TABLE IF NOT EXISTS `pinguinera`.`edad_sugerida` (
    `idPublicacion` INT NOT NULL, 
    `edad` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`idPublicacion`, `edad`),
    CONSTRAINT `fk_edad_sugerida_1`
        FOREIGN KEY (`idPublicacion`)
        REFERENCES `pinguinera`.`Publicacion` (`idPublicacion`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE=InnoDB;

-- Restablecimiento de la configuración
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
