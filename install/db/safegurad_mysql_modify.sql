use safegurad;
ALTER TABLE  `system_default_property` ADD COLUMN `news_prefix` VARCHAR(255) NOT NULL;
ALTER TABLE `customer` ADD COLUMN `last_signed` BIGINT NULL;

DROP TABLE IF EXISTS `version`;
CREATE TABLE IF NOT EXISTS `version` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `code` int(11) NOT NULL,
  `created` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `platform` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `compatible_id` bigint(20) DEFAULT NULL,
  `operator_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nmum584klt30lsg9xo25mwiv6` (`code`),
  KEY `FK_p0fjp6bx07d8y394yo6o1gydj` (`compatible_id`),
  KEY `FK_3i6f1cc74ak7aixvh79gskahj` (`operator_id`),
  CONSTRAINT `FK_3i6f1cc74ak7aixvh79gskahj` FOREIGN KEY (`operator_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_p0fjp6bx07d8y394yo6o1gydj` FOREIGN KEY (`compatible_id`) REFERENCES `version` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--- create tag
DROP TABLE IF EXISTS `tag`;
CREATE TABLE IF NOT EXISTS `tag` (
  `id` bigint(20) NOT NULL,
  `created` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL,
  `last_modified` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1wdpsed5kna2y38hnbgrnhi5b` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- create news_tags
DROP TABLE IF EXISTS `news_tags`;
CREATE TABLE IF NOT EXISTS `news_tags` (
  `news_id` bigint(20) NOT NULL,
  `tag_id` bigint(20) NOT NULL,
  KEY `FK_hy9fi0hxtjxa5ky694g9pkmv8` (`tag_id`),
  KEY `FK_oyessyhlac2b62uffu4trufqx` (`news_id`),
  CONSTRAINT `FK_hy9fi0hxtjxa5ky694g9pkmv8` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`),
  CONSTRAINT `FK_oyessyhlac2b62uffu4trufqx` FOREIGN KEY (`news_id`) REFERENCES `news` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `tag` (`id`, `created`, `active`, `last_modified`, `name`) VALUES
	(601, 1434444949968, b'0', 1434444949968, '甲醛'),
	(651, 1434445676701, b'0', 1434445676701, 'PM2.5'),
	(661, 1434447829866, b'0', 1434447829866, '苯'),
	(701, 1434513350902, b'0', 1434513350902, 'TVOC'),
	(702, 1434513373334, b'0', 1434513373334, '其它');

-- create news
DROP TABLE IF EXISTS `news`;
CREATE TABLE IF NOT EXISTS `news` (
  `id` bigint(20) NOT NULL,
  `created` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL,
  `last_modified` bigint(20) NOT NULL,
  `channel` int(11) DEFAULT NULL,
  `main_title` varchar(255) DEFAULT NULL,
  `serial` varchar(255) NOT NULL,
  `status` int(11) NOT NULL,
  `storage_path` varchar(255) DEFAULT NULL,
  `sub_title` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `operator_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9g5etsnpmflm16120e4tx5h48` (`serial`),
  KEY `FK_4ahrp7dm88vywb4emnrxcg522` (`operator_id`),
  CONSTRAINT `FK_4ahrp7dm88vywb4emnrxcg522` FOREIGN KEY (`operator_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--- modify equiment_consumer_reocrd
