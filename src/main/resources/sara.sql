DROP TABLE IF EXISTS `product_details`;

CREATE TABLE `product_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `description` text,
  `price` double DEFAULT NULL,
  `discount` float DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  `html` longtext DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;