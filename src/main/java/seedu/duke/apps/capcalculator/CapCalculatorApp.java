package seedu.duke.apps.capcalculator;

import seedu.duke.global.App;
import seedu.duke.global.Command;
import seedu.duke.global.objects.Person;
import seedu.duke.ui.Ui;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import static seedu.duke.parser.AppParser.CAP_CALCULATOR;

//@@author JuZihao
/**
 * Class representing the CAP Calculator app in PlanNUS.
 */
public class CapCalculatorApp extends App {
    private static final String NEW_LINE = "\n";
    private static final String EXITING_CURRENT_COMMAND = "Exiting current command back to Cap Calculator Main Menu.";
    private static final String ERROR_INVALID_COMMAND = "INVALID COMMAND";
    private static final String EXIT_MESSAGE = "Thank you for using Cap Calculator!";
    private static final String WELCOME_MESSAGE = "Welcome to CAP Calculator!\n"
            + "Initializing your CAP...";
    private static final String COMMANDS_LIST = "Available commands are:\n"
            + "\tcurrent\n"
            + "\tset target\n"
            + "\tset su\n"
            + "\tacadplan\n"
            + "\thelp\n"
            + "\texit";

    private final Person currentPerson;
    private final Ui ui;
    private final DecimalFormat formatFinalCap = new DecimalFormat("#.##");

    public CapCalculatorApp(Person currentPerson, Ui ui) {
        this.currentPerson = currentPerson;
        this.ui = ui;
    }

    /**
     * Main function for CAP Calculator.
     */
    public void run() {
        ui.printLine();
        showWelcomeMessage();
        boolean isExit = false;
        formatFinalCap.setRoundingMode(RoundingMode.UP);

        while (!isExit) {
            try {
                ui.showCalculatorAwaitCommand();
                ui.printLine();
                String userInput = ui.getScanner().nextLine();
                Command commandInput = CapCalculatorParser.parse(userInput, currentPerson, ui);
                commandInput.execute();

                if (commandInput.getIsChangeApp()) {
                    setIsChangeApp(commandInput.getIsChangeApp());
                    setCurrentApp(commandInput.getNewApp());
                } else {
                    setIsChangeApp(false);
                    setCurrentApp(CAP_CALCULATOR);
                }

                isExit = commandInput.getIsExit();
            } catch (NumberFormatException e) {
                System.out.println(ERROR_INVALID_COMMAND + NEW_LINE + EXITING_CURRENT_COMMAND);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        ui.printLine();
        showExitMessage();
    }


    /**
     * Prints out welcome message when user enters CAP Calculator.
     */
    private void showWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE + NEW_LINE);
        System.out.println(COMMANDS_LIST);
    }

    /**
     * Prints out exit message when user enters CAP Calculator.
     */
    private void showExitMessage() {
        System.out.println(EXIT_MESSAGE);
    }

}
