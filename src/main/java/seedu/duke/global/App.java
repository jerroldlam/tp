package seedu.duke.global;

import static seedu.duke.parser.AppParser.MENU_PAGE;

//@@author Khenus
/**
 * Class representing an application in PlanNUS.
 */
public class App {
    private boolean isExit = false;
    private boolean isChangeApp = false;
    private int currentApp = MENU_PAGE;

    public App() {

    }

    public App(boolean isExitCommand) {
        this.isExit = isExitCommand;
    }

    public void run() {
        //Method is intentionally left blank
    }

    public boolean getIsExit() {
        return this.isExit;
    }

    public boolean getIsChangeApp() {
        return this.isChangeApp;
    }

    public int getCurrentApp() {
        return this.currentApp;
    }

    public void setIsChangeApp(boolean isChangeApp) {
        this.isChangeApp = isChangeApp;
    }

    public void setCurrentApp(int newApp) {
        this.currentApp = newApp;
    }
}
