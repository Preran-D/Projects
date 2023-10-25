CREATE TABLE IF NOT EXISTS `flyway_schema_history` (
    `installed_rank` INT NOT NULL,
    `version` VARCHAR(50) DEFAULT NULL,
    `description` VARCHAR(200) NOT NULL,
    `type` VARCHAR(20) NOT NULL,
    `script` VARCHAR(1000) NOT NULL,
    `checksum` INT DEFAULT NULL,
    `installed_by` VARCHAR(100) NOT NULL,
    `installed_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `execution_time` INT NOT NULL,
    `success` TINYINT(1) NOT NULL,
    PRIMARY KEY (`installed_rank`),
    KEY `flyway_schema_history_s_idx` (`success`)
    ) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;

CREATE TABLE IF NOT EXISTS `users` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(255) DEFAULT NULL,
    `name` VARCHAR(20) NOT NULL,
    `password` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=INNODB AUTO_INCREMENT=20 DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;

CREATE TABLE IF NOT EXISTS `role` (
    `id` INT NOT NULL,
    `name` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;

CREATE TABLE IF NOT EXISTS `user_role` (
    `user` BIGINT NOT NULL,
    `role` INT NOT NULL,
    PRIMARY KEY (`user` , `role`),
    KEY `FK26f1qdx6r8j1ggkgras9nrc1d` (`role`),
    CONSTRAINT `FK26f1qdx6r8j1ggkgras9nrc1d` FOREIGN KEY (`role`)
    REFERENCES `role` (`id`),
    CONSTRAINT `FKlduspqw8rg0gbcpludbfadw6l` FOREIGN KEY (`user`)
    REFERENCES `users` (`id`)
    ) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;

CREATE TABLE IF NOT EXISTS `categories` (
    `category_id` BIGINT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`category_id`)
    ) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;

CREATE TABLE IF NOT EXISTS `post` (
    `post_id` BIGINT NOT NULL AUTO_INCREMENT,
    `date` DATETIME(6) DEFAULT NULL,
    `content` VARCHAR(10000) DEFAULT NULL,
    `title` VARCHAR(100) NOT NULL,
    `category_id` BIGINT DEFAULT NULL,
    `user_id` BIGINT DEFAULT NULL,
    PRIMARY KEY (`post_id`),
    KEY `FKjl0ab1c7s7gsd0tp830a7oogx` (`category_id`),
    KEY `FK7ky67sgi7k0ayf22652f7763r` (`user_id`),
    CONSTRAINT `FK7ky67sgi7k0ayf22652f7763r` FOREIGN KEY (`user_id`)
    REFERENCES `users` (`id`),
    CONSTRAINT `FKjl0ab1c7s7gsd0tp830a7oogx` FOREIGN KEY (`category_id`)
    REFERENCES `categories` (`category_id`)
    ) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;

CREATE TABLE IF NOT EXISTS `comments` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `content` VARCHAR(255) DEFAULT NULL,
    `post_post_id` BIGINT DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKml8v0aac8qo7cbgh37tocvxd4` (`post_post_id`),
    CONSTRAINT `FKml8v0aac8qo7cbgh37tocvxd4` FOREIGN KEY (`post_post_id`)
    REFERENCES `post` (`post_id`)
    ) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;

