-- MySQL dump 10.13  Distrib 8.0.28, for Linux (x86_64)
--
-- Host: localhost    Database: backend_db
-- ------------------------------------------------------
-- Server version	8.0.28-0ubuntu0.20.04.3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `entries`
--

DROP TABLE IF EXISTS `entries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entries` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `agree_to_terms` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entries`
--

LOCK TABLES `entries` WRITE;
/*!40000 ALTER TABLE `entries` DISABLE KEYS */;
/*!40000 ALTER TABLE `entries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entry_sectors`
--

DROP TABLE IF EXISTS `entry_sectors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entry_sectors` (
  `entry_id` bigint NOT NULL,
  `sector_id` bigint NOT NULL,
  PRIMARY KEY (`entry_id`,`sector_id`),
  KEY `FK1ctu7ewu944j2o4p6wyfgcx3w` (`sector_id`),
  CONSTRAINT `FK1ctu7ewu944j2o4p6wyfgcx3w` FOREIGN KEY (`sector_id`) REFERENCES `sectors` (`id`),
  CONSTRAINT `FKkkrrymjorc0kyr91843gammo8` FOREIGN KEY (`entry_id`) REFERENCES `entries` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entry_sectors`
--

LOCK TABLES `entry_sectors` WRITE;
/*!40000 ALTER TABLE `entry_sectors` DISABLE KEYS */;
/*!40000 ALTER TABLE `entry_sectors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sectors`
--

DROP TABLE IF EXISTS `sectors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sectors` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `parent_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq1348ewyn6hb4vr2yypo2ofcy` (`parent_id`),
  CONSTRAINT `FKq1348ewyn6hb4vr2yypo2ofcy` FOREIGN KEY (`parent_id`) REFERENCES `sectors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=582 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sectors`
--

LOCK TABLES `sectors` WRITE;
/*!40000 ALTER TABLE `sectors` DISABLE KEYS */;
INSERT INTO `sectors` VALUES (1,'Manufacturing',NULL),(2,'Service',NULL),(3,'Other',NULL),(5,'Printing',1),(6,'Food and Beverage',1),(7,'Textile and Clothing',1),(8,'Wood',1),(9,'Plastic and Rubber',1),(11,'Metalworking',1),(12,'Machinery',1),(13,'Furniture',1),(18,'Electronics and Optics',1),(19,'Construction materials',1),(21,'Transport and Logistics',2),(22,'Tourism',2),(25,'Business services',2),(28,'Information Technology and Telecommunications',2),(29,'Energy technology',3),(33,'Environment',3),(35,'Engineering',2),(37,'Creative industries',3),(39,'Milk &amp; dairy products',6),(40,'Meat &amp; meat products',6),(42,'Fish &amp; fish products',6),(43,'Beverages',6),(44,'Clothing',7),(45,'Textile',7),(47,'Wooden houses',8),(51,'Wooden building materials',8),(53,'Plastics welding and processing',559),(54,'Packaging',9),(55,'Blowing',559),(57,'Moulding',559),(62,'Forgings, Fasteners',542),(66,'MIG, TIG, Aluminum welding',542),(67,'Construction of metal structures',11),(69,'Gas, Plasma, Laser cutting',542),(75,'CNC-machining',542),(91,'Machinery equipment/tools',12),(93,'Metal structures',12),(94,'Machinery components',12),(97,'Maritime',12),(98,'Kitchen',13),(99,'Project furniture',13),(101,'Living room',13),(111,'Air',21),(112,'Road',21),(113,'Water',21),(114,'Rail',21),(121,'Software, Hardware',28),(122,'Telecommunications',28),(141,'Translation services',2),(145,'Labelling and packaging printing',5),(148,'Advertising',5),(150,'Book/Periodicals printing',5),(224,'Manufacture of machinery',12),(227,'Repair and maintenance service',12),(230,'Ship repair and conversion',97),(263,'Houses and building',11),(267,'Metal products',11),(269,'Boat/Yacht building',97),(271,'Aluminium and steel workboats',97),(337,'Other (Wood)',8),(341,'Outdoor',13),(342,'Bakery &amp; confectionery products',6),(378,'Sweets &amp; snack food',6),(385,'BedRoom',13),(389,'Bathroom/sauna',13),(390,'Children’s room',13),(392,'Office',13),(394,'Other (Furniture)',13),(408,'Other',12),(437,'Other',6),(542,'Metal works',11),(556,'Plastic goods',9),(559,'Plastic processing technology',9),(560,'Plastic profiles',9),(576,'Programming, Consultancy',28),(581,'Data processing, Web portals, E-marketing',28);
/*!40000 ALTER TABLE `sectors` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-11 13:34:47
