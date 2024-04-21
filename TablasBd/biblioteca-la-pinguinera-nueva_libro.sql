-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: biblioteca-la-pinguinera-nueva
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `libro`
--

DROP TABLE IF EXISTS `libro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libro`
--

LOCK TABLES `libro` WRITE;
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
INSERT INTO `libro` VALUES (11,'gabo',10,20,10,'azul','literatura',80,_binary ''),(12,'Julio cervantes',20,30,10,'La noche fria','Cultura',68,_binary '\0'),(51,'Gernonimo martins',0,70,0,'El cruce pirata','Aventura',48,_binary '\0'),(52,'Dominic Toreto',31,54,23,'La averacion de pablo','Religion',56,_binary '\0'),(53,'democrates',20,40,20,'La pilatosa','filosofia',58,_binary '\0'),(55,'Jurgen kolp',75,85,10,'La era de hielo','Economia',68,_binary '\0'),(57,'nuevo autor',100,100,0,'nuevo titulo','nueva area conocimiento',45,_binary '\0'),(58,'Este es un nuevo autor',5,50,45,'llego la hora','sociedad',68,_binary '\0'),(60,'gabriel garcia marquez',5,40,35,'el coronel no tiene quien le escriba','aventura',79,_binary '\0'),(61,'dfgndfgs',22,56,34,'gdyghsefs','rsdthwesrag',67,_binary '\0');
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-21 16:05:23
