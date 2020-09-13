	CREATE DATABASE IF NOT EXISTS `Domotica`;	
    
    SHOW ENGINE INNODB STATUS;
	
    CREATE TABLE IF NOT EXISTS `domotica`.`HouseCode`(
	`house_code_id` INT NOT NULL AUTO_INCREMENT UNIQUE,
    `house_registration_code` VARCHAR(20) NOT NULL,
    PRIMARY KEY(`house_code_id`)
    );
    
    CREATE TABLE IF NOT EXISTS `domotica`.`House`(
	`house_id` INTEGER NOT NULL AUTO_INCREMENT UNIQUE,
    `house_name` VARCHAR (60) NOT NULL,
    `house_code` VARCHAR (15) NOT NULL UNIQUE,
    `house_registration_id` INT NOT NULL,
    PRIMARY KEY(`house_id`),
    FOREIGN KEY (`house_registration_id`) REFERENCES `HouseCode`(`house_code_id`) ON DELETE CASCADE
    );
    
    CREATE TABLE IF NOT EXISTS `domotica`.`User`(
	`user_id` INT NOT NULL AUTO_INCREMENT UNIQUE,
    `user_name` VARCHAR (50) NOT NULL,
    `user_lastname` VARCHAR (50) NOT NULL,
    `user_email` VARCHAR (125) NOT NULL,
    `user_password` VARCHAR (50) NOT NULL,
    `house_id` INTEGER NOT NULL,
    `user_role` VARCHAR(15) NOT NULL DEFAULT "USER",
    PRIMARY KEY(`user_id`),
    FOREIGN KEY (`house_id`) REFERENCES `House`(`house_id`) ON DELETE CASCADE
    );
    
    CREATE TABLE IF NOT EXISTS `domotica`.`Area`(
	`area_id` INT NOT NULL AUTO_INCREMENT UNIQUE,
    `area_name` VARCHAR (50) NOT NULL,
    `house_id` INT NOT NULL,
    `area_role` VARCHAR(15) NOT NULL DEFAULT "USER",
    PRIMARY KEY(`Area_id`),
    FOREIGN KEY(`house_id`) REFERENCES `House`(`house_id`) ON DELETE CASCADE
    );
    
    CREATE TABLE IF NOT EXISTS `domotica`.`Device`(
	`device_id` INT NOT NULL AUTO_INCREMENT UNIQUE,
    `device_name` VARCHAR (50) NOT NULL,
    `device_state` BOOLEAN DEFAULT FALSE,
    `device_description` VARCHAR (400),
    `device_role` VARCHAR(15) NOT NULL DEFAULT "USER",
    `house_id` INT NOT NULL,
    PRIMARY KEY(`device_id`),
    FOREIGN KEY(`house_id`) REFERENCES `House`(`house_id`) ON DELETE CASCADE
    );
    
    CREATE TABLE IF NOT EXISTS `domotica`.`AreaToArea`(
	`relation_id` INT NOT NULL AUTO_INCREMENT UNIQUE,
    `parent_area_id` INT NOT NULL,
    `child_area_id` INT NOT NULL,
    PRIMARY KEY(`relation_id`),
    FOREIGN KEY(`parent_area_id`) REFERENCES `Area`(`area_id`) ON DELETE CASCADE,
    FOREIGN KEY(`child_area_id`) REFERENCES `Area`(`area_id`) ON DELETE CASCADE
    );
    
    CREATE TABLE IF NOT EXISTS `domotica`.`AreaToDevice`(
	`relation_id` INT NOT NULL AUTO_INCREMENT UNIQUE,
    `parent_area_id` INT NOT NULL,
    `child_device_id` INT NOT NULL,
    PRIMARY KEY(`relation_id`),
    FOREIGN KEY(`parent_area_id`) REFERENCES `Area`(`area_id`) ON DELETE CASCADE,
    FOREIGN KEY(`child_device_id`) REFERENCES `Device`(`device_id`) ON DELETE CASCADE
    );
    
    CREATE TABLE IF NOT EXISTS `domotica`.`AreaToUser`(
	`relation_id` INT NOT NULL AUTO_INCREMENT UNIQUE,
    `parent_area_id` INT NOT NULL,
    `child_user_id` INT NOT NULL,
    PRIMARY KEY(`relation_id`),
    FOREIGN KEY(`parent_area_id`) REFERENCES `Area`(`area_id`) ON DELETE CASCADE,
    FOREIGN KEY(`child_user_id`) REFERENCES `User`(`user_id`) ON DELETE CASCADE
    );
    
    CREATE TABLE IF NOT EXISTS `domotica`.`DeviceToUser`(
	`relation_id` INT NOT NULL AUTO_INCREMENT UNIQUE,
    `parent_device_id` INT NOT NULL,
    `child_user_id` INT NOT NULL,
    PRIMARY KEY(`relation_id`),
    FOREIGN KEY(`parent_device_id`) REFERENCES `Device`(`device_id`) ON DELETE CASCADE,
    FOREIGN KEY(`child_user_id`) REFERENCES `User`(`user_id`) ON DELETE CASCADE
    );
    
    
    