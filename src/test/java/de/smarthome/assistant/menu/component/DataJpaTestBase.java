package de.smarthome.assistant.menu.component;


import de.smarthome.assistant.menu.TestBase;
import de.smarthome.assistant.menu.persistance.repository.IngredientRepository;
import de.smarthome.assistant.menu.persistance.repository.MenuRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@Transactional
public class DataJpaTestBase extends TestBase {

    @Autowired
    protected MenuRepository menuRepository;

    @Autowired
    protected IngredientRepository ingredientRepository;

}
