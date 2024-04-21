CREATE TABLE `biblioteca-la-pinguinera-nueva`.`cancion` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `autor` VARCHAR(100) NULL,
  `cantidadDisponible` INT NOT NULL,
  `cantidadEjemplares` INT NOT NULL,
  `cantidadPrestado` INT NOT NULL,
  `Titulo` VARCHAR(255) NULL,
  `Artista` VARCHAR(45) NOT NULL,
  `Album` VARCHAR(45) NOT NULL,
  `fueBorrado` BIT(1) NULL,
  PRIMARY KEY (`id`));