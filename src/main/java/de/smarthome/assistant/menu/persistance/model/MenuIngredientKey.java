package de.smarthome.assistant.menu.persistance.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class MenuIngredientKey implements Serializable {

    @Column(name = "menu_id")
    private Long menuId;

    @Column(name = "ingredient_id")
    private Long ingredientId;
}
