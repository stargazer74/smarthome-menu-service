/*
 * Copyright (c) 2019. Chris Wohlbrecht
 *
 * MIT License
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package de.smarthome.assistant.menu.persistance;

import com.google.common.base.Strings;
import de.smarthome.assistant.menu.dto.IngredientsResponseDto;
import de.smarthome.assistant.menu.dto.MenuResponseDto;
import de.smarthome.assistant.menu.persistance.model.type.UnitOfMeasures;
import java.util.ArrayList;
import java.util.List;

public class MenuResponseDtoBuilder {

    private MenuResponseDtoBuilder() {
    }

    public static class Builder {

        private MenuResponseDto menuResponseDto = new MenuResponseDto();

        private Long id;

        private String name;

        private List<IngredientsResponseDto> ingredientsResponseDtos;

        public Builder withIngredients(List<IngredientsResponseDto> ingredientsResponseDtos) {
            this.ingredientsResponseDtos = ingredientsResponseDtos;
            return this;
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder asGriessbrei() {
            final IngredientsResponseDto ingredient_1 = new IngredientsResponseDto();
            ingredient_1.setName("Grieß");
            ingredient_1.setAmount(200.5f);
            ingredient_1.setId(1L);
            ingredient_1.setUnitOfMeasure(UnitOfMeasures.GRAMM);
            final IngredientsResponseDto ingredient_2 = new IngredientsResponseDto();
            ingredient_2.setName("Milch");
            ingredient_2.setAmount(1f);
            ingredient_2.setId(2L);
            ingredient_2.setUnitOfMeasure(UnitOfMeasures.LITER);
            List<IngredientsResponseDto> ingredients = new ArrayList<>();
            ingredients.add(ingredient_1);
            ingredients.add(ingredient_2);
            this.withIngredients(ingredients);
            this.withId(1L);
            this.withName("Grießbrei");
            return this;
        }

        public Builder asKartoffelSuppe() {
            final IngredientsResponseDto ingredient_1 = new IngredientsResponseDto();
            ingredient_1.setName("Kartoffeln");
            ingredient_1.setAmount(1.5f);
            ingredient_1.setId(3L);
            ingredient_1.setUnitOfMeasure(UnitOfMeasures.KILOGRAMM);
            final IngredientsResponseDto ingredient_2 = new IngredientsResponseDto();
            ingredient_2.setName("Wiener Würstchen");
            ingredient_2.setAmount(800f);
            ingredient_2.setId(4L);
            ingredient_2.setUnitOfMeasure(UnitOfMeasures.GRAMM);
            List<IngredientsResponseDto> ingredients = new ArrayList<>();
            ingredients.add(ingredient_1);
            ingredients.add(ingredient_2);
            this.withIngredients(ingredients);
            this.withId(2L);
            this.withName("Kartoffelsuppe");
            return this;
        }

        public MenuResponseDto build() {
            if (!Strings.isNullOrEmpty(this.name))
                this.menuResponseDto.setName(this.name);
            if (this.id != null)
                this.menuResponseDto.setId(this.id);
            if (this.ingredientsResponseDtos.size() > 0)
                this.menuResponseDto.setIngredients(this.ingredientsResponseDtos);
            return this.menuResponseDto;
        }
    }
}
