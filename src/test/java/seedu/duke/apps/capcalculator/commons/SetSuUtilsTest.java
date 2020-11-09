package seedu.duke.apps.capcalculator.commons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.apps.academicplanner.commons.AddUtils;
import seedu.duke.apps.capcalculator.exceptions.CapCalculatorException;
import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.apps.moduleloader.exceptions.ModuleLoaderException;
import seedu.duke.global.objects.PartialModule;
import seedu.duke.global.objects.Person;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//@@author JuZihao
class SetSuUtilsTest {
    private static final String EMPTY_LIST_ERROR = "Looks like your S/U list is empty!";
    private static final String INVALID_MODULE_ERROR = "Looks like you did not take the module you entered "
                        + "or the module you entered has a grade that cannot be S/Ued!";
    private static final String EXITING_CURRENT_COMMAND =
            "Exiting current command back to CAP Calculator App Main Menu.";
    private static final String NEW_LINE = "\n";
    private static final String DUPLICATE_MODULE_ERROR = "Looks like you have already added this module!";

    Person currentPerson;
    AddUtils addUtils;
    SetSuUtils setSuUtils;
    ModuleLoader allModules;
    Ui ui;
    ArrayList<PartialModule> suList;
    PartialModule suModule;
    ArrayList<PartialModule> filteredList;

    @BeforeEach
    void setup() {
        try {
            currentPerson = new Person("Bobby");
            allModules = new ModuleLoader();
            addUtils = new AddUtils(allModules,currentPerson);
            ui = new Ui();

            addUtils.addModuleToUser("CS1010",1,"A-",4);
            addUtils.addModuleToUser("CS1231",1,"B",4);
            addUtils.addModuleToUser("CG1111",1,"B+",6);
            addUtils.addModuleToUser("MA1511",1,"S",4);
            addUtils.addModuleToUser("MA1512",1,"S",4);

            addUtils.addModuleToUser("CS2040C",2,"A-",4);
            addUtils.addModuleToUser("CG1112",2,"B",4);
            addUtils.addModuleToUser("MA1508E",2,"S",4);
            addUtils.addModuleToUser("GER1000",2,"A",4);
            addUtils.addModuleToUser("GET1002",2,"B+",4);

            setSuUtils = new SetSuUtils(currentPerson, ui);
            filteredList = (ArrayList<PartialModule>) currentPerson.getModulesList().stream()
                    .filter((partialModule) -> partialModule.getCap() >= 0)
                    .collect(toList());
            suList = new ArrayList<>();

        } catch (ModuleLoaderException e) {
            e.printStackTrace();
        }
    }

    @Test
    void execute_getSuListBySemester_result() {
        try {
            suList = setSuUtils.getSuListBySemester(1);
            assertEquals(3, suList.size());

            suList = setSuUtils.getSuListBySemester(2);
            assertEquals(4, suList.size());
        } catch (CapCalculatorException e) {
            fail();
        }
    }

    @Test
    void execute_getSuListBySemester_exceptionThrown() {
        try {
            suList = setSuUtils.getSuListBySemester(4);
            assertEquals(0, suList.size());
        } catch (CapCalculatorException e) {
            assertEquals(EMPTY_LIST_ERROR + NEW_LINE + EXITING_CURRENT_COMMAND, e.getMessage());
        }
    }

    @Test
    void execute_getSuModule_result() {
        try {
            suModule = setSuUtils.getSuModule(filteredList, "CS1010");
            assertEquals("CS1010", suModule.getModuleCode());
            assertEquals(4.50, suModule.getCap());
            assertEquals(4, suModule.getModuleCredit());

            suModule = setSuUtils.getSuModule(filteredList, "CS1231");
            assertEquals("CS1231", suModule.getModuleCode());
            assertEquals(3.50,suModule.getCap());
            assertEquals(4, suModule.getModuleCredit());
        } catch (CapCalculatorException e) {
            fail();
        }
    }

    @Test
    void execute_getSuModule_exceptionThrown() {
        try {
            suModule = setSuUtils.getSuModule(filteredList, "MA1511");
        } catch (CapCalculatorException e) {
            assertEquals(INVALID_MODULE_ERROR + NEW_LINE + EXITING_CURRENT_COMMAND, e.getMessage());
        }
    }

    @Test
    void execute_addModuleToSuList_results() {
        try {
            suModule = setSuUtils.getSuModule(filteredList, "CS1231");
            setSuUtils.addModuleToSuList(suList,suModule);
            assertEquals(1, suList.size());

            suModule = setSuUtils.getSuModule(filteredList, "CS1010");
            setSuUtils.addModuleToSuList(suList,suModule);
            assertEquals(2, suList.size());
        } catch (CapCalculatorException e) {
            fail();
        }
    }

    @Test
    void execute_addModuleToSuList_exceptionThrown() {
        try {
            suModule = setSuUtils.getSuModule(filteredList, "CS1231");
            setSuUtils.addModuleToSuList(suList,suModule);
            suModule = setSuUtils.getSuModule(filteredList, "CS1231");
            setSuUtils.addModuleToSuList(suList,suModule);
        } catch (CapCalculatorException e) {
            assertEquals(DUPLICATE_MODULE_ERROR + NEW_LINE + EXITING_CURRENT_COMMAND, e.getMessage());
        }
    }
}