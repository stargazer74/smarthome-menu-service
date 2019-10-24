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

package de.smarthome.assistant.menu.component.menu;

import de.smarthome.assistant.menu.dto.IngredientsResponseDto;
import de.smarthome.assistant.menu.dto.MenuRequestDto;
import de.smarthome.assistant.menu.dto.MenuResponseDto;
import de.smarthome.assistant.menu.dto.MenuListDto;
import de.smarthome.assistant.menu.dto.mapper.MenuMapper;
import de.smarthome.assistant.menu.persistance.model.type.UnitOfMeasures;
import de.smarthome.assistant.menu.persistance.repository.MenuRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class MenuComponent implements MenuI {

    private final MenuRepository menuRepository;

    public MenuComponent(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    /**
     * Saves the incoming {@link MenuRequestDto} and returns an Optional of {@link MenuResponseDto}
     *
     * @param menuRequestDto a {@link MenuRequestDto} object
     * @return Optional of {@link MenuResponseDto}
     */
    @Override
    public Optional<MenuResponseDto> insert(MenuRequestDto menuRequestDto) {
        return Optional.ofNullable(
                MenuMapper.INSTANCE.menu2MenuResponseDto(menuRepository.save(MenuMapper.INSTANCE.menuRequestDto2Menu(menuRequestDto))));
    }

    /**
     * Returns a Optional<WeekMenuListDto> with a list of all menus in the database.
     *
     * @return Optional<WeekMenuListDto>
     */
    public Optional<MenuListDto> getAllMenus() {
        final List<MenuResponseDto> weekMenuDtos = menuRepository.findAll().stream().map(MenuMapper.INSTANCE::menu2MenuResponseDto)
                .collect(Collectors.toList());

        if (weekMenuDtos.isEmpty())
            return Optional.empty();

        final MenuListDto weekMenuListDto = new MenuListDto();
        weekMenuListDto.setWeekMenuDtos(weekMenuDtos);
        return Optional.of(weekMenuListDto);
    }
}
