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

import de.smarthome.assistant.menu.component.menu.MenuI;
import de.smarthome.assistant.menu.dto.MenuRequestDto;
import de.smarthome.assistant.menu.dto.MenuResponseDto;
import de.smarthome.assistant.menu.dto.MenuListDto;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/menu")
public class MenuController {

    private final MenuI menu;

    public MenuController(MenuI menu) {
        this.menu = menu;
    }

    /**
     * Insert a given {@link MenuRequestDto} into the database.
     *
     * @param menuRequestDto the menu dto
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MenuResponseDto> insert(@Valid @RequestBody MenuRequestDto menuRequestDto) {
        return this.menu.insert(menuRequestDto).map(a -> ResponseEntity.ok().body(a)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * Returns a WeekMenuListDto with a list of all menus stored in the database.
     *
     * @return ResponseEntity<WeekMenuListDto>
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<MenuListDto> list() {
        final Optional<MenuListDto> allMenus = this.menu.getAllMenus();
        return allMenus.map(a -> ResponseEntity.ok().body(a)).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
