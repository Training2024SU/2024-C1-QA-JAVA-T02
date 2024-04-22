-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: bibliotecapingui
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `publicacion`
--

DROP TABLE IF EXISTS `publicacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publicacion` (
  `titulo` varchar(100) NOT NULL,
  `autor` varchar(100) DEFAULT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `numPaginas` int DEFAULT NULL,
  `cantEjemplares` int DEFAULT NULL,
  `cantPrestados` int DEFAULT NULL,
  `cantDisponibles` int DEFAULT ((`cantEjemplares` - `cantPrestados`)),
  PRIMARY KEY (`titulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publicacion`
--

LOCK TABLES `publicacion` WRITE;
/*!40000 ALTER TABLE `publicacion` DISABLE KEYS */;
INSERT INTO `publicacion` VALUES ('A Confederacy of Dunces','Sr. Julio César Iglesias Carrasquillo','LIBRO',406,235,0,235),('A Farewell to Arms','Guillermo Anaya Fonseca','LIBRO',35,973,0,973),('cien','gabo','novela',322,100,20,80),('Clouds of Witness','Ramona Casares Urrutia','LIBRO',919,675,0,675),('das','sadasd','NOVELA',122,2,1,1),('el lazarillo','anonimo','NOVELA',50,200,20,180),('Far From the Madding Crowd','Isabela Regalado Zepeda','LIBRO',10,763,0,763),('Noli Me Tangere','Bernardo Castañeda Peralta','LIBRO',792,396,0,396),('satanas','mario mendoza','LIBRO',123,20,2,18),('The Little Foxes','Sta. Verónica Malave Rocha','LIBRO',65,443,0,443),('The Other Side of Silence','Sr. Hugo Rojas Rolón','LIBRO',418,283,0,283),('The Winds Twelve Quarters','Esperanza Iglesias Gómez','LIBRO',936,643,0,643),('wewwe','wewe','NOVELA',111,122,2,120);
/*!40000 ALTER TABLE `publicacion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-22  6:32:57
