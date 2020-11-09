package seedu.duke.apps.capcalculator;

import seedu.duke.apps.capcalculator.commands.CurrentCommand;
import seedu.duke.apps.capcalculator.commands.PrintHelpCommand;
import seedu.duke.apps.capcalculator.commands.SetTargetCommand;
import seedu.duke.global.exceptions.CommandParserException;
import seedu.duke.global.Command;
import seedu.duke.global.objects.Person;
import seedu.duke.ui.Ui;

import java.util.Scanner;

import static seedu.duke.apps.academicplanner.AcademicPlannerParser.NEW_LINE;
import static seedu.duke.apps.academicplanner.AcademicPlannerParser.hasNoParameter;
import static seedu.duke.parser.AppParser.ACADEMIC_PLANNER;

//@@author JuZihao
/**
 * Class representing the parser used in the CAP Calculator app.
 */
public class CapCalculatorParser {
    private static final int COMMAND_INDEX = 0;
    private static final int SECOND_COMMAND_INDEX = 1;
    private static final int CORRECT_COMMAND_LENGTH = 2;
    private static final String PROMPT_SU_CHOICES = "Enter the number corresponding to the method you wish to S/U:\n"
            + "\t1) Semester\n"
            + "\t2) Modules";
    private static final String INVALID_COMMAND_MESSAGE = "INVALID COMMAND";
    private static final String INVALID_SET_COMMAND_MESSAGE = "OOPS!!! Looks like you entered an invalid set command!";
    private static final String CURRENT_COMMAND = "CURRENT";
    private static final String SET_COMMAND = "SET";
    private static final String TARGET_COMMAND = "TARGET";
    private static final String SU_COMMAND = "SU";
    private static final String EXIT_COMMAND = "EXIT";
    private static final String HELP_COMMAND = "HELP";
    private static final String TO_ACADEMIC_PLANNER = "ACADPLAN";
    private static final String CAP_CALCULATOR_COMMAND = "CAPCALC";
    private static final String SWITCH_APP_ERROR = "Sorry, you are already in CAP calculator!";
    private static final String ERROR_HAS_PARAMETER = " NO PARAMETER ALLOWED AFTER COMMAND";
    private static final String MISSING_SET_PARAMETER = "OOPS!!! Looks like you did not specify a set command type!";
    private static final String EXITING_CURRENT_COMMAND = "Exiting current command back to CAP Calculator Main Menu.";

    /**
     * Processes user input command and returns command to be executed.
     *
     * @param userInput user input
     * @param currentPerson user of PlanNUS
     * @param ui ui
     * @return Command to be executed
     * @throws CommandParserException thrown when an invalid command is entered
     */
    public static Command parse(String userInput, Person currentPerson, Ui ui) throws CommandParserException {
        Scanner in = ui.getScanner();
        String [] inputs = processInput(userInput);
        String userCommand = inputs[COMMAND_INDEX];

        if (userCommand.equals(CURRENT_COMMAND) && hasNoParameter(inputs)) {
            return new CurrentCommand(currentPerson);
        } else if (userCommand.equals(SET_COMMAND) && getSetType(inputs).equals(TARGET_COMMAND)) {
            return new SetTargetCommand(currentPerson, ui);
        } else if (userCommand.equals(SET_COMMAND) && getSetType(inputs).equals(SU_COMMAND)) {
            promptUserForSuCommand();
            String choice = in.nextLine().trim();
            return SetSuParser.parse(choice, currentPerson, ui);
        } else if (userCommand.equals(HELP_COMMAND) && hasNoParameter(inputs)) {
            return new PrintHelpCommand();
        } else if (userCommand.equals(TO_ACADEMIC_PLANNER) && hasNoParameter(inputs)) {
            return new Command(true, true, ACADEMIC_PLANNER);
        } else if (userCommand.equals(EXIT_COMMAND) && hasNoParameter(inputs)) {
            return new Command(true);
        } else if (userCommand.equals(CAP_CALCULATOR_COMMAND) && hasNoParameter(inputs)) {
            throw new CommandParserException(SWITCH_APP_ERROR + NEW_LINE + EXITING_CURRENT_COMMAND);
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
        String[] inputs = userInput.toUpperCase().split(" ");
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
        case HELP_COMMAND:
            return ("HELP COMMAND:" + ERROR_HAS_PARAMETER);
        case CURRENT_COMMAND:
            return ("CURRENT COMMAND:" + ERROR_HAS_PARAMETER);
        case TO_ACADEMIC_PLANNER:
            return ("ACADPLAN COMMAND:" + ERROR_HAS_PARAMETER);
        case CAP_CALCULATOR_COMMAND:
            return ("CAPCALC COMMAND:" + ERROR_HAS_PARAMETER);
        case EXIT_COMMAND:
            return ("EXIT COMMAND:" + ERROR_HAS_PARAMETER);
        case SET_COMMAND:
            if (inputs[SECOND_COMMAND_INDEX].equals(TARGET_COMMAND)) {
                return ("SET TARGET COMMAND:" + ERROR_HAS_PARAMETER);
            } else if (inputs[SECOND_COMMAND_INDEX].equals(SU_COMMAND)) {
                return ("SET SU COMMAND:" + ERROR_HAS_PARAMETER);
            } else {
                return (INVALID_SET_COMMAND_MESSAGE);
            }
        default:
            return INVALID_COMMAND_MESSAGE;
        }
    }

    /**
     * Returns true if input has two parameters else returns false.
     *
     * @param inputs user input
     * @return true or false
     */
    private static boolean hasTwoParameters(String[] inputs) {
        return inputs.length == CORRECT_COMMAND_LENGTH;
    }

    /**
     * Returns the set command type.
     *
     * @param inputs user input
     * @return true or false
     * @throws CommandParserException thrown when a command that has only one parameter is entered
     */
    private static String getSetType(String[] inputs) throws CommandParserException {
        if (hasNoParameter(inputs)) {
            throw new CommandParserException(MISSING_SET_PARAMETER + NEW_LINE + EXITING_CURRENT_COMMAND);
        } else if (hasTwoParameters(inputs)) {
            return inputs[SECOND_COMMAND_INDEX];
        } else {
            String errorMessage = determineError(inputs);
            throw new CommandParserException(errorMessage + NEW_LINE + EXITING_CURRENT_COMMAND);
        }
    }

    /**
     * Prompt user to enter which S/U command to be parsed.
     */
    private static void promptUserForSuCommand() {
        System.out.println(PROMPT_SU_CHOICES);
    }
}
