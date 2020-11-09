package seedu.duke.apps.capcalculator.commands;

import seedu.duke.global.Command;
import seedu.duke.global.objects.Person;
import static seedu.duke.apps.capcalculator.commons.CalculatorUtils.formatCapToString;

//@@author JuZihao
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
        double currentCap = getCurrentCap();
        displayCurrentCap(currentCap);
        displayCurrentMcAfterSU(currentPerson.getCurrentMcAfterSU());
        displayCurrentMc(currentPerson.getCurrentMc());
    }

    /**
     * Returns user current cap.
     *
     * @return CAP
     */
    public double getCurrentCap() {
        return currentPerson.getCurrentTotalMcxGrade() / (double) currentPerson.getCurrentMcAfterSU();
    }

    /**
     * Displays user's current CAP.
     *
     * @param currentCap user's current CAP
     */
    public void displayCurrentCap(double currentCap) {
        System.out.println("Your current CAP is: " + formatCapToString(currentCap));
    }

    /**
     * Displays user's graded MCs.
     *
     * @param currentMcAfterSU user's graded MCs
     */
    public void displayCurrentMcAfterSU(int currentMcAfterSU) {
        System.out.println("Number of graded MCs taken is: " + currentMcAfterSU);
    }

    /**
     * Displays user's total MCs.
     *
     * @param currentMc user's total MCs
     */
    public void displayCurrentMc(int currentMc) {
        System.out.println("Total number of MCs taken is: " + currentMc);
    }
}
