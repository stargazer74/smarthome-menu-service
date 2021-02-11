package de.smarthome.assistant.menu.controller;

import de.smarthome.assistant.menu.TestBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
abstract class ControllerTestBase extends TestBase {

    @Autowired
    protected MockMvc mockMvc;

}
