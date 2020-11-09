package seedu.duke.global;

import static seedu.duke.parser.AppParser.MENU_PAGE;

//@@author Khenus
/**
 * Class representing a command in PlanNUS.
 */
public class Command {
    private boolean isExit;
    private boolean isChangeApp;
    private int newApp;

    public Command() {
        this.isExit = false;
        this.isChangeApp = false;
        this.newApp = MENU_PAGE;
    }

    public Command(boolean isExit) {
        this.isExit = isExit;
        this.isChangeApp = false;
        this.newApp = MENU_PAGE;
    }

    public Command(boolean isExit, boolean isChangeApp, int changeAppTo) {
        this.isExit = isExit;
        this.isChangeApp = isChangeApp;
        this.newApp = changeAppTo;
    }

    public void execute() throws Exception {
        //Method is intentionally left blank
    }

    public boolean getIsExit() {
        return this.isExit;
    }

    public boolean getIsChangeApp() {
        return this.isChangeApp;
    }

    public int getNewApp() {
        return this.newApp;
    }
}
