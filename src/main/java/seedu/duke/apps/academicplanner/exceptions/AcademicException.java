package seedu.duke.apps.academicplanner.exceptions;

//@@author Khenus
/**
 * Signals that an invalid command has been given in the Academic Planner App.
 */
public class AcademicException extends Exception {
    private String errorMessage;

    private static final String NEW_LINE = "\n";
    private static final String EXITING_CURRENT_COMMAND = "Exiting current command back to Academic Planner Main Menu.";

    /**
     * Default constructor for Academic Exception.
     *
     * @param errorMessage accompanying message
     */
    public AcademicException(String errorMessage) {
        String stringToPrint = errorMessage + NEW_LINE + EXITING_CURRENT_COMMAND;
        this.errorMessage = stringToPrint;
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }
}
