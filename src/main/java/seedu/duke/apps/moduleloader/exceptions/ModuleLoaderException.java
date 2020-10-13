package seedu.duke.apps.moduleloader.exceptions;

public class ModuleLoaderException extends Exception {
    private String errorMessage;

    public ModuleLoaderException() {

    }

    public ModuleLoaderException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }
}
