package de.smarthome.assistant.menu.persistance.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "menu_has_ingredient")
@Data
public class MenuIngredient {

    @EmbeddedId
    private MenuIngredientKey id;

    @ManyToOne
    @MapsId("menu_id")
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToOne
    @MapsId("ingredient_id")
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;
}
