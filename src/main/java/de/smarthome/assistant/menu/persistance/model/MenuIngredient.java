package de.smarthome.assistant.menu.persistance.model;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "menu_has_ingredient")
@Getter
@Setter
@EqualsAndHashCode
public class MenuIngredient {

    @EmbeddedId
    private MenuIngredientKey id;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @MapsId("menu_id")
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @MapsId("ingredient_id")
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    private Float amount;
}
