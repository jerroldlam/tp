package seedu.duke.ui;

import java.util.Scanner;

//@@author Khenus
/**
 * Class representing the ui for PlanNUS.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private static final String STARS = "************************************************************";
    private static final String NEW_LINE = "\n";
    private static final String LOGO = "             &&&&&&&&&&&&&&&&&&&&&&&&&\n"
            + "           &&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n"
            + "          &&&&&                     &&&&&\n"
            + "        &&&&&                        &&&&&&\n"
            + "       &&&&&            &&&&&&         &&&&&\n"
            + "     &&&&&&         &&&&&&&&&&&&&&      &&&&&&\n"
            + "    &&&&&         &&&&&        &&&&       &&&&&\n"
            + "  &&&&&&          &&&&          &&&        &&&&&\n"
            + " &&&&&            &&&&          &&&          &&&&&\n"
            + " &&&&&            &&&&&&&&&&&&&&&            &&&&&\n"
            + "  &&&&&           &&&&  &&&&&&&             &&&&&\n"
            + "   &&&&&          &&&&                    &&&&&&\n"
            + "     &&&&&        &&&&                   &&&&&\n"
            + "      &&&&&       &&&&                 &&&&&&\n"
            + "        &&&&&                         &&&&&\n"
            + "         &&&&&                       &&&&&\n"
            + "          &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n"
            + "            &&&&&&&&&&&&&&&&&&&&&&&&&&&";
    private static final String WELCOME_MESSAGE = "Welcome to PlanNUS!";
    private static final String WELCOME_BACK_MESSAGE = "Welcome back to PlanNUS Main Menu!";
    private static final String AWAIT_COMMAND = "Type in a command to continue...";
    private static final String ACADEMIC_AWAIT_COMMAND = "Type in an Academic Planner command to continue...";
    private static final String CALCULATOR_AWAIT_COMMAND = "Type in an CAP Calculator command to continue...";
    private static final String EXIT_MESSAGE = "Thanks for using PlanNUS! We hope to see you again!";
    private static final String HELP_MESSAGE = "\tFor academic planner, type 'acadplan' or 'a'\n"
            + "\tFor CAP calculator, type 'capcalc' or 'c'\n"
            + "\tTo exit PlanNUS, type 'exit' or 'e'";

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public Scanner getScanner() {
        return this.scanner;
    }

    public void closeScanner() {
        this.scanner.close();
    }

    public void printLine() {
        System.out.println(LINE);
    }

    public void printNewLine() {
        System.out.println(NEW_LINE);
    }

    public void printStars() {
        System.out.println(NEW_LINE + STARS + NEW_LINE);
    }

    /**
     * Prints exit message for user just before termination of program.
     */
    public void showExitMessage() {
        System.out.println(LINE + NEW_LINE
                + EXIT_MESSAGE + NEW_LINE
                + LINE + NEW_LINE);
    }

    /**
     * Prints welcome back message for user when user returns back to main menu.
     */
    public void showWelcomeBackMessage() {
        System.out.println(WELCOME_BACK_MESSAGE);
        System.out.println(HELP_MESSAGE);
    }

    /**
     * Prints welcome message for user upon first entry into PlanNUS.
     */
    public void showWelcomeMessage() {
        System.out.println(NEW_LINE + LOGO + NEW_LINE + NEW_LINE + WELCOME_MESSAGE);
        System.out.println(HELP_MESSAGE);
    }

    /**
     * Prints general await command.
     */
    public void showAwaitCommand() {
        System.out.println(NEW_LINE + AWAIT_COMMAND);
    }

    /**
     * Prints await command for academic calendar.
     */
    public void showAcademicAwaitCommand() {
        System.out.println(NEW_LINE + ACADEMIC_AWAIT_COMMAND);
    }

    /**
     * Prints await command for CAP calculator.
     */
    public void showCalculatorAwaitCommand() {
        System.out.println(NEW_LINE + CALCULATOR_AWAIT_COMMAND);
    }

}
