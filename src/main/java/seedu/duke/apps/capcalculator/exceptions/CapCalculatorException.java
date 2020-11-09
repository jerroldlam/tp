package seedu.duke.apps.capcalculator.exceptions;

//@@author JuZihao
/**
 * Signals that an invalid command has been given in the CAP Calculator App.
 */
public class CapCalculatorException extends Exception {
    private String errorMessage;

    private static final String NEW_LINE = "\n";
    private static final String EXITING_CURRENT_COMMAND =
            "Exiting current command back to CAP Calculator App Main Menu.";

    public CapCalculatorException(String errorMessage) {
        String stringToPrint = errorMessage + NEW_LINE + EXITING_CURRENT_COMMAND;
        this.errorMessage = stringToPrint;
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }
}
