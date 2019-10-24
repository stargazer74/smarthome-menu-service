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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import de.smarthome.assistant.menu.dto.MenuListDto;
import de.smarthome.assistant.menu.dto.MenuRequestDto;
import de.smarthome.assistant.menu.dto.MenuResponseDto;
import de.smarthome.assistant.menu.dto.mapper.MenuMapper;
import de.smarthome.assistant.menu.persistance.MenuResponseDtoBuilder;
import de.smarthome.assistant.menu.persistance.model.Menu;
import de.smarthome.assistant.menu.persistance.model.type.UnitOfMeasures;
import de.smarthome.assistant.menu.persistance.repository.MenuRepository;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MenuComponentTest {

    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void insertSuccessTest() {
        /*
         * prepare
         */
        final MenuResponseDto menuResponseDto = new MenuResponseDtoBuilder.Builder().asGriessbrei().build();
        final MenuRequestDto menuRequestDto = MenuMapper.INSTANCE.menuResponseDto2MenuRequestDto(menuResponseDto);

        /*
         * call
         */
        final MenuComponent menuComponent = new MenuComponent(this.menuRepository);
        final Optional<MenuResponseDto> menuResponse = menuComponent.insert(menuRequestDto);

        /*
         * test
         */
        assertTrue(menuResponse.isPresent());
        assertEquals("Grießbrei", menuResponse.get().getName());
        assertEquals(2, menuResponse.get().getIngredients().size());
        assertEquals(UnitOfMeasures.GRAMM, menuResponse.get().getIngredients().get(0).getUnitOfMeasure());
        assertEquals("Grieß", menuResponse.get().getIngredients().get(0).getName());
        assertEquals(200.5f, menuResponse.get().getIngredients().get(0).getAmount(), 0);
    }

    @Test
    public void getAllMenusSuccessTest() {
        /*
         * prepare
         */
        final MenuResponseDto menuResponseDto = new MenuResponseDtoBuilder.Builder().asGriessbrei().build();
        final MenuRequestDto menuRequestDto = MenuMapper.INSTANCE.menuResponseDto2MenuRequestDto(menuResponseDto);
        final Menu menu = MenuMapper.INSTANCE.menuRequestDto2Menu(menuRequestDto);
        this.menuRepository.save(menu);
        this.menuRepository.flush();

        /*
         * call
         */
        final MenuComponent menuComponent = new MenuComponent(this.menuRepository);
        final Optional<MenuListDto> allMenus = menuComponent.getAllMenus();

        /*
         * test
         */
        assertTrue(allMenus.isPresent());
        assertEquals(1, allMenus.get().getWeekMenuDtos().size());
        assertEquals("Grießbrei", allMenus.get().getWeekMenuDtos().get(0).getName());
    }
}
