package seedu.duke.parser;

import seedu.duke.apps.academicplanner.AcademicPlannerApp;
import seedu.duke.apps.capcalculator.CapCalculatorApp;
import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.global.App;
import seedu.duke.global.exceptions.AppParserException;
import seedu.duke.global.objects.Person;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

//@@author Khenus
/**
 * Class representing the parser for applications used in the main PlanNUS page.
 */
public class AppParser {
    public static final int MENU_PAGE = 0;
    public static final int ACADEMIC_PLANNER = 1;
    public static final int CAP_CALCULATOR = 2;

    private static final String ACADEMIC_PLAN_COMMAND = "ACADPLAN";
    private static final String CAP_CALCULATOR_COMMAND = "CAPCALC";
    private static final String EXIT_COMMAND = "EXIT";
    private static final String ACADEMIC_PLAN_SHORT_COMMAND = "A";
    private static final String CAP_CALCULATOR_SHORT_COMMAND = "C";
    private static final String EXIT_SHORT_COMMAND = "E";
    private static final String INVALID_COMMAND_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";

    /**
     * Processes user input command and returns app to be run.
     *
     * @param userInput user input
     * @param allModules variable containing all modules offered by NUS
     * @param currentPerson user of PlanNUS
     * @param ui Ui
     * @return app to be run
     * @throws AppParserException thrown when an invalid command is give
     */
    public static App parse(String userInput, ModuleLoader allModules, Person currentPerson, Ui ui, Storage storage)
            throws AppParserException {
        userInput = userInput.trim().toUpperCase();

        if (userInput.equals(ACADEMIC_PLAN_COMMAND) || userInput.equals(ACADEMIC_PLAN_SHORT_COMMAND)) {
            return new AcademicPlannerApp(allModules, currentPerson, ui, storage);
        } else if (userInput.equals(CAP_CALCULATOR_COMMAND) || userInput.equals(CAP_CALCULATOR_SHORT_COMMAND)) {
            return new CapCalculatorApp(currentPerson, ui);
        } else if (userInput.equals(EXIT_COMMAND) || userInput.equals(EXIT_SHORT_COMMAND)) {
            return new App(true);
        } else {
            throw new AppParserException(INVALID_COMMAND_MESSAGE);
        }
    }

    /**
     * Processes toggle command and returns app to be run.
     *
     * @param newApp Index of app to be toggled to
     * @param allModules variable containing all modules offered by NUS
     * @param currentPerson user of PlanNUS
     * @param ui Ui
     * @return app to be run
     * @throws AppParserException thrown when an invalid command is give
     */
    public static App specialParse(int newApp, ModuleLoader allModules, Person currentPerson, Ui ui, Storage storage)
            throws AppParserException {

        if (newApp == ACADEMIC_PLANNER) {
            return new AcademicPlannerApp(allModules, currentPerson, ui, storage);
        } else if (newApp == CAP_CALCULATOR) {
            return new CapCalculatorApp(currentPerson, ui);
        } else if (newApp == MENU_PAGE) {
            return new App(true);
        } else {
            throw new AppParserException(INVALID_COMMAND_MESSAGE);
        }
    }
}
