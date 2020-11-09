package seedu.duke;

import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.global.App;
import seedu.duke.global.objects.Person;
import seedu.duke.parser.AppParser;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.File;

import static seedu.duke.parser.AppParser.MENU_PAGE;

//@@author Khenus
/**
 * Class representing main function of PlanNUS.
 */
public class PlanNus {
    private Ui ui;
    private ModuleLoader allModules;
    private Person currentPerson;
    private Storage storage;
    private boolean isExit;

    /**
     * Default constructor for PlanNus.
     */
    public PlanNus() {
        try {
            this.ui = new Ui();
            this.allModules = new ModuleLoader();
            this.currentPerson = new Person("Bob");
            this.storage = new Storage(allModules);
            isExit = false;
        } catch (Exception e) {
            isExit = true;
            System.out.println(e.getMessage());
        }
    }

    /**
     * Main entry function for PlanNUS.
     */
    public void run() {
        assert isExit : "Startup is unsuccessful";

        File file = new File("logs");
        boolean isDirectoryCreated = file.mkdirs();
        if (!isDirectoryCreated) {
            System.out.println("Failed Creating logs Directory");
        }

        boolean isChangeApp = false;
        int currentApp = MENU_PAGE;

        ui.printLine();
        storage.loader(currentPerson);

        ui.showWelcomeMessage();

        while (!isExit) {
            try {
                App selectedApp;

                if (isChangeApp) {
                    selectedApp = AppParser.specialParse(currentApp, allModules, currentPerson, ui, storage);
                    isChangeApp = false;
                    currentApp = MENU_PAGE;
                } else {
                    ui.showAwaitCommand();
                    ui.printLine();
                    String userInput = ui.getScanner().nextLine();
                    selectedApp = AppParser.parse(userInput, allModules, currentPerson, ui, storage);
                }

                selectedApp.run();

                isExit = selectedApp.getIsExit();
                isChangeApp = selectedApp.getIsChangeApp();
                currentApp = selectedApp.getCurrentApp();

                if (!isExit && !isChangeApp) {
                    ui.printLine();
                    ui.showWelcomeBackMessage();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        ui.closeScanner();
        storage.saver(currentPerson);
        ui.showExitMessage();
    }

    /**
     * Main executable code.
     * @param args arguments from command line
     */
    public static void main(String[] args) {
        new PlanNus().run();
    }
}
