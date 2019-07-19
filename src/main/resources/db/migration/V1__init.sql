CREATE SCHEMA IF NOT EXISTS `menu_service`;

-- -----------------------------------------------------
-- Table `menu_service`.`week`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `menu_service`.`week` (
                                                     `id` INT NOT NULL,
                                                     `week_number` VARCHAR(45) NOT NULL,
                                                     `start_date` VARCHAR(45) NULL,
                                                     `end_date` VARCHAR(45) NULL,
                                                     PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `menu_service`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `menu_service`.`product` (
                                                        `id` INT NOT NULL,
                                                        `name` VARCHAR(45) NOT NULL,
                                                        `external_id` VARCHAR(45) NULL,
                                                        PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `menu_service`.`menu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `menu_service`.`menu` (
                                                     `id` INT NOT NULL,
                                                     `name` VARCHAR(45) NOT NULL,
                                                     PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `menu_service`.`week_has_menu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `menu_service`.`week_has_menu` (
                                                              `week_id` INT NOT NULL,
                                                              `menu_id` INT NOT NULL,
                                                              PRIMARY KEY (`week_id`, `menu_id`),
                                                              INDEX `fk_week_has_menu_menu1_idx` (`menu_id` ASC),
                                                              INDEX `fk_week_has_menu_week_idx` (`week_id` ASC),
                                                              CONSTRAINT `fk_week_has_menu_week`
                                                                  FOREIGN KEY (`week_id`)
                                                                      REFERENCES `menu_service`.`week` (`id`)
                                                                      ON DELETE NO ACTION
                                                                      ON UPDATE NO ACTION,
                                                              CONSTRAINT `fk_week_has_menu_menu1`
                                                                  FOREIGN KEY (`menu_id`)
                                                                      REFERENCES `menu_service`.`menu` (`id`)
                                                                      ON DELETE NO ACTION
                                                                      ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `menu_service`.`unitofmeasure`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `menu_service`.`unitofmeasure` (
                                                              `id` INT NOT NULL,
                                                              `short_name` VARCHAR(45) NOT NULL,
                                                              `name` VARCHAR(45) NULL,
                                                              PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `menu_service`.`menu_has_product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `menu_service`.`menu_has_product` (
                                                                 `menu_id` INT NOT NULL,
                                                                 `product_id` INT NOT NULL,
                                                                 PRIMARY KEY (`menu_id`, `product_id`),
                                                                 INDEX `fk_menu_has_product_product1_idx` (`product_id` ASC),
                                                                 INDEX `fk_menu_has_product_menu1_idx` (`menu_id` ASC),
                                                                 CONSTRAINT `fk_menu_has_product_menu1`
                                                                     FOREIGN KEY (`menu_id`)
                                                                         REFERENCES `menu_service`.`menu` (`id`)
                                                                         ON DELETE NO ACTION
                                                                         ON UPDATE NO ACTION,
                                                                 CONSTRAINT `fk_menu_has_product_product1`
                                                                     FOREIGN KEY (`product_id`)
                                                                         REFERENCES `menu_service`.`product` (`id`)
                                                                         ON DELETE NO ACTION
                                                                         ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `menu_service`.`product_has_unitofmeasure`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `menu_service`.`product_has_unitofmeasure` (
                                                                          `product_id` INT NOT NULL,
                                                                          `unitofmeasure_id` INT NOT NULL,
                                                                          PRIMARY KEY (`product_id`, `unitofmeasure_id`),
                                                                          INDEX `fk_product_has_unitofmeasure_unitofmeasure1_idx` (`unitofmeasure_id` ASC),
                                                                          INDEX `fk_product_has_unitofmeasure_product1_idx` (`product_id` ASC),
                                                                          CONSTRAINT `fk_product_has_unitofmeasure_product1`
                                                                              FOREIGN KEY (`product_id`)
                                                                                  REFERENCES `menu_service`.`product` (`id`)
                                                                                  ON DELETE NO ACTION
                                                                                  ON UPDATE NO ACTION,
                                                                          CONSTRAINT `fk_product_has_unitofmeasure_unitofmeasure1`
                                                                              FOREIGN KEY (`unitofmeasure_id`)
                                                                                  REFERENCES `menu_service`.`unitofmeasure` (`id`)
                                                                                  ON DELETE NO ACTION
                                                                                  ON UPDATE NO ACTION)
    ENGINE = InnoDB;
