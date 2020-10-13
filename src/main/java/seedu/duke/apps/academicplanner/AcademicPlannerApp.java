package seedu.duke.apps.academicplanner;

import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.globalcommons.App;
import seedu.duke.globalcommons.Command;
import seedu.duke.objects.Person;
import seedu.duke.ui.Ui;

/**
 * Class representing the Academic Planner Application.
 */
public class AcademicPlannerApp extends App {
    private static final String WELCOME_MESSAGE = "\nWelcome to Academic Planner!";
    private static final String EXIT_MESSAGE = "Thank you for using Academic Planner!";
    private static final String COMMANDS_LIST = "Available commands are:\n"
            + "\tadd <module code>\n"
            + "\tedit <module code>\n"
            + "\tremove <module code>\n"
            + "\tview\n"
            + "\texit\n"
            + "Type a command to continue...";

    private final ModuleLoader allModules;
    private final Person currentPerson;
    private Ui ui;

    public AcademicPlannerApp(ModuleLoader allModules, Person currentPerson, Ui ui) {
        this.allModules = allModules;
        this.currentPerson = currentPerson;
        this.ui = ui;
    }

    /**
     * Main entry point for the Academic Planner Application.
     */
    public void run() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println(COMMANDS_LIST);
        boolean isExit = false;

        while (!isExit) {
            try {
                String userInput = ui.getScanner().nextLine();
                Command commandInput = AcademicPlannerParser.parse(userInput, allModules, currentPerson, ui);
                commandInput.execute();
                isExit = commandInput.getIsExit();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        showExitMessageAcademicPlanner();
    }

    /**
     * Prints exit message for Academic Planner.
     */
    private void showExitMessageAcademicPlanner() {
        System.out.println(EXIT_MESSAGE);
    }
}
