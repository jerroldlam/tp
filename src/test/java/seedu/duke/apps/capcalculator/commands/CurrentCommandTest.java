package seedu.duke.apps.capcalculator.commands;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.apps.academicplanner.commons.AddUtils;
import seedu.duke.apps.academicplanner.commons.EditUtils;
import seedu.duke.apps.academicplanner.commons.RemoveUtils;
import seedu.duke.apps.academicplanner.exceptions.AcademicException;
import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.apps.moduleloader.exceptions.ModuleLoaderException;
import seedu.duke.global.objects.Person;
import seedu.duke.ui.Ui;

//@@author jerroldlam
class CurrentCommandTest {
    Person currentPerson;
    ModuleLoader allModules;
    AddUtils addUtils;
    RemoveUtils removeUtils;
    EditUtils editUtils;

    @BeforeEach
    void setup() throws ModuleLoaderException {
        currentPerson = new Person("Bob");
        allModules = new ModuleLoader();
        addUtils = new AddUtils(allModules,currentPerson);
        removeUtils = new RemoveUtils(new Ui(), currentPerson);
        editUtils = new EditUtils(allModules, currentPerson);
    }

    @Test
    void execute_populatedList_result() {
        addUtils.addModuleToUser("CS1010",1,"A-",4);
        addUtils.addModuleToUser("CS1231",1,"B",4);
        addUtils.addModuleToUser("CG1111",1,"B+",6);
        addUtils.addModuleToUser("MA1511",1,"S",4);
        addUtils.addModuleToUser("MA1512",1,"S",4);

        CurrentCommand currentCommand = new CurrentCommand(currentPerson);
        double currentCap = currentCommand.getCurrentCap();
        assertEquals(4.0, currentCap);
    }

    @Test
    void execute_populatedListAllSU_fail() {
        addUtils.addModuleToUser("CS1010",1,"S",4);
        addUtils.addModuleToUser("CS1231",1,"S",4);
        addUtils.addModuleToUser("CG1111",1,"S",6);
        addUtils.addModuleToUser("MA1511",1,"S",4);
        addUtils.addModuleToUser("MA1512",1,"S",4);

        CurrentCommand currentCommand = new CurrentCommand(currentPerson);
        double currentCap = currentCommand.getCurrentCap();
        boolean isNan = Double.isNaN(currentCap);
        assertTrue(isNan);
    }

    @Test
    void execute_emptyList_fail() {
        CurrentCommand currentCommand = new CurrentCommand(currentPerson);
        double currentCap = currentCommand.getCurrentCap();
        boolean isNan = Double.isNaN(currentCap);
        assertTrue(isNan);
    }

    @Test
    void execute_addOneRemoveOne_fail() {
        try {
            addUtils.addModuleToUser("CS1010", 1, "S", 4);
            removeUtils.removeModuleFromUserModuleList("CS1010");
            CurrentCommand currentCommand = new CurrentCommand(currentPerson);
            double currentCap = currentCommand.getCurrentCap();
            boolean isNan = Double.isNaN(currentCap);
            assertTrue(isNan);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void execute_addTwoRemoveOne_result() {
        try {
            addUtils.addModuleToUser("CS1010", 1, "S", 4);
            addUtils.addModuleToUser("CS2040", 1, "A", 4);
            removeUtils.removeModuleFromUserModuleList("CS1010");
            CurrentCommand currentCommand = new CurrentCommand(currentPerson);
            double currentCap = currentCommand.getCurrentCap();
            assertEquals(5.0, currentCap);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void execute_addTwoRemoveOne_fail() {
        try {
            addUtils.addModuleToUser("CS1010", 1, "A", 4);
            addUtils.addModuleToUser("CS2040", 1, "S", 4);
            removeUtils.removeModuleFromUserModuleList("CS1010");
            CurrentCommand currentCommand = new CurrentCommand(currentPerson);
            double currentCap = currentCommand.getCurrentCap();
            boolean isNan = Double.isNaN(currentCap);
            assertTrue(isNan);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void execute_addOneEditOne_result() throws AcademicException {
        ArrayList<Integer> testArr = new ArrayList<>();
        testArr.add(0);

        addUtils.addModuleToUser("CS1010",1,"A",4);
        CurrentCommand currentCommand = new CurrentCommand(currentPerson);
        double currentCap = currentCommand.getCurrentCap();
        assertEquals(5, currentCap);
        editUtils.updateModuleGrade("A-", 0, testArr);
        currentCap = currentCommand.getCurrentCap();
        assertEquals(4.5, currentCap);
    }

    @Test
    void execute_addOneEditOne_fail() throws AcademicException {
        ArrayList<Integer> testArr = new ArrayList<>();
        testArr.add(0);

        addUtils.addModuleToUser("CS1010",1,"A",4);
        CurrentCommand currentCommand = new CurrentCommand(currentPerson);
        double currentCap = currentCommand.getCurrentCap();
        assertEquals(5, currentCap);
        editUtils.updateModuleGrade("S", 0, testArr);
        currentCap = currentCommand.getCurrentCap();
        boolean isNan = Double.isNaN(currentCap);
        assertTrue(isNan);
    }
}
