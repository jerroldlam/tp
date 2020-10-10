package seedu.duke.apps.capcalculator;

import seedu.duke.globalcommons.App;
import seedu.duke.globalcommons.Command;
import seedu.duke.objects.Person;
import seedu.duke.ui.Ui;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import static seedu.duke.apps.capcalculator.CapCalculatorParser.ERROR_INVALID_COMMAND;
import static seedu.duke.apps.capcalculator.CapCalculatorParser.NEW_LINE;
import static seedu.duke.apps.capcalculator.CapCalculatorParser.EXITING_CURRENT_COMMAND;

public class CapCalculator extends App {

    private static final String AWAIT_COMMAND = "Type a command to continue...";
    private static final String EXIT_MESSAGE = "Thank you for using Cap Calculator!";
    private static final String WELCOME_MESSAGE = "Welcome to CAP Calculator! Commands available are:\n"
            + "\tcurrent\n"
            + "\tset target\n"
            + "\tTo exit CAP Calculator, use command: \"exit\"\n\n"
            + "\tInitializing your CAP...";

    private final Person currentPerson;
    private final Ui ui;
    private final DecimalFormat formatFinalCap = new DecimalFormat("#.##");

    public CapCalculator(Person currentPerson, Ui ui) {
        this.currentPerson = currentPerson;
        this.ui = ui;
    }

    //Main Function
    public void run() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println(AWAIT_COMMAND);
        boolean isExit = false;
        formatFinalCap.setRoundingMode(RoundingMode.UP);

        while (!isExit) {
            try {
                String userInput = ui.getScanner().nextLine();
                Command commandInput = CapCalculatorParser.parse(userInput, currentPerson, ui);
                commandInput.execute();
                isExit = commandInput.getIsExit();
            } catch (NumberFormatException e) {
                System.out.println(ERROR_INVALID_COMMAND + NEW_LINE + EXITING_CURRENT_COMMAND);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(EXIT_MESSAGE);
    }
}