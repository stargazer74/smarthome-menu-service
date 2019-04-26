package de.smarthome.assistant.menu.persistance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_Sequence")
    @SequenceGenerator(name = "product_Sequence", sequenceName = "PRODUCT_SEQ")
    private Long id;

}
