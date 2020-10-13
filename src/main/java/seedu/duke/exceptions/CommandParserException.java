package seedu.duke.exceptions;

/**
 * Signals that an invalid command has been given in the main command parser.
 */
public class CommandParserException extends Exception {
    String errorMessage;

    public CommandParserException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }
}
