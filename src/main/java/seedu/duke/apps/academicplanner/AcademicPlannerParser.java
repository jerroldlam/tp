package seedu.duke.apps.academicplanner;

import seedu.duke.apps.academicplanner.commands.AddModuleCommand;
import seedu.duke.apps.academicplanner.commands.EditModuleCommand;
import seedu.duke.apps.academicplanner.commands.RemoveModuleCommand;
import seedu.duke.apps.academicplanner.commands.SearchModulesCommand;
import seedu.duke.apps.academicplanner.commands.ModuleDetailsCommand;
import seedu.duke.apps.academicplanner.commands.PrintCalenderCommand;
import seedu.duke.apps.academicplanner.commands.PrintHelpCommand;
import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.global.Command;
import seedu.duke.global.exceptions.CommandParserException;
import seedu.duke.global.objects.Person;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.Scanner;

import static seedu.duke.parser.AppParser.CAP_CALCULATOR;

//@@author jerroldlam
/**
 * Class representing the parser for the academic parser. Used in the academic parser app.
 */
public class AcademicPlannerParser {
    private static final int COMMAND_INDEX = 0;
    private static final int MODULE_CODE_INDEX = 1;
    private static final int CORRECT_COMMAND_LENGTH = 2;
    public static final String NEW_LINE = "\n";
    private static final String EXITING_CURRENT_COMMAND = "Exiting current command back to Academic Planner Main Menu.";
    private static final String ERROR_INVALID_COMMAND = "INVALID COMMAND";
    private static final String ERROR_NO_PARAMETER = " NO PARAMETER AFTER COMMAND";
    private static final String ERROR_HAS_PARAMETER = " NO PARAMETER ALLOWED AFTER COMMAND";
    private static final String ERROR_IN_ACADPLAN = "Sorry, you are already in Academic Planner!";
    private static final String ADD_COMMAND = "ADD";
    private static final String EDIT_COMMAND = "EDIT";
    private static final String REMOVE_COMMAND = "REMOVE";
    private static final String VIEW_COMMAND = "VIEW";
    private static final String SEARCH_COMMAND = "SEARCH";
    private static final String HELP_COMMAND = "HELP";
    private static final String EXIT_COMMAND = "EXIT";
    private static final String DETAILS_COMMAND = "DETAILS";
    private static final String ACADEMIC_PLANNER_COMMAND = "ACADPLAN";
    private static final String TO_CAP_CALCULATOR_COMMAND = "CAPCALC";

    /**
     * Command to process the user inputs and to return the intended command with the correct parameters.
     *
     * @param userInput input of user
     * @param allModules data of all modules offered by NUS
     * @param currentPerson person using the app
     * @param ui ui for output
     * @return Command to be executed
     * @throws CommandParserException to return with error message
     */
    public static Command parse(String userInput, ModuleLoader allModules, Person currentPerson, Ui ui, Storage storage)
        throws CommandParserException {

        String[] inputs = processInput(userInput.trim());
        String userCommand = inputs[COMMAND_INDEX];
        Scanner in = ui.getScanner();

        if (userCommand.equals(ADD_COMMAND) && hasParameter(inputs)) {
            return new AddModuleCommand(allModules, currentPerson, ui, inputs[MODULE_CODE_INDEX].trim(), storage);

        } else if (userCommand.equals(EDIT_COMMAND) && hasParameter(inputs)) {
            return new EditModuleCommand(allModules, currentPerson, ui, inputs[MODULE_CODE_INDEX].trim(), storage);

        } else if (userCommand.equals(REMOVE_COMMAND) && hasParameter(inputs)) {
            return new RemoveModuleCommand(allModules, currentPerson, ui, inputs[MODULE_CODE_INDEX].trim(), storage);

        } else if (userCommand.equals(VIEW_COMMAND) && hasNoParameter(inputs)) {
            return new PrintCalenderCommand(currentPerson, in);

        } else if (userCommand.equals(HELP_COMMAND) && hasNoParameter(inputs)) {
            return new PrintHelpCommand();

        } else if (userCommand.equals(DETAILS_COMMAND) && hasParameter(inputs)) {
            return new ModuleDetailsCommand(allModules, inputs[MODULE_CODE_INDEX].trim());

        } else if (userCommand.equals(SEARCH_COMMAND) && hasParameter(inputs)) {
            return new SearchModulesCommand(allModules, inputs[MODULE_CODE_INDEX].trim());

        } else if (userCommand.equals(TO_CAP_CALCULATOR_COMMAND) && hasNoParameter(inputs)) {
            return new Command(true, true, CAP_CALCULATOR);

        } else if (userCommand.equals(EXIT_COMMAND) && hasNoParameter(inputs)) {
            return new Command(true);

        } else if (userCommand.equals(ACADEMIC_PLANNER_COMMAND) && hasNoParameter(inputs)) {
            throw new CommandParserException(ERROR_IN_ACADPLAN + NEW_LINE + EXITING_CURRENT_COMMAND);
        } else {
            String errorMessage = determineError(inputs);
            throw new CommandParserException(errorMessage + NEW_LINE + EXITING_CURRENT_COMMAND);
        }
    }

    /**
     * Processes user input to remove whitespaces and return a string array.
     *
     * @param userInput user input
     * @return string array
     */
    public static String[] processInput(String userInput) {
        userInput = userInput.replaceAll("\\s+"," ");
        String[] inputs = userInput.toUpperCase().split(" ",2);
        return inputs;
    }

    /**
     * Checks for the type of error and returns corresponding message.
     *
     * @param inputs user input
     * @return error message
     */
    private static String determineError(String[] inputs) {
        switch (inputs[COMMAND_INDEX]) {
        case ADD_COMMAND:
            return ("ADD COMMAND:" + ERROR_NO_PARAMETER);
        case EDIT_COMMAND:
            return ("EDIT COMMAND:" + ERROR_NO_PARAMETER);
        case REMOVE_COMMAND:
            return ("REMOVE COMMAND:" + ERROR_NO_PARAMETER);
        case SEARCH_COMMAND:
            return ("SEARCH COMMAND:" + ERROR_NO_PARAMETER);
        case DETAILS_COMMAND:
            return ("DETAILS COMMAND:" + ERROR_NO_PARAMETER);
        case ACADEMIC_PLANNER_COMMAND:
            return ("ACADPLAN COMMAND:" + ERROR_HAS_PARAMETER);
        case VIEW_COMMAND:
            return ("VIEW COMMAND:" + ERROR_HAS_PARAMETER);
        case HELP_COMMAND:
            return ("HELP COMMAND:" + ERROR_HAS_PARAMETER);
        case EXIT_COMMAND:
            return ("EXIT COMMAND:" + ERROR_HAS_PARAMETER);
        case TO_CAP_CALCULATOR_COMMAND:
            return ("CAPCALC COMMAND:" + ERROR_HAS_PARAMETER);
        default:
            return (ERROR_INVALID_COMMAND);
        }
    }

    /**
     * Returns true if input has parameter attached at the end after command,
     * else returns false.
     *
     * @param inputs user input
     * @return true or false
     */
    private static boolean hasParameter(String[] inputs) {
        return inputs.length == CORRECT_COMMAND_LENGTH;
    }

    /**
     * Returns true if input only has a command,
     * else returns false.
     *
     * @param inputs user input
     * @return boolean
     */
    public static boolean hasNoParameter(String[] inputs) {
        return inputs.length == 1;
    }
}
