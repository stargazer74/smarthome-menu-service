CREATE SCHEMA IF NOT EXISTS `menu_service`;

-- -----------------------------------------------------
-- Table `menu_service`.`week`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `menu_service`.`week`
(
    `id`          INT AUTO_INCREMENT NOT NULL,
    `week_number` VARCHAR(45)        NOT NULL,
    `start_date`  VARCHAR(45)        NULL,
    `end_date`    VARCHAR(45)        NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `menu_service`.`unit_of_measure`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `menu_service`.`unit_of_measure`
(
    `id`         INT AUTO_INCREMENT NOT NULL,
    `short_name` VARCHAR(45)        NOT NULL,
    `name`       VARCHAR(45)        NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `menu_service`.`ingredient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `menu_service`.`ingredient`
(
    `id`                 INT AUTO_INCREMENT NOT NULL,
    `name`               VARCHAR(45)        NOT NULL,
    `external_id`        VARCHAR(45)        NULL,
    `amount`             VARCHAR(45)        NULL,
    `unit_of_measure_id` INT                NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_product_unit_of_measure1_idx` (`unit_of_measure_id` ASC),
    CONSTRAINT `fk_product_unit_of_measure1`
        FOREIGN KEY (`unit_of_measure_id`)
            REFERENCES `menu_service`.`unit_of_measure` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `menu_service`.`menu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `menu_service`.`menu`
(
    `id`   INT AUTO_INCREMENT NOT NULL,
    `name` VARCHAR(45)        NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `menu_service`.`week_has_menu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `menu_service`.`week_has_menu`
(
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
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `menu_service`.`menu_has_ingredient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `menu_service`.`menu_has_ingredient`
(
    `menu_id`       INT NOT NULL,
    `ingredient_id` INT NOT NULL,
    PRIMARY KEY (`menu_id`, `ingredient_id`),
    INDEX `fk_menu_has_ingredient_ingredient1_idx` (`ingredient_id` ASC),
    INDEX `fk_menu_has_ingredient_menu1_idx` (`menu_id` ASC),
    CONSTRAINT `fk_menu_has_ingredient_menu1`
        FOREIGN KEY (`menu_id`)
            REFERENCES `menu_service`.`menu` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_menu_has_ingredient_ingredient1`
        FOREIGN KEY (`ingredient_id`)
            REFERENCES `menu_service`.`ingredient` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

