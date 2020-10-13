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
import static org.junit.jupiter.api.Assertions.assertFalse;

class AddUtilsTest {

    ModuleLoader allModules;
    Person currentPerson;
    AddUtils addUtils;
    ModuleValidator moduleValidator;

    @BeforeEach
    void setup() {
        try {
            allModules = new ModuleLoader();
            currentPerson = new Person("Bob");
            addUtils = new AddUtils(allModules,currentPerson);
            moduleValidator = new ModuleValidator(allModules,currentPerson);
        } catch (ModuleLoaderException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void addModuleToUser_emptyList_success() {
        setup();

        ArrayList<PartialModule> modulesList = currentPerson.getModulesList();
        assertEquals(modulesList.size(),0);
        addUtils.addModuleToUser("CS1010",1,"A+",4);
        assertEquals(modulesList.size(),1);

        for (PartialModule m : modulesList) {
            if (m.getModuleCode().equalsIgnoreCase("CS1010")) {
                assertEquals(m.getModuleCode(),"CS1010");
                assertEquals(m.getSemesterIndex(),1);
                assertEquals(m.getGrade(),"A+");
                assertEquals(m.getModuleCredit(),4);
            }
        }
        assertTrue(moduleValidator.isModTakenByUser("CS1010"));
        assertFalse(moduleValidator.isModTakenByUser("CS2113"));
    }

    @Test
    void addModuleToUser_nonEmptyList_success() {
        setup();

        ArrayList<PartialModule> modulesList = currentPerson.getModulesList();
        assertEquals(modulesList.size(),0);

        addUtils.addModuleToUser("CS1010",1,"A+",4);
        assertEquals(modulesList.size(),1);

        addUtils.addModuleToUser("CS1231",1,"B-",4);
        assertEquals(modulesList.size(),2);

        for (PartialModule m : modulesList) {
            if (m.getModuleCode().equalsIgnoreCase("CS1010")) {
                assertEquals(m.getModuleCode(),"CS1010");
                assertEquals(m.getSemesterIndex(),1);
                assertEquals(m.getGrade(),"A+");
                assertEquals(m.getModuleCredit(),4);
            } else {
                assertEquals(m.getModuleCode(),"CS1231");
                assertEquals(m.getSemesterIndex(),1);
                assertEquals(m.getGrade(),"B-");
                assertEquals(m.getModuleCredit(),4);
            }
        }
        assertTrue(moduleValidator.isModTakenByUser("CS1010"));
        assertTrue(moduleValidator.isModTakenByUser("CS1231"));
        assertFalse(moduleValidator.isModTakenByUser("CS2113"));
    }
}
