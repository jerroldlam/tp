package seedu.duke.apps.academicplanner;

import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.global.App;
import seedu.duke.global.Command;
import seedu.duke.global.objects.Person;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import static seedu.duke.parser.AppParser.ACADEMIC_PLANNER;

//@@author Khenus
/**
 * Class representing the Academic Planner Application.
 */
public class AcademicPlannerApp extends App {
    private static final String NEW_LINE = "\n";
    private static final String WELCOME_MESSAGE = "Welcome to Academic Planner!";
    private static final String EXIT_MESSAGE = "Thank you for using Academic Planner!";
    private static final String COMMANDS_LIST = "Available commands are:\n"
            + "\tadd <module code>\n"
            + "\tedit <module code>\n"
            + "\tremove <module code>\n"
            + "\tdetails <module code>\n"
            + "\tsearch <module code key>\n"
            + "\tcapcalc\n"
            + "\tview\n"
            + "\thelp\n"
            + "\texit";

    private final ModuleLoader allModules;
    private final Person currentPerson;
    private Ui ui;
    private Storage storage;

    /**
     * Default constructor for Academic Planner App.
     *
     * @param allModules all modules offered by NUS
     * @param currentPerson current user
     * @param ui ui of PlanNUS
     */
    public AcademicPlannerApp(ModuleLoader allModules, Person currentPerson, Ui ui, Storage storage) {
        this.allModules = allModules;
        this.currentPerson = currentPerson;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Main entry point for the Academic Planner Application.
     */
    public void run() {
        ui.printLine();
        showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                ui.showAcademicAwaitCommand();
                ui.printLine();
                String userInput = ui.getScanner().nextLine();
                Command commandInput = AcademicPlannerParser.parse(userInput, allModules, currentPerson, ui, storage);
                commandInput.execute();

                if (commandInput.getIsChangeApp()) {
                    setIsChangeApp(commandInput.getIsChangeApp());
                    setCurrentApp(commandInput.getNewApp());
                } else {
                    setIsChangeApp(false);
                    setCurrentApp(ACADEMIC_PLANNER);
                }

                isExit = commandInput.getIsExit();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        ui.printLine();
        showExitMessage();
    }

    /**
     * Prints welcome message for Academic Planner.
     */
    private void showWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE + NEW_LINE);
        System.out.println(COMMANDS_LIST);
    }

    /**
     * Prints exit message for Academic Planner.
     */
    private void showExitMessage() {
        System.out.println(EXIT_MESSAGE);
    }
}
