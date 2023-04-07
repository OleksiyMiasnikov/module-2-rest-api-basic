-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema module_2_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema module_2_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `module_2_db` DEFAULT CHARACTER SET utf8mb3 ;
USE `module_2_db` ;

-- -----------------------------------------------------
-- Table `module_2_db`.`tags`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `module_2_db`.`tags` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `module_2_db`.`certificates`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `module_2_db`.`certificates` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(100) NULL,
  `price` DECIMAL(10,2) UNSIGNED NULL,
  `duration` DECIMAL(10) UNSIGNED NULL,
  `create_date` VARCHAR(30) NULL,
  `last_update_date` VARCHAR(30) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `module_2_db`.`tags_has_certificates`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `module_2_db`.`tags_has_certificates` (
  `tags_id` INT UNSIGNED NOT NULL,
  `certificates_id` INT NOT NULL,
  PRIMARY KEY (`tags_id`, `certificates_id`),
  INDEX `fk_tags_has_certificates_certificates1_idx` (`certificates_id` ASC) VISIBLE,
  INDEX `fk_tags_has_certificates_tags_idx` (`tags_id` ASC) VISIBLE,
  CONSTRAINT `fk_tags_has_certificates_tags`
    FOREIGN KEY (`tags_id`)
    REFERENCES `module_2_db`.`tags` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tags_has_certificates_certificates1`
    FOREIGN KEY (`certificates_id`)
    REFERENCES `module_2_db`.`certificates` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
