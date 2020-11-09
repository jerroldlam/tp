package seedu.duke.apps.academicplanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.apps.academicplanner.commands.AddModuleCommand;
import seedu.duke.apps.academicplanner.commands.EditModuleCommand;
import seedu.duke.apps.academicplanner.commands.RemoveModuleCommand;
import seedu.duke.apps.academicplanner.commands.PrintHelpCommand;
import seedu.duke.apps.academicplanner.commands.PrintCalenderCommand;
import seedu.duke.apps.academicplanner.commands.SearchModulesCommand;
import seedu.duke.apps.academicplanner.commands.ModuleDetailsCommand;
import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.global.Command;
import seedu.duke.global.objects.Person;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

//@@author jerroldlam
class AcademicPlannerParserTest {
    private static final String ERROR_INVALID_COMMAND = "INVALID COMMAND";
    public static final String NEW_LINE = "\n";
    private static final String EXITING_CURRENT_COMMAND = "Exiting current command back to Academic Planner Main Menu.";
    private static final String ERROR_IN_ACADPLAN = "Sorry, you are already in Academic Planner!";
    private static final String ERROR_NO_PARAMETER = " NO PARAMETER AFTER COMMAND";
    private static final String ERROR_HAS_PARAMETER = " NO PARAMETER ALLOWED AFTER COMMAND";

    private Person currentPerson;
    private Ui ui;
    private ModuleLoader allModules;
    private Storage storage;

    @BeforeEach
    public void setup() {
        try {
            currentPerson = new Person("Bob");
            ui = new Ui();
            allModules = new ModuleLoader();
            storage = new Storage(allModules);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void parse_emptyInput_exceptionThrown() {
        try {
            final String emptyInput = "";
            AcademicPlannerParser.parse(emptyInput,allModules,currentPerson,ui,storage);
        } catch (Exception e) {
            assertEquals(ERROR_INVALID_COMMAND + NEW_LINE + EXITING_CURRENT_COMMAND, e.getMessage());
        }
    }

    @Test
    void parse_randomInput_exceptionThrown() {
        try {
            final String randomInput = "i am a gummy bear";
            AcademicPlannerParser.parse(randomInput,allModules,currentPerson,ui,storage);
        } catch (Exception e) {
            assertEquals(ERROR_INVALID_COMMAND + NEW_LINE + EXITING_CURRENT_COMMAND, e.getMessage());
        }
    }

    @Test
    void parse_addModuleCommand_correct() {
        final String input = "add cs1010";
        parseAndAssertCommandType(input,AddModuleCommand.class,allModules,currentPerson,ui,storage);
    }

    @Test
    void parse_editModuleCommand_correct() {
        final String input = "edit cs1010";
        parseAndAssertCommandType(input, EditModuleCommand.class,allModules,currentPerson,ui,storage);
    }

    @Test
    void parse_moduleDetails_correct() {
        final String input = "details cs1010";
        parseAndAssertCommandType(input,ModuleDetailsCommand.class,allModules,currentPerson,ui,storage);
    }

    @Test
    void parse_addModuleCommandWithSpaces_correct() {
        final String input = "   add    cs1010    ";
        parseAndAssertCommandType(input,AddModuleCommand.class,allModules,currentPerson,ui,storage);
    }

    @Test
    void parse_editModuleCommandWithSpaces_correct() {
        final String input = "   edit     cs1010   ";
        parseAndAssertCommandType(input, EditModuleCommand.class,allModules,currentPerson,ui,storage);
    }

    @Test
    void parse_moduleDetailsWithSpaces_correct() {
        final String input = "   details    cs1010   ";
        parseAndAssertCommandType(input,ModuleDetailsCommand.class,allModules,currentPerson,ui,storage);
    }

    @Test
    void parse_printModuleCommand_correct() {
        final String input = "view";
        parseAndAssertCommandType(input, PrintCalenderCommand.class,allModules,currentPerson,ui,storage);
    }

    @Test
    void parse_printHelpCommand_correct() {
        final String input = "help";
        parseAndAssertCommandType(input, PrintHelpCommand.class,allModules,currentPerson,ui,storage);
    }

    @Test
    void parse_removeModuleCommand_correct() {
        final String input = "remove cs1010";
        parseAndAssertCommandType(input, RemoveModuleCommand.class,allModules,currentPerson,ui,storage);
    }

    @Test
    void parse_searchModuleCommand_correct() {
        final String input = "search cs10";
        parseAndAssertCommandType(input,SearchModulesCommand.class,allModules,currentPerson,ui,storage);
    }

    @Test
    void parse_capCalcCommand_correct() {
        final String input = "capcalc";
        parseAndAssertCommandType(input,Command.class,allModules,currentPerson,ui,storage);
    }

    @Test
    void parse_exitCommand_correct() {
        final String input = "exit";
        parseAndAssertCommandType(input,Command.class,allModules,currentPerson,ui,storage);
    }

    @Test
    void parse_acadPlanCommand_exception() {
        final String input = "acadplan";
        try {
            AcademicPlannerParser.parse(input,allModules,currentPerson,ui,storage);
        } catch (Exception e) {
            assertEquals(ERROR_IN_ACADPLAN + NEW_LINE + EXITING_CURRENT_COMMAND, e.getMessage());
        }
    }

    @Test
    void parse_incompleteAddCommand_exception() {
        final String input = "add";
        try {
            AcademicPlannerParser.parse(input,allModules,currentPerson,ui,storage);
        } catch (Exception e) {
            assertEquals("ADD COMMAND:" + ERROR_NO_PARAMETER + NEW_LINE + EXITING_CURRENT_COMMAND, e.getMessage());
        }
    }

    @Test
    void parse_incompleteEditCommand_exception() {
        final String input = "edit";
        try {
            AcademicPlannerParser.parse(input,allModules,currentPerson,ui,storage);
        } catch (Exception e) {
            assertEquals("EDIT COMMAND:" + ERROR_NO_PARAMETER + NEW_LINE + EXITING_CURRENT_COMMAND, e.getMessage());
        }
    }

    @Test
    void parse_incompleteRemoveCommand_exception() {
        final String input = "remove";
        try {
            AcademicPlannerParser.parse(input,allModules,currentPerson,ui,storage);
        } catch (Exception e) {
            assertEquals("REMOVE COMMAND:" + ERROR_NO_PARAMETER + NEW_LINE + EXITING_CURRENT_COMMAND, e.getMessage());
        }
    }

    @Test
    void parse_incompleteSearchCommand_exception() {
        final String input = "search";
        try {
            AcademicPlannerParser.parse(input,allModules,currentPerson,ui,storage);
        } catch (Exception e) {
            assertEquals("SEARCH COMMAND:" + ERROR_NO_PARAMETER + NEW_LINE + EXITING_CURRENT_COMMAND, e.getMessage());
        }
    }

    @Test
    void parse_incompleteDetailsCommand_exception() {
        final String input = "details";
        try {
            AcademicPlannerParser.parse(input,allModules,currentPerson,ui,storage);
        } catch (Exception e) {
            assertEquals("DETAILS COMMAND:" + ERROR_NO_PARAMETER + NEW_LINE + EXITING_CURRENT_COMMAND, e.getMessage());
        }
    }

    @Test
    void parse_acadplanCommandTooManyParameters_exception() {
        final String input = "acadplan potato peeler joe biden";
        try {
            AcademicPlannerParser.parse(input,allModules,currentPerson,ui,storage);
        } catch (Exception e) {
            assertEquals("ACADPLAN COMMAND:" + ERROR_HAS_PARAMETER + NEW_LINE
                    + EXITING_CURRENT_COMMAND, e.getMessage());
        }
    }

    @Test
    void parse_viewCommandTooManyParameters_exception() {
        final String input = "view potato peeler joe biden";
        try {
            AcademicPlannerParser.parse(input,allModules,currentPerson,ui,storage);
        } catch (Exception e) {
            assertEquals("VIEW COMMAND:" + ERROR_HAS_PARAMETER + NEW_LINE + EXITING_CURRENT_COMMAND, e.getMessage());
        }
    }

    @Test
    void parse_helpCommandTooManyParameters_exception() {
        final String input = "help potato peeler joe biden";
        try {
            AcademicPlannerParser.parse(input,allModules,currentPerson,ui,storage);
        } catch (Exception e) {
            assertEquals("HELP COMMAND:" + ERROR_HAS_PARAMETER + NEW_LINE + EXITING_CURRENT_COMMAND, e.getMessage());
        }
    }

    @Test
    void parse_exitCommandTooManyParameters_exception() {
        final String input = "exit potato peeler joe biden";
        try {
            AcademicPlannerParser.parse(input,allModules,currentPerson,ui,storage);
        } catch (Exception e) {
            assertEquals("EXIT COMMAND:" + ERROR_HAS_PARAMETER + NEW_LINE + EXITING_CURRENT_COMMAND, e.getMessage());
        }
    }

    @Test
    void parse_capcalcCommandTooManyParameters_exception() {
        final String input = "capcalc potato peeler joe biden";
        try {
            AcademicPlannerParser.parse(input,allModules,currentPerson,ui,storage);
        } catch (Exception e) {
            assertEquals("CAPCALC COMMAND:" + ERROR_HAS_PARAMETER + NEW_LINE + EXITING_CURRENT_COMMAND, e.getMessage());
        }
    }

    private <T extends Command> void parseAndAssertCommandType(String input, Class<T> expectedCommandClass,
                                                               ModuleLoader allModules,Person currentPerson, Ui ui,
                                                               Storage storage) {
        try {
            final Command result = AcademicPlannerParser.parse(input, allModules, currentPerson, ui, storage);
            assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
        } catch (Exception e) {
            fail();
        }
    }
}