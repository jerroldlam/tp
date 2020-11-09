package seedu.duke.apps.moduleloader.exceptions;

//@@author Khenus
/**
 * Signals that an error has occured when loading modules.
 */
public class ModuleLoaderException extends Exception {
    private String errorMessage;

    public ModuleLoaderException() {
        //Code is intentionally left blank
    }

    public ModuleLoaderException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }
}
