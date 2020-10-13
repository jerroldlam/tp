package seedu.duke.apps.academicplanner.commons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.apps.moduleloader.exceptions.ModuleLoaderException;
import seedu.duke.objects.PartialModule;
import seedu.duke.objects.Person;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemoveUtilsTest {

    ModuleLoader allModules;
    Person currentPerson;
    ModuleValidator moduleValidator;
    AddUtils addUtils;
    RemoveUtils removeUtils;

    @BeforeEach
    void setup() {
        try {
            allModules = new ModuleLoader();
            currentPerson = new Person("Bob");
            addUtils = new AddUtils(allModules,currentPerson);
            moduleValidator = new ModuleValidator(allModules,currentPerson);
            removeUtils = new RemoveUtils(currentPerson);
        } catch (ModuleLoaderException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void removeModuleFromUserModuleList_nonEmptyList_success() {
        setup();

        ArrayList<PartialModule> modulesList = currentPerson.getModulesList();
        assertEquals(modulesList.size(),0);
        addUtils.addModuleToUser("CS1010",1,"A+",4);
        assertEquals(modulesList.size(),1);
        removeUtils.removeModuleFromUserModuleList("CS1010");
        assertEquals(modulesList.size(), 0);
    }
}
