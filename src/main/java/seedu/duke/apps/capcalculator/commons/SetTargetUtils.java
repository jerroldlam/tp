package seedu.duke.apps.capcalculator.commons;

import seedu.duke.exceptions.InvalidCapException;
import seedu.duke.exceptions.InvalidCreditException;
import seedu.duke.objects.Person;
import seedu.duke.ui.Ui;
import static seedu.duke.apps.capcalculator.commons.CalculatorUtils.MAXIMUM_CAP;
import static seedu.duke.apps.capcalculator.commons.CalculatorUtils.formatCapToString;

/**
 * Class representing the common Set Target functions in CAP Calculator.
 */
public class SetTargetUtils {
    private static final String INVALID_CAP_MESSAGE = "Your target CAP cannot be greater than the maximum CAP of 5!";
    private static final String INVALID_MC_MESSAGE = "Your target MC cannot be 0!";

    private Person currentPerson;
    private Ui ui;

    public SetTargetUtils(Person currentPerson, Ui ui) {
        this.currentPerson = currentPerson;
        this.ui = ui;
    }

    /**
     * Obtain the target CAP from the user.
     */
    public double getTargetCap() throws InvalidCapException {
        System.out.println("What is your target CAP?");
        double targetCap = Double.parseDouble(ui.getScanner().nextLine());
        checkValidCap(targetCap);
        return targetCap;
    }

    /**
     * Obtain the target MCs from the user.
     */
    public int getTargetGradedMC() throws InvalidCreditException {
        System.out.println("How many graded MCs you are taking to achieve the target CAP?");
        int targetGradedMC = Integer.parseInt(ui.getScanner().nextLine());
        checkValidCredits(targetGradedMC);
        return targetGradedMC;
    }

    /**
     * Checks if the target Cap given by the user is valid.
     *
     * @param cap Cap to be checked
     * @throws InvalidCapException if the Cap given is greater than 5.00 or less than 0
     */
    private void checkValidCap(double cap) throws InvalidCapException {
        if (cap > MAXIMUM_CAP || cap <= 0) {
            throw new InvalidCapException(INVALID_CAP_MESSAGE);
        }
    }

    /**
     * Checks if the target Cap given by the user is valid.
     *
     * @param credits Module credits to be checked
     * @throws InvalidCreditException if the module credit given less than 0
     */
    private void checkValidCredits(int credits) throws InvalidCreditException {
        if (credits <= 0) {
            throw new InvalidCreditException(INVALID_MC_MESSAGE);
        }
    }

    /**
     * Calculate what should be the user's minimum CAP in order to achieve user's target CAP and display to user.
     */
    public void showResultsToUser(double targetCap,int targetGradedMC) {
        int totalMcToTarget = currentPerson.getCurrentMcAfterSU() + targetGradedMC;
        double targetCapxTargetMC = (double) totalMcToTarget * targetCap;
        double neededCap = (targetCapxTargetMC - currentPerson.getCurrentTotalMcxGrade()) / (double) targetGradedMC;

        if (neededCap <= 5) {
            printTargetResultPossible(targetCap, targetGradedMC, neededCap);
        } else {
            printTargetResultImpossible(targetCap, targetGradedMC);
        }
    }

    /**
     * Prints out message for user when the target result cannot be achieved.
     *
     * @param targetCap user's targeted CAP.
     * @param targetGradedMC user's targeted MC.
     */
    private void printTargetResultImpossible(double targetCap, int targetGradedMC) {
        System.out.println("OOPS!! Looks like you are not able to achieve your target CAP of " + targetCap
                + " with you target MCs of " + targetGradedMC + ".");
    }

    /**
     * Prints out message for user when the target result can be achieved.
     *
     * @param targetCap user's targeted CAP.
     * @param targetGradedMC user's targeted MC.
     * @param neededCap user's future CAP needed to reach targeted CAP
     */
    private void printTargetResultPossible(double targetCap, int targetGradedMC, double neededCap) {
        System.out.println("You should achieve a minimum CAP of " + formatCapToString(neededCap) + " for your next "
                + targetGradedMC + " MCs to achieve your target CAP of " + targetCap + ".");
    }
}
