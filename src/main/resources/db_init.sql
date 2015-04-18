-- MySQL dump 10.13  Distrib 5.6.22, for Win64 (x86_64)
--
-- Host: localhost    Database: renovator
-- ------------------------------------------------------
-- Server version	5.6.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `username` varchar(45) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES ('lingda','123','ROLE_ADMIN');
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `description` text,
  `price` double DEFAULT NULL,
  `discount` float DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  `picurl` varchar(255) DEFAULT NULL,
  `picurl_zoom` varchar(255) DEFAULT NULL,
  `picurl1` varchar(255) DEFAULT NULL,
  `picurl2` varchar(255) DEFAULT NULL,
  `picurl3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'灵达的神器','皮衣','猪猪的最爱',37.5,0.85,'2015-04-04 00:00:00','印钞机.jpg','印钞机.jpg','印钞机1.jpg','印钞机1.jpg','印钞机1.jpg'),(2,'印钞机','皮具','一天最多1000张',123,0.1,'2015-04-12 00:00:00','印钞机.jpg','印钞机.jpg','印钞机1.jpg','印钞机1.jpg','印钞机1.jpg'),(3,'时光机','皮衣','轮回一万年',500,0.4,'2015-04-18 00:00:00','印钞机.jpg','印钞机.jpg','印钞机1.jpg','印钞机1.jpg','印钞机1.jpg'),(4,'粉色包','包类','粉色包',100,1,'2015-04-18 15:15:14','粉色包.jpg','粉色包.jpg','粉色包1.jpg','粉色包1.jpg','粉色包1.jpg'),(5,'黑色包','包类','黑色包',100,1,'2015-04-18 15:19:27','黑色包.jpg','黑色包.jpg','黑色包1.jpg','黑色包1.jpg','黑色包1.jpg'),(6,'黑金包','包类','黑金包',100,1,'2015-04-18 15:19:56','黑金包.jpg','黑金包.jpg','黑金包1.jpg','黑金包1.jpg','黑金包1.jpg'),(7,'红色包','包类','红色包',100,1,'2015-04-18 15:21:29','红色包.jpg','红色包.jpg','红色包1.jpg','红色包1.jpg','红色包1.jpg'),(8,'白点包','包类','白点包',100,1,'2015-04-18 15:21:58','白点包.jpg','白点包.jpg','白点包1.jpg','白点包1.jpg','白点包1.jpg'),(10,'皮夹子','皮具','皮夹子',100,1,'2015-04-18 15:25:36','皮夹子.jpg','皮夹子.jpg','皮夹子1.jpg','皮夹子1.jpg','皮夹子1.jpg'),(11,'白鞋','鞋类','白鞋',100,1,'2015-04-18 15:26:24','白鞋.jpg','白鞋.jpg','白鞋1.jpg','白鞋1.jpg','白鞋1.jpg'),(12,'黑鞋','鞋类','黑鞋',100,1,'2015-04-18 15:26:43','黑鞋.jpg','黑鞋.jpg','黑鞋1.jpg','黑鞋1.jpg','黑鞋1.jpg');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,'12345567','已发货','sale',87.5,'2015-04-04 00:00:00',15,7),(2,'12345','已下单','free',37.5,'2015-04-04 00:00:00',15,2),(3,'1213545','派送中','sale',400,'2015-04-18 11:43:20',15,3),(4,'573831','已收货','normal',200,'2015-04-18 11:45:06',15,5);
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `contact` varchar(45) DEFAULT NULL,
  `address` text,
  `birthday` date DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `open_id` varchar(128) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (15,'唐灵达','13402188638',NULL,NULL,1500,'oR_l8s7xyfgmR7YHu1eI1z8RUzuI','darlingtld@qq.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-18 20:46:43

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(64) COMMENT '标题',
  `cover` VARCHAR(255) COMMENT '封面',
  `content` MEDIUMTEXT COMMENT '正文',
  `ts` DATE DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;




DROP TABLE IF EXISTS `pushMessageTask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pushMessageTask` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(255) NOT NULL	,
  `msg` VARCHAR(2550) NOT NULL,
  `scheduled_time` DATE DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

-- Dump completed on 2015-04-05  9:55:33
