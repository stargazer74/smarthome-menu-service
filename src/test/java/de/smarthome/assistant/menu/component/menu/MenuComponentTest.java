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

import static org.junit.Assert.*;

import de.smarthome.assistant.menu.dto.IngredientsRequestDto;
import de.smarthome.assistant.menu.dto.MenuRequestDto;
import de.smarthome.assistant.menu.dto.MenuResponseDto;
import de.smarthome.assistant.menu.persistance.model.type.UnitOfMeasures;
import de.smarthome.assistant.menu.persistance.repository.MenuRepository;
import java.util.ArrayList;
import java.util.List;
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
        final IngredientsRequestDto ingredientsRequestDto = new IngredientsRequestDto();
        ingredientsRequestDto.setAmount(2.5f);
        ingredientsRequestDto.setName("Grieß");
        ingredientsRequestDto.setUnitOfMeasureName(UnitOfMeasures.LITER);

        List<IngredientsRequestDto> ingredientsRequestDtos = new ArrayList<>();
        ingredientsRequestDtos.add(ingredientsRequestDto);

        final MenuRequestDto menuRequestDto = new MenuRequestDto();
        menuRequestDto.setIngredients(ingredientsRequestDtos);
        menuRequestDto.setName("Grießbrei");

        final MenuComponent menuComponent = new MenuComponent(this.menuRepository);
        final Optional<MenuResponseDto> menuResponseDto = menuComponent.insert(menuRequestDto);

        /*
         * test
         */
        assertTrue(menuResponseDto.isPresent());
        assertEquals("Grießbrei", menuResponseDto.get().getName());

    }
}
