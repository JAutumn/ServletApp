-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema servletApp
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema servletApp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `servletApp` ;
USE `servletApp` ;

-- -----------------------------------------------------
-- Table `servletApp`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `servletApp`.`users` ;

CREATE TABLE IF NOT EXISTS `servletApp`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `servletApp`.`avatars`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `servletApp`.`avatars` ;

CREATE TABLE IF NOT EXISTS `servletApp`.`avatars` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data` MEDIUMBLOB NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC),
  CONSTRAINT `fk_avatars_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `servletApp`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `servletApp`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `servletApp`;
INSERT INTO `servletApp`.`users` (`id`, `email`, `password`, `role`, `name`) VALUES (1, 'admin@gmail.com', 'admin', 'ADMIN', 'Alex');
INSERT INTO `servletApp`.`users` (`id`, `email`, `password`, `role`, `name`) VALUES (2, 'billi@gmail.com', '123', 'USER', 'Billi');
INSERT INTO `servletApp`.`users` (`id`, `email`, `password`, `role`, `name`) VALUES (3, 'stephani@gmail.com', '123', 'USER', 'Stephani');
INSERT INTO `servletApp`.`users` (`id`, `email`, `password`, `role`, `name`) VALUES (4, 'kate@gmail.com', '123', 'USER', 'Kate');

COMMIT;

