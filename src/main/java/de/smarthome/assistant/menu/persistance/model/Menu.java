package de.smarthome.assistant.menu.persistance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_Sequence")
    @SequenceGenerator(name = "menu_Sequence", sequenceName = "MENU_SEQ")
    private Long id;

}
