package de.smarthome.assistant.menu.persistance.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class MenuIngredientKey implements Serializable {

    @Column(name = "menu_id")
    private Long menuId;

    @Column(name = "ingredient_id")
    private Long ingredientId;
}
