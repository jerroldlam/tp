package seedu.duke.exceptions;

import seedu.duke.apps.capcalculator.exceptions.CapCalculatorException;

/**
 * Signals that the given CAP is not valid.
 */
public class InvalidCapException extends CapCalculatorException {

    public InvalidCapException(String errorMessage) {
        super(errorMessage);
    }
}
