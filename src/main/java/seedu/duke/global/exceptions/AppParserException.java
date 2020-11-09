package seedu.duke.global.exceptions;

//@@author Khenus
/**
 * Signals that an invalid command has been given in the App parser.
 */
public class AppParserException extends Exception {
    String errorMessage;

    public AppParserException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }
}
