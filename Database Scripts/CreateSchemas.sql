use <<localDatabaseName>>;

CREATE TABLE `device` (
  `device_id` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`device_id`)
);

CREATE TABLE `locale` (
  `locale_id` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`locale_id`)
);

CREATE TABLE `rank_source` (
  `rank_source_id` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  `description` varchar(255) NOT NULL,
  `base_url` varchar(127) NOT NULL,
  PRIMARY KEY (`rank_source_id`),
  UNIQUE KEY `base_url_UNIQUE` (`base_url`)
);

CREATE TABLE `web_property` (
  `web_property_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `label` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`web_property_id`)
);





