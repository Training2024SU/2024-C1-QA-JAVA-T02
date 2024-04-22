-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: biblioteca_pinguinera_db
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
-- Table structure for table `area_genero`
--

DROP TABLE IF EXISTS `area_genero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `area_genero` (
  `titulo_publicacion` varchar(45) NOT NULL,
  `area_generocol` varchar(45) NOT NULL,
  PRIMARY KEY (`titulo_publicacion`,`area_generocol`),
  CONSTRAINT `fk_area_genero_1` FOREIGN KEY (`titulo_publicacion`) REFERENCES `publicacion` (`titulo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area_genero`
--

LOCK TABLES `area_genero` WRITE;
/*!40000 ALTER TABLE `area_genero` DISABLE KEYS */;
INSERT INTO `area_genero` VALUES ('libreblue','elarea'),('LibroDoc','nuevaarea'),('Libroprubaingreso','arealibro'),('librotemp','conocimiento'),('librotemp2','unevoconocimientonnn'),('Novela 1','Ficción'),('Novela 2','Misterio'),('Novela 3','Romance'),('Novela 4','Aventura'),('Novela 5','Fantasía'),('novela21','areaAliii'),('NovelaDoc','NuevoGenero'),('novelali','generoAli'),('TITULOUPDATE','areaCon');
/*!40000 ALTER TABLE `area_genero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `autor`
--

DROP TABLE IF EXISTS `autor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `autor` (
  `id` varchar(15) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autor`
--

LOCK TABLES `autor` WRITE;
/*!40000 ALTER TABLE `autor` DISABLE KEYS */;
INSERT INTO `autor` VALUES ('0015965a-f','Virgie McClure V'),('002acf14-e','Piedad Willms'),('008bdba5-6','Gustavo Lebsack'),('009fb087-6','Jutta Bartell'),('01c3704c-b','Jordan Nitzsche'),('01ceda6f-7','Ozie Rodriguez'),('02c57d81-5','Lamar Kuhic'),('04ead009-6','Gabriela Hoeger'),('052d4e0c-5','autornuevo'),('057cf164-f','Yu Swift'),('05957cd9-b','John Kunze'),('0663debc-c','Evelin Rippin'),('068eba6f-a','Lena Feeney I'),('079ce19d-6','Miss Ellis Hilpert'),('07e8d2ee-c','Jamaal Muller'),('08082bed-0','Harrison Ebert V'),('085b6f2c-3','Karol Reinger'),('0a5f5681-5','Mitsuko Torp'),('0b95c062-0','Milda McCullough'),('0ba2eb77-8','Francisco Stanton'),('0bd5cf2e-4','Shaunda Smith'),('0e6bdd3c-2','Guillermina Barton Sr.'),('0f4c99f4-5','Mrs. Adelle Berge'),('0f8330ad-2','Ida Prohaska'),('10a19bb9-a','Cinderella Johnston'),('12d7f4c0-5','Mrs. Joan Bradtke'),('1363468b-4','Mr. Dana Hirthe'),('1397d8d4-8','Galen Hirthe'),('146b3d6a-9','Clint Jerde DDS'),('149c053b-0','Dr. Jewel Hettinger'),('14a8872b-2','Melany Bauch'),('14ca5e9e-2','Eula Altenwerth'),('1551206b-a','Cesar Nitzsche'),('157c07fd-9','Ms. Gilberto Stracke'),('15f6cedf-e','Staci Monahan PhD'),('162eb131-6','Lita Moen'),('1674ccb6-d','Dr. Elvin Hilll'),('189a77e7-9','Louis Lynch Jr.'),('18b40bdb-0','Mrs. Tonya Kessler'),('1ab67bbf-1','Lucy Reichert'),('1e97689a-6','Lorrine Kilback'),('2061a3ed-e','Allan Kuhn'),('21bc76b9-0','Mrs. Von Lind'),('22856e02-f','Dr. Maria Jaskolski'),('2337b258-5','Jonelle Buckridge V'),('267e7451-1','Larhonda Ondricka II'),('27400bab-2','Suzanne Schowalter'),('27733e9f-6','Margarito Medhurst'),('284c43e4-b','Evelin Von DDS'),('297ea64e-d','Benny Stark'),('298dc4c4-8','Maxwell Torphy DDS'),('2a0a37c9-7','Susy Hoeger'),('2ad5ca60-7','Verona Orn'),('2ba03df5-c','Whitney Kuhlman'),('2c845555-6','Jutta Legros'),('2cc04755-3','Lissa Willms'),('2e00e65e-6','Marry Goodwin PhD'),('2e07b34f-d','Marquita Smitham II'),('2ea22321-c','Neal Hermiston'),('2efd4d37-2','Mrs. Augustus Gleichner'),('2fd5e7f5-e','Drew Mueller'),('304ff94f-c','Enoch Kunde'),('3071a1b7-5','Mr. Wonda Wunsch'),('30e75290-0','Ira Becker'),('31db5c7c-e','Monnie Hahn'),('322edfa3-9','Nathaniel Schumm'),('3299e4ad-3','Buddy Rath'),('3405ecbb-6','Theola Zboncak'),('345ab60a-6','Miss Carman Bartell'),('34a6b9c0-2','Serena Medhurst'),('34e2babb-a','Sung Kris V'),('352acf1b-3','Cordell Jast III'),('35957282-1','Miss Hong Skiles'),('3704d635-b','Keven Barrows III'),('372b5527-b','Ms. Kendal Nitzsche'),('374a4ea7-8','Roselle Kunze II'),('37790473-3','Anna Schumm'),('37d03605-4','Luke Wiegand V'),('38514fe3-a','Carrol Bednar'),('38871df3-7','Randal Wiza'),('38e3ad4c-7','Miss Arden Schultz'),('3be9dc5c-a','Miss Delbert Pfannerstill'),('3c081dcd-6','Tyrell Reichert'),('3d909fa7-d','Joseph Wintheiser'),('3da3ee1c-9','Jeffery Emmerich'),('3dd545a3-a','Loida Heaney'),('3de8521c-b','Connie Thompson'),('3e047639-9','Jarod Okuneva'),('3e7f519e-5','Mrs. Murray Parisian'),('3ec27a43-5','Cathryn Huels'),('3f538737-6','Laverne Marvin Jr.'),('40dc03f9-9','Mrs. Alec Lebsack'),('41782c2b-8','Stanton Bernhard'),('41c94566-4','Jeannetta Armstrong'),('439f23f2-1','Kacy Haag'),('44f66102-f','Dr. Janeen Green'),('45c910df-5','Thao Rau'),('47e98027-3','Buster Rohan'),('495166bb-f','Lane Haley'),('49d0a8f8-a','Clayton Schroeder'),('4a1640a8-4','Santos Kemmer'),('4af118c4-2','Rickey Vandervort DDS'),('4b11be72-c','Luciano Steuber'),('4d026685-1','94e4c118-b'),('4dc07381-f','Margart Kassulke'),('4e1bc999-7','Giovanni Abshire'),('4f15fa28-b','Foster Jakubowski'),('4fcb8266-c','Miquel Ebert DVM'),('504bd6c8-8','Kathlene Gaylord'),('516292f8-d','Mozelle Quigley'),('5190f199-c','Angelyn Schmidt'),('52053f33-c','Merrie Crist'),('5230499e-f','Felecia Runolfsdottir'),('525c6ff9-8','Thurman Botsford'),('529c19b3-a','Fredrick Leannon II'),('53c6b660-f','Wilbur Koss'),('53e059df-b','Salvatore Ryan'),('53fd3749-2','Jed Weissnat'),('55ec1827-c','Ms. Delbert Torp'),('561af519-a','Lane Pacocha'),('56c8cece-b','Mr. Reid Jacobson'),('5769302d-7','Graig Kub'),('5859dbc8-f','Chas Hagenes Sr.'),('59d577e6-2','autor'),('59e3d7fc-c','Arlie Champlin'),('59fc657c-0','Jasmin Zieme'),('5a467984-6','Herman Stiedemann'),('5a554e42-5','Aurelio Murray'),('5aef95b7-0','Earline Collier II'),('5af50090-e','Deshawn Carroll'),('5c036afe-9','Lisbeth Waters'),('5e6c8f68-3','Dr. Jonas Reinger'),('5fe96d5b-9','Arnoldo Brakus IV'),('6057ef4a-f','Tonisha Sauer IV'),('60707702-e','Catalina Harris'),('60840def-a','Andrea Fisher'),('60972232-e','Griselda Harris'),('61982354-0','Ivory Rosenbaum'),('62af4be3-c','Mack Gottlieb'),('63f49ee7-6','Robbie Kutch III'),('6400235c-2','Dorthea Von DVM'),('646b4803-0','Noelia Shanahan'),('6517a47e-4','Ms. Andrea Goodwin'),('659e5ed6-a','Ms. Hyman Lind'),('65e2d792-8','Marlin Terry'),('6609a494-a','Henrietta Hickle II'),('6675a9c6-1','Bernardine Buckridge PhD'),('6699a73c-3','Ida Schinner'),('66eaa8b2-7','Kenisha Romaguera V'),('6867856b-d','Dr. Toi Roob'),('68b3d8c9-a','Deandre Kilback'),('68bf8150-f','Lita Weissnat'),('6a74126f-5','Vita Muller I'),('6a75da20-3','Estefana Hessel'),('6bde6166-3','Divina McLaughlin'),('6c70da61-9','Miss Candyce Dickinson'),('6c8d578e-c','Melinda Emmerich'),('6e94fa06-e','Teresia Yundt III'),('6fb71cec-2','Camie Runolfsdottir'),('6fefebc7-e','Owen Schultz'),('711330c9-5','Olen Rolfson'),('7152ac6b-7','Omer Mayer DDS'),('7185c4e5-b','Mr. Aracelis Buckridge'),('71be1342-1','Haydee Murphy'),('71e44f57-2','Zenia Nolan'),('71fed81c-6','Jack Borer'),('737ed358-1','Librada Batz'),('7659a295-9','Tameka Kovacek'),('7691b3ab-9','Gillian Streich'),('7a04c48e-3','Ethan Orn II'),('7a7ae363-4','Emmanuel Morar'),('7ab3a5c5-d','Inge Will Sr.'),('7bba7bf4-6','Violeta Trantow'),('7bff3e37-7','Dr. Roseanne Daniel'),('7c1c4bd3-e','Laurette Daniel I'),('7c20666a-c','Lawerence Schultz'),('7c5cafdd-c','Mikel Gusikowski I'),('7c7a56a9-d','Mr. Alpha Ernser'),('7d256fc0-1','Bret Romaguera'),('7dfebc8d-7','Emanuel Macejkovic II'),('7e870b89-2','Makeda Nienow'),('7ed3ad93-4','Meghann Gusikowski IV'),('7f1af729-0','Antonette West'),('7fa5279f-c','Cody Sanford'),('7fb5d818-7','Maira Dooley'),('8046ae2f-f','Annabel McDermott'),('80c7c68c-2','Von Grant Jr.'),('813849da-c','Dr. Rosalba Leffler'),('81c3a581-e','Krissy Smitham'),('82b86596-f','Millie Kilback'),('83b2df67-9','Alycia Stracke'),('83e6e524-5','Anderson Reichel'),('8416ebc7-a','Hung Doyle'),('841ddf4b-8','Norman Schamberger'),('84c4d6d4-0','Thad Ruecker'),('859e0b7b-e','Ms. Vanesa Heaney'),('85a465be-3','Houston Zboncak'),('878888c7-9','Davida Graham'),('8947a64a-f','Raina Schuster'),('8985794b-8','Dr. Neva Berge'),('89e4d04f-e','Everett Shields'),('8a0fc896-9','Blair Flatley'),('8a6cbbbf-1','Abe Bailey'),('8a74b4e3-b','Mr. Toshia Pouros'),('8c3bb2d4-c','Inga Will'),('8c5e630f-b','Geoffrey Grady II'),('8dd3ca0e-1','Alan Schmitt II'),('8ede1dad-4','Karima Blick'),('8effe754-0','Frances Reinger'),('8f65b880-8','Domonique Wolf'),('8fee3d8f-9','Reuben Lind'),('90f1414f-3','Miss Rayford Connelly'),('913d006a-0','Lidia Hodkiewicz'),('9383eb69-5','Ms. Celinda Fadel'),('941eb070-4','Les Kreiger'),('95586668-9','Franklyn Roberts'),('957112ea-5','Mae Metz'),('95cfdccd-5','Alexander Torphy'),('96c13b6e-b','Sherita Wyman'),('96faf6ab-f','Dr. Oma Abbott'),('97b69937-7','Dahlia Abbott'),('97f3ed28-e','Stephany Hermiston PhD'),('981839a9-c','Hana Breitenberg'),('9940df8d-5','Mervin Hayes'),('99ce2f65-6','Janine Walter'),('9b7b76ed-7','Rosina Rippin'),('9bb4118f-5','Song Johnson'),('9bfd9387-c','Jame Satterfield'),('9d8f79f1-8','Brant Mraz'),('9da522ca-9','Emmett Lindgren'),('9e3df1e0-b','Ms. Bettina Corwin'),('9ea2cd56-9','Burl Blick'),('9ed5f08a-5','Eusebia Glover'),('9f433b77-4','Jesusita Schroeder'),('a0daae1e-c','Ike Haley'),('a0f80af3-0','Leila Fritsch'),('a1526c74-0','Mr. Robin Huel'),('a232f8a9-a','Kimberli Jakubowski'),('a36a1c9f-e','Nathanial Hettinger III'),('a8011898-a','Jermaine Deckow'),('a8427df2-5','Elene Friesen'),('a94224a9-8','Julian Kerluke'),('a95a6bdb-7','Mr. Cody Hand'),('aa6e553c-9','Garret Ernser I'),('ab0ee77a-7','Amanda Bayer'),('abc7c01f-d','Inga Kutch'),('ac056c72-e','Buford Hayes'),('ad10997f-f','Miss Dewayne Gutmann'),('ad8da3a3-5','Celia Stamm'),('adab1ad9-3','Elton Flatley'),('adc11ba4-d','Rubie Smith'),('ae278a8f-5','Marlon Mayert'),('af10edb1-9','Lawrence Lakin'),('afe9c872-c','Sage McCullough'),('b1b27bfe-0','Glenn Wolff'),('b1c60913-1','Bulah Marvin'),('b1fdeaff-1','Crista Kuhic'),('b250d0a4-2','Selena Howe'),('b2cc56e1-2','Andre Waelchi'),('b31addd4-3','Mr. Wilfred Watsica'),('b3981ce3-c','Odis Koelpin'),('b3bf5501-7','Lillia Lebsack'),('b5885e95-2','Mirna Price'),('b5967808-3','Shu Keebler'),('b5dfe8d4-2','Ms. Mary Grady'),('b64752d3-8','Cory Hammes'),('b67870b0-5','Nicholas Hickle'),('b6a04453-f','Burt Schultz Jr.'),('b6a8d71a-5','Marcos Schowalter'),('b7374e75-1','Mr. Emerita Beatty'),('b75b3534-9','Ming Turner'),('b8706677-c','Tamisha Grady'),('b8a1afed-1','Walker Breitenberg'),('b8f5ea8b-d','Jermaine Hettinger'),('ba4c0051-a','Mr. Jule Schulist'),('baed3dac-2','Dr. Chi Dietrich'),('bd3fd557-d','Johnathon Keeling MD'),('bdbd580c-9','Sallie Terry'),('be091913-3','Herschel McCullough'),('beaa69e1-e','Ms. Nicolas Hauck'),('beb039b8-a','Madonna Kuhic'),('bf1c1c16-4','Dwight Champlin'),('c0fcf8ca-7','Spencer Becker'),('c18b6186-f','Ms. Heath Nikolaus'),('c3d10dda-9','Karen Bogan'),('c3efd395-4','Thaddeus Friesen'),('c4101489-b','Ms. Leanora Labadie'),('c4b5e2f3-a','Carl Leannon'),('c54e9c1c-1','Gilma Beer'),('c575d372-9','Ta Klein'),('c5b6f763-5','Teddy Green'),('c670be05-5','Kasi Erdman'),('c6f260e5-e','Dr. Suanne Ullrich'),('c8121207-7','Treva Miller'),('c84569d3-3','Angeles Bashirian'),('c8bba56a-1','Latasha Jacobi'),('c959e7df-b','Robin Tromp'),('c9c5c0fe-8','Les Bashirian'),('cab36ac5-a','Iris Aufderhar'),('cb0b545f-c','Florida Zieme'),('cb6e35a3-2','Josef Monahan'),('cb78ca5d-5','Kenya Hyatt'),('cbf6e791-d','Bradley Ziemann'),('cc10e68f-3','Leonardo Ankunding MD'),('cc5a2538-e','Suellen Balistreri DVM'),('ccc1d4fb-e','Miss Antoinette Morar'),('ce17176c-b','Mr. Adolph Haley'),('ce3198fd-7','Tawna Kuhlman'),('ce7018e3-b','Bettye Wiza'),('ce9bdd72-e','Miss Elwood Zulauf'),('cf5ad280-c','Everette Barton V'),('d023f78f-6','Carmen Mann'),('d110a0e9-1','Roxy Kessler'),('d166f954-c','Miguel Rutherford'),('d1bada34-c','Alfonzo Walsh'),('d1fe840e-7','Kum Grimes'),('d21d9b3d-5','Korey Stanton'),('d2b848e7-2','Brenda Metz'),('d3072ef6-9','Terisa Herzog Jr.'),('d4f99e22-d','Angie Hudson'),('d572d57c-8','Orlando Gutmann'),('d66ff5c4-0','Glenna Bode'),('d6a4ba2a-2','Gena Pouros'),('d7a2a4b2-6','Robert Purdy'),('d942b056-0','Irwin VonRueden'),('dac4496c-5','Zack Rice MD'),('dc0cd64b-2','Josef Medhurst'),('dc2855d2-8','Clay Rodriguez'),('dd24869b-b','Leonel Nicolas'),('dd559f5d-f','Roberto Stanton Jr.'),('de1e4ac9-f','Melvin Willms'),('de861c43-8','Margarito Franecki'),('de9c7a18-4','Connie Raynor DDS'),('deb36e13-9','Samantha Bode'),('df6533ef-2','Gregorio Willms'),('df853b7a-e','Carley Hettinger'),('dfb1431a-d','Lanora Swift Sr.'),('dfbfffdb-2','Kristian Weber MD'),('e0819c5b-8','Ross Veum'),('e083df9b-5','Keila Adams'),('e0d827ec-6','Dylan Pfannerstill'),('e10a3c82-2','Long Huels'),('e148f0bc-0','Brittanie Roberts'),('e393d86d-c','Dr. Edward Schmitt'),('e64b289b-0','Glen Conroy'),('e64b7c5a-1','Phil Dach'),('e8207375-2','Leora Rodriguez III'),('e845ecc6-2','Sherman Nitzsche'),('e9bf494a-3','Henry Homenick'),('ea307aa2-7','Vernice Huels'),('eb212edd-1','Sharee Shanahan'),('ebee2602-d','Julian Gislason'),('eddaf857-6','Harry Brakus'),('ee311a52-1','Eldon Morissette'),('ee847650-c','Donnie Effertz'),('ef20d917-9','Laverne Pacocha'),('ef574f52-c','Nikki Maggio'),('efa0dc1a-3','Donn Rolfson'),('f05239d1-b','Felipe Schuppe'),('f05c3a81-5','Jeremiah Gleichner'),('f0c32d1e-e','Colin Bashirian'),('f3cd9b9a-7','Maryam Williamson'),('f47d22dd-2','Delmer Cole DVM'),('f489e340-e','Daryl Paucek'),('f62cff4a-9','Elton Okuneva DDS'),('f6945e4e-a','Micaela Kerluke I'),('f974098f-4','Larraine Koelpin'),('f9e62170-a','Philomena Torphy'),('fa17889e-4','Fiona Kunde'),('fa1ad1ea-4','Joanne Hane'),('fa75f3fc-1','Jarred Yost'),('fa8b790f-8','Mr. Chang Corwin'),('fb2901b3-8','Mr. Santo Fisher'),('fba865b3-8','Stacee Funk Sr.'),('fc0a9924-5','Barney Leuschke'),('fc1ba745-e','Mrs. Miquel Toy'),('fc4640ab-3','Demarcus Dicki'),('fd139303-1','Dylan Reichert'),('fd9062ab-7','Ahmed Gleason'),('ff06afea-0','Deedee Berge Sr.'),('ff390f79-5','Jasper Yundt');
/*!40000 ALTER TABLE `autor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cancion`
--

DROP TABLE IF EXISTS `cancion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cancion` (
  `titulo` varchar(100) NOT NULL,
  `genero` varchar(45) DEFAULT NULL,
  `autor` varchar(100) DEFAULT NULL,
  `letra` text,
  `fecha_lanzamiento` date DEFAULT NULL,
  `cantidad_copia` int NOT NULL DEFAULT '0',
  `cantidad_prestado` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`titulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cancion`
--

LOCK TABLES `cancion` WRITE;
/*!40000 ALTER TABLE `cancion` DISABLE KEYS */;
INSERT INTO `cancion` VALUES ('Cancion 10','Hip Hop','Autor 10','Letra de la Cancion 10','2024-04-28',110,30),('Cancion 11','Pop','Autor 1','Letra de la Cancion 1','2024-04-19',100,20),('Cancion 2','Rock','Autor 2','Letra de la Cancion 2','2024-04-20',150,30),('Cancion 3','Reggaeton','Autor 3','Letra de la Cancion 3','2024-04-21',80,10),('Cancion 4','Electrónica','Autor 4','Letra de la Cancion 4','2024-04-22',200,40),('Cancion 5','Hip Hop','Autor 5','Letra de la Cancion 5','2024-04-23',120,20),('Cancion 6','Pop','Autor 6','Letra de la Cancion 6','2024-04-24',90,5),('Cancion 7','Rock','Autor 7','Letra de la Cancion 7','2024-04-25',180,15),('Cancion 8','Reggaeton','Autor 8','Letra de la Cancion 8','2024-04-26',70,2),('Cancion 9','Electrónica','Autor 9','Letra de la Cancion 9','2024-04-27',160,20),('Cancion A','Pop','Autor X','Letra de la Cancion A','2024-04-30',80,15),('Cancion B','Rock','Autor Y','Letra de la Cancion B','2024-05-01',120,25),('Cancion C','Reggaeton','Autor Z','Letra de la Cancion C','2024-05-02',90,10),('Cancion D','Electrónica','Autor W','Letra de la Cancion D','2024-05-03',150,30),('Cancion E','Hip Hop','Autor V','Letra de la Cancion E','2024-05-04',110,20),('Cancion F','Pop','Autor U','Letra de la Cancion F','2024-05-05',100,5),('Cancion G','Rock','Autor T','Letra de la Cancion G','2024-05-06',130,10),('Cancion H','Reggaeton','Autor S','Letra de la Cancion H','2024-05-07',85,5),('Cancion I','Electrónica','Autor R','Letra de la Cancion I','2024-05-08',140,25),('Cancion J','Hip Hop','Autor Q','Letra de la Cancion J','2024-05-09',95,15),('CANCIONPRUEBA','GENEROPRUEBA','AUTORPRUEBA','LETRA...','2024-04-03',15,0);
/*!40000 ALTER TABLE `cancion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contacto_empleado`
--

DROP TABLE IF EXISTS `contacto_empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contacto_empleado` (
  `id_empleado` varchar(15) NOT NULL,
  `numero` varchar(15) NOT NULL,
  PRIMARY KEY (`id_empleado`),
  CONSTRAINT `fk_contacto_empleado_empleado` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`idEmpleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contacto_empleado`
--

LOCK TABLES `contacto_empleado` WRITE;
/*!40000 ALTER TABLE `contacto_empleado` DISABLE KEYS */;
INSERT INTO `contacto_empleado` VALUES ('9ed71838-5','987654');
/*!40000 ALTER TABLE `contacto_empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contacto_usuario`
--

DROP TABLE IF EXISTS `contacto_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contacto_usuario` (
  `correo_usuario` varchar(100) NOT NULL,
  `numero` varchar(15) NOT NULL,
  PRIMARY KEY (`correo_usuario`),
  CONSTRAINT `fk_contacto_usuario_usuario` FOREIGN KEY (`correo_usuario`) REFERENCES `usuario` (`correo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contacto_usuario`
--

LOCK TABLES `contacto_usuario` WRITE;
/*!40000 ALTER TABLE `contacto_usuario` DISABLE KEYS */;
INSERT INTO `contacto_usuario` VALUES ('user3','78910'),('user4','987654'),('user5','123456789');
/*!40000 ALTER TABLE `contacto_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direccion_empleado`
--

DROP TABLE IF EXISTS `direccion_empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `direccion_empleado` (
  `id_empleado` varchar(15) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  PRIMARY KEY (`id_empleado`),
  CONSTRAINT `fk_direccion_empleado_empleado` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`idEmpleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direccion_empleado`
--

LOCK TABLES `direccion_empleado` WRITE;
/*!40000 ALTER TABLE `direccion_empleado` DISABLE KEYS */;
INSERT INTO `direccion_empleado` VALUES ('9ed71838-5','asysdireccion');
/*!40000 ALTER TABLE `direccion_empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direccion_usuario`
--

DROP TABLE IF EXISTS `direccion_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `direccion_usuario` (
  `correo_usuario` varchar(100) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  PRIMARY KEY (`correo_usuario`),
  CONSTRAINT `fk_direccion_usuario_usuario` FOREIGN KEY (`correo_usuario`) REFERENCES `usuario` (`correo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direccion_usuario`
--

LOCK TABLES `direccion_usuario` WRITE;
/*!40000 ALTER TABLE `direccion_usuario` DISABLE KEYS */;
INSERT INTO `direccion_usuario` VALUES ('user3','direccion'),('user4','nuevadirecionuser4'),('user5','nuevadireccion');
/*!40000 ALTER TABLE `direccion_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edad_sugerida`
--

DROP TABLE IF EXISTS `edad_sugerida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edad_sugerida` (
  `titulo_publicacion` varchar(45) NOT NULL,
  `edad` varchar(45) NOT NULL,
  PRIMARY KEY (`titulo_publicacion`,`edad`),
  CONSTRAINT `fk_edad_sugerida_1` FOREIGN KEY (`titulo_publicacion`) REFERENCES `publicacion` (`titulo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edad_sugerida`
--

LOCK TABLES `edad_sugerida` WRITE;
/*!40000 ALTER TABLE `edad_sugerida` DISABLE KEYS */;
INSERT INTO `edad_sugerida` VALUES ('Novela 1','18'),('Novela 2','25'),('Novela 3','15'),('Novela 4','10'),('Novela 5','0'),('novela21','14'),('NovelaDoc','14'),('novelali','14');
/*!40000 ALTER TABLE `edad_sugerida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
  `idEmpleado` varchar(15) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `contrasena` varchar(45) DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `rol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idEmpleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES ('18a458e9-9','ASYSTENTETEMP9','123','ASYSENTETEMP9','ASISTENTE'),('23b07086-5','asistentetemp5','123','asistentetemp5','ASISTENTE'),('464a6e44-a','asistenteProp','123','asistenteProp','ASISTENTE'),('529da966-3','administrador','123','administrador','ADMINISTRADOR'),('64778db0-3','propietario','123','propietario','ADMINISTRADOR'),('6727d2b3-a','goiewrghoie','er','ger','ASISTENTE'),('73dfe6ec-6','propietarioD','123','propietarioD','PROPIETARIO'),('7a079594-4','propietario','123','123','SUPERADMIN'),('881858dc-9','admin','123','admin','ADMINISTRADOR'),('90738e86-c','asistonto','123','asiston','ASISTENTE'),('907bcca8-0','propel','123','propel','ASISTENTE'),('957edceb-1','elasis3','123','elasis3','ASISTENTE'),('9ed71838-5','asys','nuevac','asys','ASISTENTE'),('admin','John Doe','contrasenasegura123456','administrador@pingu.com.co','SUPERADMIN'),('b5d10486-7','asis','123','123','ASISTENTE'),('b6cc5421-0','elpro','123','elpro','PROPIETARIO'),('bd2992fd-6','asistenteTemporal','123','asistente@temp','ASISTENTE'),('c415e626-5','asistemp','123','asistemp','ASISTENTE'),('e0bdf833-6','cweccsd','ds','sd','ASISTENTE');
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamo`
--

DROP TABLE IF EXISTS `prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestamo` (
  `id_prestamo` varchar(15) NOT NULL,
  `fecha_prestamo` date DEFAULT NULL,
  `fecha_devolucion` date DEFAULT NULL,
  `estado` varchar(100) DEFAULT NULL,
  `correo_usuario` varchar(100) DEFAULT NULL,
  `titulo_publicacion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_prestamo`),
  KEY `fk_correo_usuario_idx` (`correo_usuario`),
  KEY `fk_titulo_publicacion_idx` (`titulo_publicacion`),
  CONSTRAINT `fk_correo_usuario` FOREIGN KEY (`correo_usuario`) REFERENCES `usuario` (`correo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_titulo_publicacion` FOREIGN KEY (`titulo_publicacion`) REFERENCES `publicacion` (`titulo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamo`
--

LOCK TABLES `prestamo` WRITE;
/*!40000 ALTER TABLE `prestamo` DISABLE KEYS */;
INSERT INTO `prestamo` VALUES ('4d8292e7-c','2024-04-16','2024-04-27','FINALIZADO','user5','TITULOUPDATE'),('5e165724-2','2024-04-18','2024-04-27','SOLICITADO','user','libreblue'),('6b789f14-d','2024-04-22','2024-05-01','FINALIZADO','user3',NULL),('854d8e0a-1','2024-04-18','2024-04-29','FINALIZADO','user5','novela21'),('b41aba67-8','2024-04-16','2024-04-26','SOLICITADO','user5','TITULOUPDATE'),('c38a60ed-d','2024-04-21','2024-04-30','SOLICITADO','user4','libreblue');
/*!40000 ALTER TABLE `prestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamocancion`
--

DROP TABLE IF EXISTS `prestamocancion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestamocancion` (
  `id_prestamo_cancion` int NOT NULL AUTO_INCREMENT,
  `id_prestamo` varchar(15) NOT NULL,
  `id_cancion` varchar(100) NOT NULL,
  PRIMARY KEY (`id_prestamo_cancion`),
  KEY `fk_id_prestamo_idx` (`id_prestamo`),
  KEY `fk_id_cancion_idx` (`id_cancion`),
  CONSTRAINT `fk_id_cancion_cancion` FOREIGN KEY (`id_cancion`) REFERENCES `cancion` (`titulo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_id_prestamo_cancion` FOREIGN KEY (`id_prestamo`) REFERENCES `prestamo` (`id_prestamo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamocancion`
--

LOCK TABLES `prestamocancion` WRITE;
/*!40000 ALTER TABLE `prestamocancion` DISABLE KEYS */;
/*!40000 ALTER TABLE `prestamocancion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamotesis`
--

DROP TABLE IF EXISTS `prestamotesis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestamotesis` (
  `id_prestamo_tesis` int NOT NULL AUTO_INCREMENT,
  `id_prestamo` varchar(15) NOT NULL,
  `id_tesis` varchar(100) NOT NULL,
  PRIMARY KEY (`id_prestamo_tesis`),
  KEY `fk_id_prestamo_tesis_idx` (`id_prestamo`),
  KEY `fk_id_tesis_idx` (`id_tesis`),
  CONSTRAINT `fk_id_prestamo_tesis` FOREIGN KEY (`id_prestamo`) REFERENCES `prestamo` (`id_prestamo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_id_tesis_tesis` FOREIGN KEY (`id_tesis`) REFERENCES `tesis` (`titulo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamotesis`
--

LOCK TABLES `prestamotesis` WRITE;
/*!40000 ALTER TABLE `prestamotesis` DISABLE KEYS */;
INSERT INTO `prestamotesis` VALUES (4,'6b789f14-d','Nombre de la Tesis 1');
/*!40000 ALTER TABLE `prestamotesis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamovideograbacion`
--

DROP TABLE IF EXISTS `prestamovideograbacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestamovideograbacion` (
  `id_prestamo_videograbacion` int NOT NULL AUTO_INCREMENT,
  `id_prestamo` varchar(15) NOT NULL,
  `id_videograbacion` varchar(100) NOT NULL,
  PRIMARY KEY (`id_prestamo_videograbacion`),
  KEY `fk_id_prestamo_videograbacion_idx` (`id_prestamo`),
  KEY `fk_id_videograbacion_idx` (`id_videograbacion`),
  CONSTRAINT `fk_id_prestamo_videograbacion` FOREIGN KEY (`id_prestamo`) REFERENCES `prestamo` (`id_prestamo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_id_videograbacion_videograbacion` FOREIGN KEY (`id_videograbacion`) REFERENCES `videograbacion` (`titulo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamovideograbacion`
--

LOCK TABLES `prestamovideograbacion` WRITE;
/*!40000 ALTER TABLE `prestamovideograbacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `prestamovideograbacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publicacion`
--

DROP TABLE IF EXISTS `publicacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publicacion` (
  `titulo` varchar(45) NOT NULL,
  `tipo_publicacion` varchar(45) DEFAULT NULL,
  `id_autor` varchar(15) NOT NULL,
  `num_paginas` int DEFAULT NULL,
  `cant_ejemplares` int DEFAULT NULL,
  `cant_prestados` int DEFAULT NULL,
  `cant_disponible` int DEFAULT NULL,
  PRIMARY KEY (`titulo`),
  KEY `fk_autor_idx` (`id_autor`),
  CONSTRAINT `fk_autor` FOREIGN KEY (`id_autor`) REFERENCES `autor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publicacion`
--

LOCK TABLES `publicacion` WRITE;
/*!40000 ALTER TABLE `publicacion` DISABLE KEYS */;
INSERT INTO `publicacion` VALUES ('libreblue','Libro','02c57d81-5',20,15,9,6),('LibroDoc','Libro','65e2d792-8',200,15,0,15),('Libroprubaingreso','Libro','008bdba5-6',204,50,0,50),('librotemp','Libro','65e2d792-8',200,15,0,15),('librotemp2','Libro','65e2d792-8',200,15,1,14),('Novela 1','Novela','008bdba5-6',NULL,100,20,80),('Novela 2','Novela','02c57d81-5',NULL,120,30,90),('Novela 3','Novela','297ea64e-d',NULL,80,10,70),('Novela 4','Novela','a8011898-a',NULL,150,40,110),('Novela 5','Novela','02c57d81-5',NULL,200,50,150),('novela21','Libro','162eb131-6',200,14,2,12),('NovelaDoc','Novela','a0daae1e-c',0,15,0,15),('novelali','Novela','162eb131-6',0,15,4,11),('TITULOUPDATE','Libro','d21d9b3d-5',200,20,5,15);
/*!40000 ALTER TABLE `publicacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registros_creados`
--

DROP TABLE IF EXISTS `registros_creados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registros_creados` (
  `id_registro` int NOT NULL AUTO_INCREMENT,
  `fecha_creacion` datetime DEFAULT NULL,
  `id_empleado_creador` varchar(15) NOT NULL,
  `id_empleado_creado` varchar(15) NOT NULL,
  PRIMARY KEY (`id_registro`),
  KEY `fk_id_empleado_creador_idx` (`id_empleado_creador`),
  KEY `fk_id_empleado_creado_idx` (`id_empleado_creado`),
  CONSTRAINT `fk_id_empleado_creado` FOREIGN KEY (`id_empleado_creado`) REFERENCES `empleado` (`idEmpleado`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_id_empleado_creador` FOREIGN KEY (`id_empleado_creador`) REFERENCES `empleado` (`idEmpleado`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registros_creados`
--

LOCK TABLES `registros_creados` WRITE;
/*!40000 ALTER TABLE `registros_creados` DISABLE KEYS */;
INSERT INTO `registros_creados` VALUES (1,'2024-04-19 10:39:24','881858dc-9','73dfe6ec-6'),(2,'2024-04-22 00:43:04','b6cc5421-0','464a6e44-a'),(3,'2024-04-22 01:11:03','admin','bd2992fd-6'),(4,'2024-04-22 01:14:34','7a079594-4','23b07086-5'),(5,'2024-04-22 01:19:54','7a079594-4','18a458e9-9'),(6,'2024-04-22 01:56:27','7a079594-4','529da966-3');
/*!40000 ALTER TABLE `registros_creados` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `actualizar_fecha_creacion` BEFORE INSERT ON `registros_creados` FOR EACH ROW BEGIN
    SET NEW.fecha_creacion = NOW();
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `tesis`
--

DROP TABLE IF EXISTS `tesis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tesis` (
  `titulo` varchar(100) NOT NULL,
  `fecha` date DEFAULT NULL,
  `autor` varchar(100) DEFAULT NULL,
  `campo_estudio` varchar(100) DEFAULT NULL,
  `pais` varchar(100) DEFAULT NULL,
  `cantidad_copia` int NOT NULL DEFAULT '0',
  `cantidad_prestado` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`titulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tesis`
--

LOCK TABLES `tesis` WRITE;
/*!40000 ALTER TABLE `tesis` DISABLE KEYS */;
INSERT INTO `tesis` VALUES ('Nombre de la Tesis 1','2023-01-14','Autor de la Tesis 1','Campo de Estudio 1','País de la Tesis 1',10,4),('Nombre de la Tesis 2','2023-02-19','Autor de la Tesis 2','Campo de Estudio 2','País de la Tesis 2',15,5),('Nombre de la Tesis 3','2023-03-09','Autor de la Tesis 3','Campo de Estudio 3','País de la Tesis 3',20,8),('Nombre de la Tesis 4','2023-04-04','Autor de la Tesis 4','Campo de Estudio 4','País de la Tesis 4',12,4),('Nombre de la Tesis 5','2023-05-17','Autor de la Tesis 5','Campo de Estudio 5','País de la Tesis 5',18,6),('Título 1','2023-01-14','Autor 1','Campo 1','País 1',10,3),('Título 10','2023-10-11','Autor 10','Campo 10','País 10',35,14),('Título 2','2023-02-19','Autor 2','Campo 2','País 2',15,5),('Título 3','2023-03-09','Autor 3','Campo 3','País 3',20,8),('Título 4','2023-04-04','Autor 4','Campo 4','País 4',12,4),('Título 5','2023-05-17','Autor 5','Campo 5','País 5',18,6),('Título 6','2023-06-21','Autor 6','Campo 6','País 6',25,9),('Título 7','2023-07-13','Autor 7','Campo 7','País 7',30,12),('Título 8','2023-08-29','Autor 8','Campo 8','País 8',22,7),('Título 9','2023-09-04','Autor 9','Campo 9','País 9',28,10),('titulotesis','2024-04-01','nombreautor','campoestudiotesis','colombia',20,1);
/*!40000 ALTER TABLE `tesis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `correo` varchar(100) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `contrasena` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`correo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('daniel','cliente','123'),('nombrea','nombrea','1234'),('ohman','ohman','123'),('user','user','123'),('user2','user2','123'),('user3','user3','111'),('user4','user4','321'),('user5','user5','321'),('user6','user6','123'),('userprueba14','userprueba14','123');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videograbacion`
--

DROP TABLE IF EXISTS `videograbacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videograbacion` (
  `titulo` varchar(100) NOT NULL,
  `sinopsis` text,
  `genero` varchar(45) DEFAULT NULL,
  `autor` varchar(100) DEFAULT NULL,
  `calificacion` int DEFAULT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `cantidad_copia` int NOT NULL DEFAULT '0',
  `cantidad_prestado` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`titulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videograbacion`
--

LOCK TABLES `videograbacion` WRITE;
/*!40000 ALTER TABLE `videograbacion` DISABLE KEYS */;
INSERT INTO `videograbacion` VALUES ('video','video','video','video',4,'video',14,1),('Video 1','Sinopsis del video 1','Género 1','Autor 1',4,'Tipo 1',10,3),('Video 10','Sinopsis del video 10','Género 10','Autor 10',5,'Tipo 10',35,14),('Video 2','Sinopsis del video 2','Género 2','Autor 2',3,'Tipo 2',15,5),('Video 3','Sinopsis del video 3','Género 3','Autor 3',5,'Tipo 3',20,8),('Video 4','Sinopsis del video 4','Género 4','Autor 4',4,'Tipo 4',12,4),('Video 5','Sinopsis del video 5','Género 5','Autor 5',3,'Tipo 5',18,6),('Video 6','Sinopsis del video 6','Género 6','Autor 6',4,'Tipo 6',25,9),('Video 7','Sinopsis del video 7','Género 7','Autor 7',5,'Tipo 7',30,12),('Video 8','Sinopsis del video 8','Género 8','Autor 8',3,'Tipo 8',22,7),('Video 9','Sinopsis del video 9','Género 9','Autor 9',4,'Tipo 9',28,10),('Video A','Sinopsis del video A','Género A','Autor A',4,'Tipo A',10,3),('Video B','Sinopsis del video B','Género B','Autor B',3,'Tipo B',15,5),('Video C','Sinopsis del video C','Género C','Autor C',5,'Tipo C',20,8),('Video D','Sinopsis del video D','Género D','Autor D',4,'Tipo D',12,4),('Video E','Sinopsis del video E','Género E','Autor E',3,'Tipo E',18,6);
/*!40000 ALTER TABLE `videograbacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'biblioteca_pinguinera_db'
--

--
-- Dumping routines for database 'biblioteca_pinguinera_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-22  3:06:15
