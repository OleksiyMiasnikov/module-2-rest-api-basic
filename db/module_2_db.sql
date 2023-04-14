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
-- Table `module_2_db`.`tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `module_2_db`.`tag` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `module_2_db`.`certificate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `module_2_db`.`certificate` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(100) NULL,
  `price` DECIMAL(10,2) UNSIGNED NULL,
  `duration` DECIMAL(10) UNSIGNED NULL,
  `create_date` VARCHAR(45) NULL,
  `last_update_date` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `module_2_db`.`certificate_with_tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `module_2_db`.`certificate_with_tag` (
  `tag_id` INT UNSIGNED NOT NULL,
  `certificate_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`tag_id`, `certificate_id`),
  INDEX `fk_tags_has_certificates_certificates1_idx` (`certificate_id` ASC) VISIBLE,
  INDEX `fk_tags_has_certificates_tags_idx` (`tag_id` ASC) VISIBLE,
  CONSTRAINT `fk_tags_has_certificates_tags`
    FOREIGN KEY (`tag_id`)
    REFERENCES `module_2_db`.`tag` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tags_has_certificates_certificates1`
    FOREIGN KEY (`certificate_id`)
    REFERENCES `module_2_db`.`certificate` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
