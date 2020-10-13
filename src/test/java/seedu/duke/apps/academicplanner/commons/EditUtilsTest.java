package seedu.duke.apps.academicplanner.commons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.apps.moduleloader.exceptions.ModuleLoaderException;
import seedu.duke.objects.PartialModule;
import seedu.duke.objects.Person;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EditUtilsTest {

    ModuleLoader allModules;
    ModuleValidator moduleValidator;
    Person currentPerson;
    AddUtils addUtils;
    EditUtils editUtils;
    ArrayList<PartialModule> modulesList;

    @BeforeEach
    void setup() {
        try {
            allModules = new ModuleLoader();
            currentPerson = new Person("Bob");
            addUtils = new AddUtils(allModules,currentPerson);
            moduleValidator = new ModuleValidator(allModules,currentPerson);
            editUtils = new EditUtils(allModules, currentPerson);

            modulesList = currentPerson.getModulesList();
            assertEquals(modulesList.size(),0);

            addUtils.addModuleToUser("CS1010",1,"A+",4);
            assertEquals(modulesList.size(),1);
        } catch (ModuleLoaderException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void updateModuleGrade_nonEmptyList_success() {
        setup();

        editUtils.updateModuleGrade("CS1010", "A-");
        assertEquals(modulesList.size(), 1);
        for (PartialModule m : modulesList) {
            if (m.getModuleCode().equalsIgnoreCase("CS1010")) {
                assertEquals(m.getModuleCode(), "CS1010");
                assertTrue(moduleValidator.isValidGrade(m.getGrade()));
                assertEquals(m.getGrade(), "A-");
                assertEquals(m.getSemesterIndex(), 1);
                assertEquals(m.getModuleCredit(), 4);
            }
        }
    }

    @Test
    void updateModuleSemester_nonEmptyList_success() {
        setup();

        editUtils.updateModuleSemester("CS1010", "3");
        assertEquals(modulesList.size(), 1);
        for (PartialModule m : modulesList) {
            if (m.getModuleCode().equalsIgnoreCase("CS1010")) {
                assertEquals(m.getModuleCode(), "CS1010");
                assertEquals(m.getGrade(), "A+");
                assertTrue(moduleValidator.isValidSemester(m.getSemesterIndex()));
                assertEquals(m.getSemesterIndex(), 3);
                assertEquals(m.getModuleCredit(), 4);
            }
        }
    }
}
