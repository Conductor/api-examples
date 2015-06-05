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
   `account_id` int(10) unsigned,
    PRIMARY KEY (`web_property_id`)
 );
 
 CREATE TABLE `tracked_search` (
   `tracked_search_id` int(11) NOT NULL,
   `status_id` tinyint(3) unsigned NOT NULL DEFAULT '1',
   `preferred_url` varchar(255),
   `query_phrase` varchar(255) NOT NULL,
   `location_id` int(11) NOT NULL,
   `rank_source_id` int(11) NOT NULL,
   `device_id` int(11) NOT NULL,
   `web_property_id` int(11) NOT NULL,
   PRIMARY KEY (`tracked_search_id`),
   KEY `fk_tracked_search_location_id` (`location_id`),
   KEY `fk_tracked_search_device_id` (`device_id`),
   KEY `fk_tracked_search_rank_source_id` (`rank_source_id`),
   CONSTRAINT `fk_tracked_location_id` FOREIGN KEY (`location_id`) REFERENCES `locale` (`locale_id`),
   CONSTRAINT `fk_tracked_rank_source_id` FOREIGN KEY (`rank_source_id`) REFERENCES `rank_source` (`rank_source_id`),
   CONSTRAINT `fk_tracked_search_device_id` FOREIGN KEY (`device_id`) REFERENCES `device` (`device_id`)
 );

CREATE TABLE `client_web_property_rank_report` (
  `universal_rank` int(11),
  `true_rank` int(11) ,
  `classic_rank` int(11),
  `web_property_id` int(11) NOT NULL,
  `tracked_search_id` int(11) NOT NULL,
  `item_type` varchar(32) NOT NULL,
  `target` varchar(255) DEFAULT NULL,
  `target_domain_name` varchar(255) DEFAULT NULL,
  `target_url` varchar(511) DEFAULT NULL
);

 CREATE TABLE `client_web_property_search_volume_report` (
   `tracked_search_id` int(11),
   `average_volume` int(11),
    PRIMARY KEY (`tracked_search_id`),
    KEY `fk_tracked_search_id` (`tracked_search_id`),
    CONSTRAINT `fk_tracked_search_id` FOREIGN KEY(`tracked_search_id`) REFERENCES `tracked_search` (`tracked_search_id`)
 );
 
 CREATE TABLE `tracked_search_volumes` (
   `tracked_search_id` int(11) ,
   `volume` int(11),
   `month` int(2) NOT NULL,
   `year` int(4) NOT NULL,
   FOREIGN KEY(`tracked_search_id`) REFERENCES `tracked_search` (`tracked_search_id`)
 );
