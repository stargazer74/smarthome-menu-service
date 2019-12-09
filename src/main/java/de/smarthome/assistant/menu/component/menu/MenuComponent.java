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

import de.smarthome.assistant.menu.dto.MenuRequestDto;
import de.smarthome.assistant.menu.dto.MenuResponseDto;
import de.smarthome.assistant.menu.dto.MenuListDto;
import de.smarthome.assistant.menu.dto.mapper.MenuMapper;
import de.smarthome.assistant.menu.persistance.model.Ingredient;
import de.smarthome.assistant.menu.persistance.model.Menu;
import de.smarthome.assistant.menu.persistance.repository.IngredientRepository;
import de.smarthome.assistant.menu.persistance.repository.MenuRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class MenuComponent implements MenuI {

    private final MenuRepository menuRepository;

    private final IngredientRepository ingredientRepository;

    public MenuComponent(MenuRepository menuRepository, IngredientRepository ingredientRepository) {
        this.menuRepository = menuRepository;
        this.ingredientRepository = ingredientRepository;
    }

    /**
     * Saves the incoming {@link MenuRequestDto} and returns an Optional of {@link MenuResponseDto}
     *
     * @param menuRequestDto a {@link MenuRequestDto} object
     * @return Optional of {@link MenuResponseDto}
     */
    @Override
    public Optional<MenuResponseDto> insert(MenuRequestDto menuRequestDto) {
        // find ingredients with existing names to prevent constraint violation exception
        menuRequestDto.getIngredients().forEach(a -> {
            final Optional<Ingredient> ingredient = this.ingredientRepository.findByName(a.getName());
            ingredient.ifPresent(value -> a.setId(value.getId()));
        });
        final Menu menu = MenuMapper.INSTANCE.menuRequestDto2Menu(menuRequestDto);
        menu.getIngredients().forEach(a -> a.setMenu(menu));
        Menu savedMenu = menuRepository.save(menu);
        MenuResponseDto returnValue = MenuMapper.INSTANCE.menu2MenuResponseDto(savedMenu);
        return Optional.ofNullable(returnValue);
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
        weekMenuListDto.setMenuDtos(weekMenuDtos);
        return Optional.of(weekMenuListDto);
    }
}
