package de.smarthome.assistant.menu.persistance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "week")
public class Week {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "week_Sequence")
    @SequenceGenerator(name = "week_Sequence", sequenceName = "WEEK_SEQ")
    private Long id;

}
