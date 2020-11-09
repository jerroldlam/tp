package seedu.duke.apps.capcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.apps.capcalculator.commands.CurrentCommand;
import seedu.duke.apps.capcalculator.commands.PrintHelpCommand;
import seedu.duke.apps.capcalculator.commands.SetTargetCommand;
import seedu.duke.global.Command;
import seedu.duke.global.objects.Person;
import seedu.duke.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//@@author JuZihao
class CapCalculatorAppParserTest {
    private static final String INVALID_COMMAND_MESSAGE = "INVALID COMMAND";
    private static final String INVALID_SET_COMMAND_MESSAGE = "OOPS!!! Looks like you entered an invalid set command!";
    private static final String SWITCH_APP_ERROR = "Sorry, you are already in CAP calculator!";
    private static final String ERROR_HAS_PARAMETER = " NO PARAMETER ALLOWED AFTER COMMAND";
    private static final String MISSING_SET_PARAMETER = "OOPS!!! Looks like you did not specify a set command type!";
    private static final String NEW_LINE = "\n";
    private static final String EXITING_CURRENT_COMMAND = "Exiting current command back to CAP Calculator Main Menu.";

    private Person currentPerson;
    private Ui ui;

    @BeforeEach
    public void setup() {
        currentPerson = new Person("Bob");
        ui = new Ui();
    }

    @Test
    void parse_emptyInput_exceptionThrown() {
        try {
            final String emptyInput = "";
            CapCalculatorParser.parse(emptyInput, currentPerson, ui);
        } catch (Exception e) {
            assertEquals(getErrorMessage(INVALID_COMMAND_MESSAGE), e.getMessage());
        }
    }

    @Test
    void parse_unknownInput_exceptionThrown() {
        try {
            final String unknownInput = "unknown command word";
            CapCalculatorParser.parse(unknownInput, currentPerson, ui);
        } catch (Exception e) {
            assertEquals(getErrorMessage(INVALID_COMMAND_MESSAGE), e.getMessage());
        }
    }

    /**
     * Tests for commands. =======================================================================
     */

    @Test
    public void parse_currentCommand_parsedCorrectly() {
        final String input = "current";
        parseAndAssertCommandType(input, CurrentCommand.class, currentPerson, ui);
    }

    @Test
    public void parse_wrongCurrentCommand_exceptionThrown() {
        try {
            final String input = "current 1";
            CapCalculatorParser.parse(input, currentPerson, ui);
        } catch (Exception e) {
            assertEquals(getErrorMessage("CURRENT COMMAND:" + ERROR_HAS_PARAMETER), e.getMessage());
        }
    }

    @Test
    public void parse_emptySetCommand_exceptionThrown() {
        try {
            final String input = "set";
            CapCalculatorParser.parse(input, currentPerson, ui);
        } catch (Exception e) {
            assertEquals(getErrorMessage(MISSING_SET_PARAMETER), e.getMessage());
        }
    }

    @Test
    public void parse_wrongSetCommand_exceptionThrown() {
        try {
            final String input = "set 123";
            CapCalculatorParser.parse(input, currentPerson, ui);
        } catch (Exception e) {
            assertEquals(getErrorMessage(INVALID_SET_COMMAND_MESSAGE), e.getMessage());
        }
    }

    @Test
    public void parse_setTargetCommand_parsedCorrectly() {
        final String input = "set target";
        parseAndAssertCommandType(input, SetTargetCommand.class, currentPerson, ui);
    }

    @Test
    public void parse_wrongSetTargetCommand_exceptionThrown() {
        try {
            final String input = "set target 1";
            CapCalculatorParser.parse(input, currentPerson, ui);
        } catch (Exception e) {
            assertEquals(getErrorMessage("SET TARGET COMMAND:" + ERROR_HAS_PARAMETER), e.getMessage());
        }
    }

    @Test
    public void parse_exitCommand_parsedCorrectly() {
        final String input = "exit";
        parseAndAssertCommandType(input, Command.class, currentPerson, ui);
    }

    @Test
    public void parse_wrongExitCommand_exceptionThrown() {
        try {
            final String input = "exit 1";
            CapCalculatorParser.parse(input, currentPerson, ui);
        } catch (Exception e) {
            assertEquals(getErrorMessage("EXIT COMMAND:" + ERROR_HAS_PARAMETER), e.getMessage());
        }
    }

    @Test
    public void parse_HelpCommand_parsedCorrectly() {
        final String input = "help";
        parseAndAssertCommandType(input, PrintHelpCommand.class, currentPerson, ui);
    }

    @Test
    public void parse_wrongHelpCommand_exceptionThrown() {
        try {
            final String input = "help 1";
            CapCalculatorParser.parse(input, currentPerson, ui);
        } catch (Exception e) {
            assertEquals(getErrorMessage("HELP COMMAND:" + ERROR_HAS_PARAMETER), e.getMessage());
        }
    }

    @Test
    public void parse_acadPlanCommand_parsedCorrectly() {
        final String input = "acadplan";
        parseAndAssertCommandType(input, Command.class, currentPerson, ui);
    }

    @Test
    public void parse_wrongAcadPlanCommand_exceptionThrown() {
        try {
            final String input = "acadplan 1";
            CapCalculatorParser.parse(input, currentPerson, ui);
        } catch (Exception e) {
            assertEquals(getErrorMessage("ACADPLAN COMMAND:" + ERROR_HAS_PARAMETER), e.getMessage());
        }
    }

    @Test
    public void parse_capCalcCommand_exceptionThrown() {
        try {
            final String input = "capcalc";
            CapCalculatorParser.parse(input, currentPerson, ui);
        } catch (Exception e) {
            assertEquals(getErrorMessage(SWITCH_APP_ERROR), e.getMessage());
        }
    }

    @Test
    public void parse_wrongCapCalcCommand_exceptionThrown() {
        try {
            final String input = "capcalc 1";
            CapCalculatorParser.parse(input, currentPerson, ui);
        } catch (Exception e) {
            assertEquals(getErrorMessage("CAPCALC COMMAND:" + ERROR_HAS_PARAMETER), e.getMessage());
        }
    }

    /**
     * Parses input and asserts the class/type of the returned command object.
     *
     * @param input to be parsed
     * @param expectedCommandClass expected class of returned command
     */
    private <T extends Command> void parseAndAssertCommandType(String input, Class<T> expectedCommandClass,
                                                            Person currentPerson, Ui ui) {
        try {
            final Command result = CapCalculatorParser.parse(input, currentPerson, ui);
            assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
        } catch (Exception e) {
            fail();
        }
    }

    private String getErrorMessage(String errorMessage) {
        return errorMessage + NEW_LINE + EXITING_CURRENT_COMMAND;
    }
}