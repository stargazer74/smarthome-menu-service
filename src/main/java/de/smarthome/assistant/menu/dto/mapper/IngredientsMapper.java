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

package de.smarthome.assistant.menu.dto.mapper;

import de.smarthome.assistant.menu.dto.IngredientsRequestDto;
import de.smarthome.assistant.menu.dto.IngredientsResponseDto;
import de.smarthome.assistant.menu.persistance.model.Ingredient;
import de.smarthome.assistant.menu.persistance.model.MenuIngredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IngredientsMapper {

    IngredientsMapper INSTANCE = Mappers.getMapper(IngredientsMapper.class);

    @Mappings({ @Mapping(source = "unitOfMeasure", target = "ingredient.unitOfMeasure.name"),
            @Mapping(source = "id", target = "id.ingredientId"), @Mapping(source = "name", target = "ingredient.name") })
    MenuIngredient ingredientsRequestDto2Ingredient(IngredientsRequestDto ingredientsRequestDto);

    @Mappings({ @Mapping(source = "ingredient.unitOfMeasure.name", target = "unitOfMeasure"),
            @Mapping(source = "id.ingredientId", target = "id") })
    IngredientsResponseDto ingredient2IngredientsResponseDto(MenuIngredient ingredient);
}
