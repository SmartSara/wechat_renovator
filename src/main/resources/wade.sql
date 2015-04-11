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
