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
-- Table structure for table `prestamo`
--

DROP TABLE IF EXISTS `prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestamo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `estadoPrestamo` varchar(255) DEFAULT NULL,
  `fechaDePrestamo` datetime(6) DEFAULT NULL,
  `material_id` bigint DEFAULT NULL,
  `usuario_id` bigint NOT NULL,
  `fechaDeEntrega` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbivngnhldmsxy0069wr5u9a06` (`usuario_id`),
  CONSTRAINT `FKbivngnhldmsxy0069wr5u9a06` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamo`
--

LOCK TABLES `prestamo` WRITE;
/*!40000 ALTER TABLE `prestamo` DISABLE KEYS */;
INSERT INTO `prestamo` VALUES (8,'REALIZADO','2024-04-18 11:03:54.801463',11,62,'2024-05-03 11:03:54.801463'),(9,'FINALIZADO','2024-04-19 00:05:20.407904',11,63,'2024-05-04 00:05:20.407904'),(10,'REALIZADO','2024-04-19 08:07:10.511341',1024,63,'2024-05-04 08:07:10.511847'),(11,'REALIZADO','2024-04-20 00:00:00.000000',55,63,NULL),(12,'REALIZADO','2024-04-19 08:15:12.138000',55,61,NULL),(13,'FINALIZADO',NULL,51,63,NULL),(14,'REALIZADO','2024-04-20 00:00:00.000000',51,105,'2024-05-05 00:00:00.000000'),(15,'REALIZADO','2024-04-20 00:00:00.000000',1024,61,NULL),(17,'REALIZADO','2024-04-20 00:00:00.000000',51,62,'2024-05-05 00:00:00.000000'),(18,'REALIZADO',NULL,53,61,'2024-05-05 10:21:50.186960'),(19,'FINALIZADO',NULL,12,62,NULL),(20,'FINALIZADO','2024-04-20 00:00:00.000000',53,105,'2024-05-05 10:41:44.952177'),(21,'REALIZADO','2024-04-21 00:00:00.000000',51,63,'2024-05-06 00:00:00.000000'),(22,'SOLICITADO','2024-04-20 00:00:00.000000',51,63,NULL),(24,'SOLICITADO',NULL,12,61,NULL),(25,'SOLICITADO',NULL,52,63,NULL),(26,'SOLICITADO',NULL,1024,63,NULL),(27,'SOLICITADO',NULL,1025,110,NULL),(28,'SOLICITADO',NULL,1025,62,NULL),(29,'REALIZADO','2024-04-21 00:00:00.000000',1525,110,'2024-05-06 00:00:00.000000'),(35,'REALIZADO',NULL,53,62,'2024-05-06 15:23:55.550088'),(36,'REALIZADO','2024-04-21 00:00:00.000000',51,63,'2024-05-06 00:00:00.000000');
/*!40000 ALTER TABLE `prestamo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-21 16:05:24
