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
import de.smarthome.assistant.menu.dto.MenuRequestDto;
import de.smarthome.assistant.menu.dto.MenuResponseDto;
import de.smarthome.assistant.menu.persistance.model.Menu;
import java.util.List;
import java.util.stream.Collectors;

import de.smarthome.assistant.menu.persistance.model.MenuIngredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    MenuMapper INSTANCE = Mappers.getMapper(MenuMapper.class);

    @Mappings({ @Mapping(source = "ingredients", target = "ingredients", qualifiedByName = "ingredientRequestMap") })
    Menu menuRequestDto2Menu(MenuRequestDto menuRequestDto);

    @Mappings({ @Mapping(source = "ingredients", target = "ingredients", qualifiedByName = "ingredientsMap") })
    MenuResponseDto menu2MenuResponseDto(Menu menu);

    MenuRequestDto menuResponseDto2MenuRequestDto(MenuResponseDto menuResponseDto);

    @Named("ingredientsMap")
    default List<IngredientsResponseDto> ingredientsMap(List<MenuIngredient> ingredients) {
        return ingredients.stream().map(IngredientsMapper.INSTANCE::ingredient2IngredientsResponseDto).collect(Collectors.toList());
    }

    @Named("ingredientRequestMap")
    default List<MenuIngredient> ingredientRequestMap(List<IngredientsRequestDto> ingredientsRequestDtos) {
        return ingredientsRequestDtos.stream().map(IngredientsMapper.INSTANCE::ingredientsRequestDto2Ingredient)
                .collect(Collectors.toList());
    }
}
