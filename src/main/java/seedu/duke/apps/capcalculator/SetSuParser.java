package seedu.duke.apps.capcalculator;

import seedu.duke.apps.capcalculator.commands.SetSuByModuleCommand;
import seedu.duke.apps.capcalculator.commands.SetSuBySemesterCommand;
import seedu.duke.global.exceptions.CommandParserException;
import seedu.duke.global.Command;
import seedu.duke.global.objects.Person;
import seedu.duke.ui.Ui;

//@@author JuZihao
/**
 * Class representing the parser used for set su command.
 */
public class SetSuParser {
    private static final String BY_MODULES = "2";
    private static final String BY_SEMESTER = "1";
    private static final String INVALID_SU_ERROR = "Number entered does not correspond to any S/U method.";

    /**
     * Processes user input command and returns which set su command to be parsed.
     *
     * @param currentPerson user of PlanNUS
     * @return Command to be executed
     * @throws CommandParserException thrown when an invalid input is entered
     */
    public static Command parse(String choice, Person currentPerson, Ui ui) throws CommandParserException {
        if (choice.equals(BY_SEMESTER)) {
            return new SetSuBySemesterCommand(currentPerson, ui);
        } else if (choice.equals(BY_MODULES)) {
            return new SetSuByModuleCommand(currentPerson, ui);
        } else {
            throw new CommandParserException(INVALID_SU_ERROR);
        }
    }
}
