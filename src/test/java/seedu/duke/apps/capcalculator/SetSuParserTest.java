package seedu.duke.apps.capcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.apps.capcalculator.commands.SetSuByModuleCommand;
import seedu.duke.apps.capcalculator.commands.SetSuBySemesterCommand;
import seedu.duke.global.Command;
import seedu.duke.global.objects.Person;
import seedu.duke.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

//@@author JuZihao
class SetSuParserTest {
    private static final String INVALID_SU_ERROR = "Number entered does not correspond to any S/U method.";
    Person currentPerson;
    Ui ui;

    @BeforeEach
    public void setup() {
        currentPerson = new Person("Bob");
        ui = new Ui();
    }

    @Test
    void parse_SetSuByModuleCommand_parsedCorrectly() {
        final String choice = "2";
        parseAndAssertCommandType(choice, SetSuByModuleCommand.class, currentPerson, ui);
    }

    @Test
    void parse_SetSuBySemesterCommand_parsedCorrectly() {
        final String choice = "1";
        parseAndAssertCommandType(choice, SetSuBySemesterCommand.class, currentPerson, ui);
    }

    @Test
    void parse_unknownChoice_exceptionThrown() {
        try {
            final String unknownChoice = "unknown choice";
            SetSuParser.parse(unknownChoice, currentPerson, ui);
        } catch (Exception e) {
            assertEquals(INVALID_SU_ERROR, e.getMessage());
        }
    }

    /**
     * Parses input and asserts the class/type of the returned command object.
     *
     * @param choice to be parsed
     * @param expectedCommandClass expected class of returned command
     */
    private <T extends Command> void parseAndAssertCommandType(String choice, Class<T> expectedCommandClass,
                                                               Person currentPerson, Ui ui) {
        try {
            final Command result = SetSuParser.parse(choice, currentPerson, ui);
            assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
        } catch (Exception e) {
            fail();
        }
    }
}