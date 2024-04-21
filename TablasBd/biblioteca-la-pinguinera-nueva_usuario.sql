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
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contrasena` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `rol` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (61,'nuevacontrania2024','usuarionuevo@gmail.com','usuarionuevo','LECTORES'),(62,'cambiodecontrasenia','juanpineda@correonuevo.com','Juan Pineda','LECTORES'),(63,'nuevocontrase','pedrohernandez@hotmail.com','Pedro Hernandez','LECTORES'),(64,'nuevaadmin','admin','admin','ADMINISTRADOR'),(65,'secambiocontrasena','nuevoasistente','martin','ASISTENTE'),(100,'contrasenamaestra','superusuario@gmail.com','El patron','SUPER_USUARIO'),(101,'estasicambio','pedrocapo@gmail.com','Pedro capo','ASISTENTE'),(102,'contranueva','correonuevo@hotmail.com','Buenaventura Coloso','ASISTENTE'),(103,'3243463','sfghsdfzhsd','ndfhafs','ASISTENTE'),(104,'nueva2024','zdfhsd','arh','ASISTENTE'),(105,'secambioesta','damianperdomo@gmail.com','Damian Perdomo','LECTORES'),(106,'10230233','pachofortunagmail.com','pacho fortuna','ASISTENTE'),(107,'esponjagart','patricioestrella@hotmail.com','patricio','ASISTENTE'),(108,'angela12345','angela@gmail.com','Angela suarez','ADMINISTRADOR'),(109,'2024nuevacontrasena','estecorreoesnuevo@gmail.com','soy nuevo','ASISTENTE'),(110,'juan202467','juanlaport@gmail.com','juan pedrosa','LECTORES'),(111,'secambioesta','asistente@correasistente.com','Ninibet Rosales','ASISTENTE'),(112,'jhonatan2024','jhonatan@gmail.com','Jhonatan Mangoste','ADMINISTRADOR'),(115,'gabriel2024','gabrieladmin@gmail.com','Gabriel Magalaes','ADMINISTRADOR'),(116,'*******correo.com','*******','****** ******','LECTORES'),(117,'********','','******','ASISTENTE'),(118,'1234','pedroQgmail.com','perdro capo','ASISTENTE'),(119,'*******','co*****123@correo.com','j*** p***','ASISTENTE'),(120,'********','*********','*******','ADMINISTRADOR'),(121,'2024','alfredo123@gmail.com','Alfredo Reyes','ASISTENTE'),(122,'346223','david@correo.com','david hernandez','ASISTENTE'),(123,'2024devon','devon@correo.com','devon alvarez','ADMINISTRADOR');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `registrar_usuario_administrador` AFTER INSERT ON `usuario` FOR EACH ROW BEGIN
    IF NEW.rol = 'ADMINISTRADOR' THEN
        INSERT INTO registros_creados (correo_usuario, contrasena, tipo_registro, fecha_creacion)
        VALUES (NEW.correo, NEW.contrasena, 'ADMINISTRADOR', NOW());
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-21 16:05:24
