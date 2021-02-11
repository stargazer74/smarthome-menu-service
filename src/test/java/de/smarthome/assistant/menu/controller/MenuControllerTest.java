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

package de.smarthome.assistant.menu.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import de.smarthome.assistant.menu.component.menu.MenuI;
import de.smarthome.assistant.menu.dto.MenuListDto;
import de.smarthome.assistant.menu.dto.MenuResponseDto;
import de.smarthome.assistant.menu.persistance.MenuResponseDtoBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

public class MenuControllerTest extends ControllerTestBase{

    @MockBean
    private MenuI menu;

    private MenuListDto menuListDto;

    @BeforeEach
    public void setUp() {
        this.menuListDto = new MenuListDto();
        final MenuResponseDto menuResponseDto_1 = new MenuResponseDtoBuilder.Builder().asGriessbrei().build();
        final MenuResponseDto menuResponseDto_2 = new MenuResponseDtoBuilder.Builder().asKartoffelSuppe().build();
        List<MenuResponseDto> responseDtos = new ArrayList<>();
        responseDtos.add(menuResponseDto_1);
        responseDtos.add(menuResponseDto_2);
        menuListDto.setMenuDtos(responseDtos);
    }

    @Test
    public void insertSuccessTest() {
        assertTrue(true);
    }

    @Test
    public void getAllMenusSuccessTest() throws Exception {
        /*
         * prepare
         */
        given(menu.getAllMenus()).willReturn(Optional.of(this.menuListDto));

        /*
         * test
         */
        mockMvc.perform(get("/menu/list").characterEncoding("UTF-8").contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(200))
                .andExpect(jsonPath("$.menuDtos[0].name").value("Grießbrei")).
                andExpect(jsonPath("$.menuDtos[1].name").value("Kartoffelsuppe"));
    }

    @Test
    public void deleteSuccessTest() throws Exception {
        /*
         * test
         */
        mockMvc.perform(delete("/menu/123")).andExpect(status().isOk());
    }
}
