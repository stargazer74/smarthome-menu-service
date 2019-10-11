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

package de.smarthome.assistant.menu.component.weekmenu;

import de.smarthome.assistant.menu.dto.IngredientsResponseDto;
import de.smarthome.assistant.menu.dto.MenuResponseDto;
import de.smarthome.assistant.menu.dto.WeekMenuListDto;
import de.smarthome.assistant.menu.dto.mapper.WeekMenuMapper;
import de.smarthome.assistant.menu.persistance.model.type.UnitOfMeasures;
import de.smarthome.assistant.menu.persistance.repository.WeekMenuRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class WeekMenuComponent implements WeekMenuI {

    private final WeekMenuRepository weekMenuRepository;

    public WeekMenuComponent(WeekMenuRepository weekMenuRepository) {
        this.weekMenuRepository = weekMenuRepository;
    }

    /**
     * Returns a Optional<WeekMenuListDto> with a list of all menus in the database.
     *
     * @return Optional<WeekMenuListDto>
     */
    public Optional<WeekMenuListDto> getAllMenus() {
        return Optional.of(mockData());

//        final List<MenuResponseDto> weekMenuDtos = weekMenuRepository.findAll().stream().map(WeekMenuMapper.INSTANCE::menu2MenuResponseDto)
//                .collect(Collectors.toList());
//
//        if(weekMenuDtos.isEmpty())
//            return Optional.empty();
//
//        final WeekMenuListDto weekMenuListDto = new WeekMenuListDto();
//        weekMenuListDto.setWeekMenuDtos(weekMenuDtos);
//        return Optional.of(weekMenuListDto);
    }

    private WeekMenuListDto mockData() {
        // create demo data
        final MenuResponseDto weekMenuResponseDto_1 = new MenuResponseDto();
        weekMenuResponseDto_1.setId(1L);
        weekMenuResponseDto_1.setName("Schweinefleisch mit Klößen");
        final IngredientsResponseDto ingredientResponse_1 = new IngredientsResponseDto();
        ingredientResponse_1.setId(1L);
        ingredientResponse_1.setName("Kartoffeln");
        ingredientResponse_1.setAmount("200");
        ingredientResponse_1.setUnitOfMeasure(UnitOfMeasures.ESSLOEFFEL);
        final IngredientsResponseDto ingredientResponse_2 = new IngredientsResponseDto();
        ingredientResponse_2.setId(2L);
        ingredientResponse_2.setName("Rinderfleisch");
        ingredientResponse_2.setAmount("500");
        ingredientResponse_2.setUnitOfMeasure(UnitOfMeasures.LITER);
        List<IngredientsResponseDto> ingredients_1 = new ArrayList<>();
        ingredients_1.add(ingredientResponse_1);
        ingredients_1.add(ingredientResponse_2);
        weekMenuResponseDto_1.setIngedients(ingredients_1);

        final MenuResponseDto weekMenuResponseDto_2 = new MenuResponseDto();
        weekMenuResponseDto_2.setId(2L);
        weekMenuResponseDto_2.setName("Grießbrei");
        final IngredientsResponseDto ingredientResponse_3 = new IngredientsResponseDto();
        ingredientResponse_3.setId(3L);
        ingredientResponse_3.setName("Grieß");
        ingredientResponse_3.setAmount("200");
        ingredientResponse_3.setUnitOfMeasure(UnitOfMeasures.LITER);
        final IngredientsResponseDto ingredientResponse_4 = new IngredientsResponseDto();
        ingredientResponse_4.setId(4L);
        ingredientResponse_4.setName("Zucker");
        ingredientResponse_4.setAmount("2");
        ingredientResponse_4.setUnitOfMeasure(UnitOfMeasures.KILOGRAMM);
        List<IngredientsResponseDto> ingredients_2 = new ArrayList<>();
        ingredients_2.add(ingredientResponse_3);
        ingredients_2.add(ingredientResponse_4);
        weekMenuResponseDto_2.setIngedients(ingredients_2);

        List<MenuResponseDto> weekMenuResponseDtos = new ArrayList<>();
        weekMenuResponseDtos.add(weekMenuResponseDto_1);
        weekMenuResponseDtos.add(weekMenuResponseDto_2);
        WeekMenuListDto weekMenuListResponseDto = new WeekMenuListDto();
        weekMenuListResponseDto.setWeekMenuDtos(weekMenuResponseDtos);
        return weekMenuListResponseDto;
    }
}
