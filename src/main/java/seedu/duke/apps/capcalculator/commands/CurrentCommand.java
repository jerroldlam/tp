package seedu.duke.apps.capcalculator.commands;

import seedu.duke.globalcommons.Command;
import seedu.duke.objects.Person;
import static seedu.duke.apps.capcalculator.commons.CalculatorUtils.formatCapToString;

/**
 * Class representing the Current CAP command for CAP Calculator.
 */
public class CurrentCommand extends Command {

    private Person currentPerson;

    public CurrentCommand(Person currentPerson) {
        this.currentPerson = currentPerson;
    }

    /**
     * Prints out user's current cap obtained from his/her academic calendar.
     */
    @Override
    public void execute() {
        double currentCap = currentPerson.getCurrentTotalMcxGrade() / (double) currentPerson.getCurrentMcAfterSU();
        System.out.println("Your current now CAP is: " + formatCapToString(currentCap));
        System.out.println("Number of graded MCs taken is: " + currentPerson.getCurrentMcAfterSU());
    }
}
