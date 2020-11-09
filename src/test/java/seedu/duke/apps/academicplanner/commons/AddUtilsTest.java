package seedu.duke.apps.academicplanner.commons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.apps.moduleloader.exceptions.ModuleLoaderException;
import seedu.duke.global.objects.PartialModule;
import seedu.duke.global.objects.Person;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

//@@author jerroldlam
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
        assertEquals(0,modulesList.size());
        addUtils.addModuleToUser("CS1010",1,"A+",4);
        assertEquals(1,modulesList.size());

        for (PartialModule m : modulesList) {
            if (m.getModuleCode().equalsIgnoreCase("CS1010")) {
                assertEquals("CS1010",m.getModuleCode());
                assertEquals(1,m.getSemesterIndex());
                assertEquals("A+",m.getGrade());
                assertEquals(4,m.getModuleCredit());
            }
        }
        assertTrue(moduleValidator.isModTakenByUser("CS1010"));
        assertFalse(moduleValidator.isModTakenByUser("CS2113"));
    }

    @Test
    void addModuleToUser_nonEmptyList_success() {
        setup();

        ArrayList<PartialModule> modulesList = currentPerson.getModulesList();
        assertEquals(0,modulesList.size());

        addUtils.addModuleToUser("CS1010",1,"A+",4);
        assertEquals(1,modulesList.size());

        addUtils.addModuleToUser("CS1231",1,"B-",4);
        assertEquals(2,modulesList.size());

        for (PartialModule m : modulesList) {
            if (m.getModuleCode().equalsIgnoreCase("CS1010")) {
                assertEquals("CS1010",m.getModuleCode());
                assertEquals(1,m.getSemesterIndex());
                assertEquals("A+",m.getGrade());
                assertEquals(4,m.getModuleCredit());
            } else {
                assertEquals("CS1231",m.getModuleCode());
                assertEquals(1,m.getSemesterIndex());
                assertEquals("B-",m.getGrade());
                assertEquals(4,m.getModuleCredit());
            }
        }
        assertTrue(moduleValidator.isModTakenByUser("CS1010"));
        assertTrue(moduleValidator.isModTakenByUser("CS1231"));
        assertFalse(moduleValidator.isModTakenByUser("CS2113"));
    }
}
