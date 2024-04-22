-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema biblioteca_pinguinera_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema biblioteca_pinguinera_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `biblioteca_pinguinera_db` DEFAULT CHARACTER SET utf8 ;
USE `biblioteca_pinguinera_db` ;

-- -----------------------------------------------------
-- Table `biblioteca_pinguinera_db`.`Empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_pinguinera_db`.`Empleado` (
  `idEmpleado` VARCHAR(15) NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `contrasena` VARCHAR(45) NULL,
  `correo` VARCHAR(45) NULL,
  `rol` VARCHAR(45) NULL,
  PRIMARY KEY (`idEmpleado`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biblioteca_pinguinera_db`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_pinguinera_db`.`Usuario` (
  `correo` VARCHAR(100) NOT NULL,
  `nombre` VARCHAR(100) NULL,
  `contrasena` VARCHAR(100) NULL,
  PRIMARY KEY (`correo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biblioteca_pinguinera_db`.`Autor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_pinguinera_db`.`Autor` (
  `id` VARCHAR(15) NOT NULL,
  `nombre` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `biblioteca_pinguinera_db`.`Publicacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_pinguinera_db`.`Publicacion` (
  `titulo` VARCHAR(45) NOT NULL,
  `tipo_publicacion` VARCHAR(45) NULL,
  `id_autor` VARCHAR(15) NOT NULL,
  `num_paginas` INT NULL,
  `cant_ejemplares` INT NULL,
  `cant_prestados` INT NULL,
  `cant_disponible` INT NULL,
  PRIMARY KEY (`titulo`),
  INDEX `fk_autor_idx` (`id_autor` ASC) VISIBLE,
  CONSTRAINT `fk_autor`
    FOREIGN KEY (`id_autor`)
    REFERENCES `biblioteca_pinguinera_db`.`Autor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biblioteca_pinguinera_db`.`Prestamo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_pinguinera_db`.`Prestamo` (
  `id_prestamo` VARCHAR(15),
  `fecha_prestamo` DATE NULL,
  `fecha_devolucion` DATE NULL,
  `estado` VARCHAR(100) NULL,
  `correo_usuario` VARCHAR(100) NULL,
  `titulo_publicacion` VARCHAR(100) NULL,
  PRIMARY KEY (`id_prestamo`),
  INDEX `fk_correo_usuario_idx` (`correo_usuario` ASC) VISIBLE,
  INDEX `fk_titulo_publicacion_idx` (`titulo_publicacion` ASC) VISIBLE,
  CONSTRAINT `fk_correo_usuario`
    FOREIGN KEY (`correo_usuario`)
    REFERENCES `biblioteca_pinguinera_db`.`Usuario` (`correo`)
    ON DELETE CASCADE  -- Borrado en cascada
    ON UPDATE CASCADE, -- Actualización en cascada
  CONSTRAINT `fk_titulo_publicacion`
    FOREIGN KEY (`titulo_publicacion`)
    REFERENCES `biblioteca_pinguinera_db`.`Publicacion` (`titulo`)
    ON DELETE CASCADE  -- Borrado en cascada
    ON UPDATE CASCADE  -- Actualización en cascada
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biblioteca_pinguinera_db`.`Area_genero`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_pinguinera_db`.`Area_genero` (
  `titulo_publicacion` VARCHAR(45) NOT NULL,
  `area_generocol` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`titulo_publicacion`, `area_generocol`),
  CONSTRAINT `fk_area_genero_1`
    FOREIGN KEY (`titulo_publicacion`)
    REFERENCES `biblioteca_pinguinera_db`.`Publicacion` (`titulo`)
    ON DELETE CASCADE  -- Borrado en cascada
    ON UPDATE CASCADE  -- Actualización en cascada
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biblioteca_pinguinera_db`.`edad_sugerida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_pinguinera_db`.`edad_sugerida` (
  `titulo_publicacion` VARCHAR(45) NOT NULL,
  `edad` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`titulo_publicacion`, `edad`),
  CONSTRAINT `fk_edad_sugerida_1`
    FOREIGN KEY (`titulo_publicacion`)
    REFERENCES `biblioteca_pinguinera_db`.`Publicacion` (`titulo`)
    ON DELETE CASCADE  -- Borrado en cascada
    ON UPDATE CASCADE  -- Actualización en cascada
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `biblioteca_pinguinera_db`.`videograbacion` (
  `titulo` VARCHAR(100) NOT NULL,
  `sinopsis` TEXT NULL,
  `genero` VARCHAR(45) NULL,
  `autor` VARCHAR(100) NULL,
  `calificacion` INT NULL,
  `tipo` VARCHAR(45) NULL,
  `cantidad_copia` INT NOT NULL DEFAULT 0,
  `cantidad_prestado` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`titulo`)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `biblioteca_pinguinera_db`.`cancion` (
  `titulo` VARCHAR(100) NOT NULL,
  `genero` VARCHAR(45) NULL,
  `autor` VARCHAR(100) NULL,
  `letra` TEXT NULL,
  `fecha_lanzamiento` DATE NULL,
  `cantidad_copia` INT NOT NULL DEFAULT 0,
  `cantidad_prestado` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`titulo`)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `biblioteca_pinguinera_db`.`tesis` (
  `titulo` VARCHAR(100) NOT NULL,
  `fecha` DATE NULL,
  `autor` VARCHAR(100) NULL,
  `campo_estudio` VARCHAR(100) NULL,
  `pais` VARCHAR(100) NULL,
  `cantidad_copia` INT NOT NULL DEFAULT 0,
  `cantidad_prestado` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`titulo`)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `biblioteca_pinguinera_db`.`prestamocancion` (
  `id_prestamo_cancion` INT AUTO_INCREMENT,
  `id_prestamo` VARCHAR(15) NOT NULL,
  `id_cancion` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_prestamo_cancion`),
  INDEX `fk_id_prestamo_idx` (`id_prestamo` ASC) VISIBLE,
  INDEX `fk_id_cancion_idx` (`id_cancion` ASC) VISIBLE,
  CONSTRAINT `fk_id_prestamo_cancion`
    FOREIGN KEY (`id_prestamo`)
    REFERENCES `biblioteca_pinguinera_db`.`Prestamo` (`id_prestamo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_id_cancion_cancion`
    FOREIGN KEY (`id_cancion`)
    REFERENCES `biblioteca_pinguinera_db`.`cancion` (`titulo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `biblioteca_pinguinera_db`.`prestamovideoGrabacion` (
  `id_prestamo_videograbacion` INT AUTO_INCREMENT,
  `id_prestamo` VARCHAR(15) NOT NULL,
  `id_videograbacion` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_prestamo_videograbacion`),
  INDEX `fk_id_prestamo_videograbacion_idx` (`id_prestamo` ASC) VISIBLE,
  INDEX `fk_id_videograbacion_idx` (`id_videograbacion` ASC) VISIBLE,
  CONSTRAINT `fk_id_prestamo_videograbacion`
    FOREIGN KEY (`id_prestamo`)
    REFERENCES `biblioteca_pinguinera_db`.`Prestamo` (`id_prestamo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_id_videograbacion_videograbacion`
    FOREIGN KEY (`id_videograbacion`)
    REFERENCES `biblioteca_pinguinera_db`.`videograbacion` (`titulo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `biblioteca_pinguinera_db`.`prestamotesis` (
  `id_prestamo_tesis` INT AUTO_INCREMENT,
  `id_prestamo` VARCHAR(15) NOT NULL,
  `id_tesis` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_prestamo_tesis`),
  INDEX `fk_id_prestamo_tesis_idx` (`id_prestamo` ASC) VISIBLE,
  INDEX `fk_id_tesis_idx` (`id_tesis` ASC) VISIBLE,
  CONSTRAINT `fk_id_prestamo_tesis`
    FOREIGN KEY (`id_prestamo`)
    REFERENCES `biblioteca_pinguinera_db`.`Prestamo` (`id_prestamo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_id_tesis_tesis`
    FOREIGN KEY (`id_tesis`)
    REFERENCES `biblioteca_pinguinera_db`.`tesis` (`titulo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `biblioteca_pinguinera_db`.`registros_creados` (
  `id_registro` INT AUTO_INCREMENT,
  `fecha_creacion` DATETIME,
  `id_empleado_creador` VARCHAR(15) NOT NULL,
  `id_empleado_creado` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id_registro`),
  INDEX `fk_id_empleado_creador_idx` (`id_empleado_creador` ASC) VISIBLE,
  INDEX `fk_id_empleado_creado_idx` (`id_empleado_creado` ASC) VISIBLE,
  CONSTRAINT `fk_id_empleado_creador`
    FOREIGN KEY (`id_empleado_creador`)
    REFERENCES `biblioteca_pinguinera_db`.`Empleado` (`idEmpleado`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_id_empleado_creado`
    FOREIGN KEY (`id_empleado_creado`)
    REFERENCES `biblioteca_pinguinera_db`.`Empleado` (`idEmpleado`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;

DELIMITER //
CREATE TRIGGER actualizar_fecha_creacion
BEFORE INSERT ON registros_creados
FOR EACH ROW
BEGIN
    SET NEW.fecha_creacion = NOW();
END;
//
DELIMITER ;

CREATE TABLE IF NOT EXISTS `biblioteca_pinguinera_db`.`contacto_empleado` (
  `id_empleado` VARCHAR(15) NOT NULL,
  `numero` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id_empleado`),
  CONSTRAINT `fk_contacto_empleado_empleado`
    FOREIGN KEY (`id_empleado`)
    REFERENCES `biblioteca_pinguinera_db`.`Empleado` (`idEmpleado`)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `biblioteca_pinguinera_db`.`direccion_empleado` (
  `id_empleado` VARCHAR(15) NOT NULL,
  `direccion` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_empleado`),
  CONSTRAINT `fk_direccion_empleado_empleado`
    FOREIGN KEY (`id_empleado`)
    REFERENCES `biblioteca_pinguinera_db`.`Empleado` (`idEmpleado`)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `biblioteca_pinguinera_db`.`contacto_usuario` (
  `correo_usuario` VARCHAR(100) NOT NULL,
  `numero` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`correo_usuario`),
  CONSTRAINT `fk_contacto_usuario_usuario`
    FOREIGN KEY (`correo_usuario`)
    REFERENCES `biblioteca_pinguinera_db`.`Usuario` (`correo`)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `biblioteca_pinguinera_db`.`direccion_usuario` (
  `correo_usuario` VARCHAR(100) NOT NULL,
  `direccion` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`correo_usuario`),
  CONSTRAINT `fk_direccion_usuario_usuario`
    FOREIGN KEY (`correo_usuario`)
    REFERENCES `biblioteca_pinguinera_db`.`Usuario` (`correo`)
)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO Publicacion (titulo, tipo_publicacion, id_autor, cant_ejemplares, cant_prestados, cant_disponible)
VALUES
    ('Novela 1', 'Novela', '008bdba5-6', 100, 20, 80),
    ('Novela 2', 'Novela', 'f05c3a81-5', 120, 30, 90),
    ('Novela 3', 'Novela', '297ea64e-d',  80, 10, 70),
    ('Novela 4', 'Novela', 'a8011898-a',  150, 40, 110),
    ('Novela 5', 'Novela', '02c57d81-5',  200, 50, 150);

INSERT INTO Area_genero (titulo_publicacion, area_generocol)
VALUES
    ('Novela 1', 'Ficción'),
    ('Novela 2', 'Misterio'),
    ('Novela 3', 'Romance'),
    ('Novela 4', 'Aventura'),
    ('Novela 5', 'Fantasía');


INSERT INTO edad_sugerida (titulo_publicacion, edad)
VALUES
    ('Novela 1', '18'),
    ('Novela 2', '25'),
    ('Novela 3', '15'),
    ('Novela 4', '10'),
    ('Novela 5', '0');