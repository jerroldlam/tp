package seedu.duke.apps.capcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.apps.capcalculator.commands.CurrentCommand;
import seedu.duke.apps.capcalculator.commands.SetTargetCommand;
import seedu.duke.globalcommons.Command;
import seedu.duke.objects.Person;
import seedu.duke.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.duke.apps.capcalculator.CapCalculatorParser.INVALID_COMMAND_MESSAGE;

class CapCalculatorAppParserTest {

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
            assertEquals(INVALID_COMMAND_MESSAGE, e.getMessage());
        }
    }

    @Test
    void parse_unknownInput_exceptionThrown() {
        try {
            final String unknownInput = "unknown command word";
            CapCalculatorParser.parse(unknownInput, currentPerson, ui);
        } catch (Exception e) {
            assertEquals(INVALID_COMMAND_MESSAGE, e.getMessage());
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
    public void parse_setTargetCommand_parsedCorrectly() {
        final String input = "set target";
        parseAndAssertCommandType(input, SetTargetCommand.class, currentPerson, ui);
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
}