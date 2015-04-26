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
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) DEFAULT NULL COMMENT '标题',
  `cover` varchar(255) DEFAULT NULL COMMENT '封面',
  `content` mediumtext COMMENT '正文',
  `ts` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
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
  `picurl1` varchar(255) DEFAULT NULL,
  `picurl2` varchar(255) DEFAULT NULL,
  `picurl3` varchar(255) DEFAULT NULL,
  `html` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'灵达的神器','皮衣','猪猪的最爱',37.5,0.85,'2015-04-04 00:00:00','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg',NULL),(2,'印钞机','皮具','一天最多1000张',123,0.1,'2015-04-12 00:00:00','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg',NULL),(3,'时光机','皮衣','轮回一万年',500,0.4,'2015-04-18 00:00:00','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg',NULL),(4,'粉色包','包类','粉色包',100,1,'2015-04-18 15:15:14','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg',NULL),(5,'黑色包','包类','黑色包',100,1,'2015-04-18 15:19:27','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg',NULL),(6,'黑金包','包类','黑金包',100,1,'2015-04-18 15:19:56','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg',NULL),(7,'红色包','包类','红色包',100,1,'2015-04-18 15:21:29','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg',NULL),(8,'白点包','包类','白点包',100,1,'2015-04-18 15:21:58','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg',NULL),(10,'皮夹子','皮具','皮夹子',100,1,'2015-04-18 15:25:36','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg',NULL),(11,'白鞋','鞋类','白鞋',100,1,'2015-04-18 15:26:24','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg',NULL),(12,'黑鞋','鞋类','黑鞋',100,1,'2015-04-18 15:26:43','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg',NULL),(13,'冷饮','皮具','爱丁堡',14,0.1,'2015-04-26 07:44:31','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg',NULL),(14,'阿布','皮具','123',123,123,'2015-04-26 07:49:35','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','<p><img src=\"http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/66461430005757113.jpg\" _src=\"http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/66461430005757113.jpg\" style=\"\"/></p><p><img src=\"http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/25701430005759945.jpg\" _src=\"http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/25701430005759945.jpg\" style=\"\"/></p><p><br/></p>'),(15,'214','皮具','3214',1234,1234,'2015-04-26 07:54:21','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','<p>213421342134<img src=\"http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/34431430006055842.jpg\" _src=\"http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/34431430006055842.jpg\"/></p>'),(16,'saf','鞋类','asdf',12345,1,'2015-04-26 08:09:21','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/2051430006947834.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/49961430006951862.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/2051430006947834.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/2051430006947834.jpg','<p><img src=\"http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/2051430006947834.jpg\" _src=\"http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/2051430006947834.jpg\" style=\"\"/></p><p><img src=\"http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/49961430006951862.jpg\" _src=\"http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/49961430006951862.jpg\" style=\"\"/></p><p><br/></p>'),(17,'皮鞋','皮具','不错的皮鞋',567,0.1,'2015-04-26 10:05:26','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/98931430013919674.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/65031430013921766.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/98931430013919674.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/98931430013919674.jpg','<p><img src=\"http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/98931430013919674.jpg\" _src=\"http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/98931430013919674.jpg\" style=\"\"/></p><p><img src=\"http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/65031430013921766.jpg\" _src=\"http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/65031430013921766.jpg\" style=\"\"/></p><p><br/></p>'),(18,'落叶归根','鞋类','举头望无尽灰云',598,0.3,'2015-04-26 10:19:48','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/48261430014767181.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/41321430014783890.png','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/48261430014767181.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/48261430014767181.jpg','<p><img src=\"http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/48261430014767181.jpg\" _src=\"http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/48261430014767181.jpg\" style=\"\"/></p><p><img src=\"http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/41321430014783890.png\" _src=\"http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/41321430014783890.png\" style=\"\"/></p><p><br/></p>'),(19,'花田错','鞋类','说好破晓前忘掉',500,0.2,'2015-04-26 10:20:37','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/83081430014834587.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg','<p><img src=\"http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg\" _src=\"http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/10391430014831295.jpg\" style=\"\"/></p><p><img src=\"http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/83081430014834587.jpg\" _src=\"http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/83081430014834587.jpg\" style=\"\"/></p><p><br/></p>'),(20,'龙的传人','包类','遥远的东方有一条龙',2394839,0.3,'2015-04-26 10:21:40','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/74511430014895312.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/8261430014898587.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/74511430014895312.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/74511430014895312.jpg','<p><img src=\"http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/74511430014895312.jpg\" _src=\"http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/74511430014895312.jpg\" style=\"\"/></p><p><img src=\"http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/8261430014898587.jpg\" _src=\"http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/8261430014898587.jpg\" style=\"\"/></p><p><br/></p>'),(21,'在梅边','包类','在梅边，不知爱何时出现',300,0.1,'2015-04-26 10:22:54','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/81431430014972217.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/81431430014972217.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/81431430014972217.jpg','http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/81431430014972217.jpg','<p><img src=\"http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/81431430014972217.jpg\" _src=\"http://localhost:8080/product_editcase/umeditor/jsp/upload/20150426/81431430014972217.jpg\"/></p>');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pushmessagetask`
--

DROP TABLE IF EXISTS `pushmessagetask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pushmessagetask` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL,
  `msg` varchar(2550) NOT NULL,
  `scheduled_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pushmessagetask`
--

LOCK TABLES `pushmessagetask` WRITE;
/*!40000 ALTER TABLE `pushmessagetask` DISABLE KEYS */;
/*!40000 ALTER TABLE `pushmessagetask` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (15,'唐灵达','13402188638',NULL,NULL,1500,'oR_l8s7xyfgmR7YHu1eI1z8RUzuI','darlingtld@qq.com'),(18,'c罗','1234567890',NULL,NULL,0,'123456','asdf@aa.com'),(19,'dsaf','asdf',NULL,NULL,0,'123456','asdf');
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

-- Dump completed on 2015-04-26 12:13:40
