package seedu.duke.exceptions;

import seedu.duke.apps.capcalculator.exceptions.CapCalculatorException;

/**
 * Signals that the given MC is not valid.
 */
public class InvalidCreditException extends CapCalculatorException {

    public InvalidCreditException(String errorMessage) {
        super(errorMessage);
    }
}
