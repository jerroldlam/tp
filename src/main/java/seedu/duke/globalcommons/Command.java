package seedu.duke.globalcommons;

/**
 * Class representing a command in PlanNUS.
 */
public class Command {
    private boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public void execute() throws Exception {

    }

    public boolean getIsExit() {
        return this.isExit;
    }
}
