-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema online_market
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `online_market` ;

-- -----------------------------------------------------
-- Schema online_market
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `online_market` DEFAULT CHARACTER SET utf8 ;
USE `online_market` ;

-- -----------------------------------------------------
-- Table `online_market`.`Item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `online_market`.`Item` (
  `id` INT NOT NULL,
  `name` VARCHAR(80) NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `online_market`.`item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `online_market`.`item` (
  `id` INT NOT NULL,
  `nane` VARCHAR(80) NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `online_market`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `online_market`.`role` (
  `id` INT NOT NULL,
  `name` VARCHAR(20) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `online_market`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `online_market`.`user` (
  `id` INT NOT NULL,
  `username` VARCHAR(70) NULL,
  `password` VARCHAR(200) NULL,
  `roleId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_role_idx` (`roleId` ASC),
  CONSTRAINT `fk_user_role`
    FOREIGN KEY (`roleId`)
    REFERENCES `online_market`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
