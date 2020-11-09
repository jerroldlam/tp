package seedu.duke.apps.capcalculator.commons;

import seedu.duke.apps.capcalculator.exceptions.InvalidCapException;
import seedu.duke.apps.capcalculator.exceptions.InvalidCreditException;
import seedu.duke.global.objects.Person;

import java.util.Scanner;

import static seedu.duke.apps.capcalculator.commons.CalculatorUtils.round;
import static seedu.duke.apps.capcalculator.commons.CalculatorUtils.formatCapToString;
import static seedu.duke.apps.capcalculator.commons.CalculatorUtils.MAXIMUM_CAP;

//@@author JuZihao
/**
 * Class representing the common Set Target functions in CAP Calculator.
 */
public class SetTargetUtils {
    private static final String MAXIMUM_CAP_ERROR = "Your target CAP cannot be greater than the maximum CAP of 5!";
    private static final String MINIMUM_CAP_ERROR = "Your target CAP cannot be lower than the minimum CAP of 0!";
    private static final String MINIMUM_MC_ERROR = "Your target MC should be greater than 0!";
    private static final String MAXIMUM_MC_ERROR = "Your target MC should not be greater than 180!";
    private static final String NOT_NUMBER_ERROR = "Looks like you did not enter an valid integer!";
    private static final int MINIMUM_MC = 0;
    private static final int MAXIMUM_MC = 180;

    private Person currentPerson;
    private Scanner in;

    public SetTargetUtils(Person currentPerson, Scanner in) {
        this.currentPerson = currentPerson;
        this.in = in;
    }

    /**
     * Returns the target Cap obtained from the user.
     *
     * @throws InvalidCapException if the Cap given is greater than 5.00 or less than 0
     */
    public double getTargetCap() throws InvalidCapException {
        System.out.println("What is your target CAP?");
        try {
            double targetCap = Double.parseDouble(in.nextLine());
            if (isValidCap(targetCap)) {
                return round(targetCap,2);
            } else if (targetCap > MAXIMUM_CAP) {
                throw new InvalidCapException(MAXIMUM_CAP_ERROR);
            } else {
                throw new InvalidCapException(MINIMUM_CAP_ERROR);
            }
        } catch (NumberFormatException e) {
            throw new InvalidCapException(NOT_NUMBER_ERROR);
        }
    }

    /**
     * Returns the target MCs obtained from the user.
     *
     * @throws InvalidCreditException if the module credit given less than 0
     */
    public int getTargetGradedMC() throws InvalidCreditException {
        System.out.println("How many graded MCs you are taking to achieve the target CAP?");
        try {
            int targetGradedMC = Integer.parseInt(in.nextLine());
            if (isValidCredits(targetGradedMC)) {
                return targetGradedMC;
            } else if (targetGradedMC > MINIMUM_MC) {
                throw new InvalidCreditException(MAXIMUM_MC_ERROR);
            } else {
                throw new InvalidCreditException(MINIMUM_MC_ERROR);
            }
        } catch (NumberFormatException e) {
            throw new InvalidCreditException(NOT_NUMBER_ERROR);
        }
    }

    /**
     * Checks if the target Cap given by the user is valid.
     * Returns true when the Cap provided is less than 5.00 and more or equals to 0.
     * Returns false otherwise.
     *
     * @param cap Cap to be checked
     * @return boolean whether Cap is valid
     */
    private boolean isValidCap(double cap) {
        return cap <= MAXIMUM_CAP && cap >= 0;
    }

    /**
     * Checks if the target Cap given by the user is valid.
     * Returns false when the MC provided is less than 0 else false.
     *
     *  @param credits Module credits to be checked
     *  @return boolean whether MC is valid
     */
    private boolean isValidCredits(int credits) {
        return credits > MINIMUM_MC && credits <= MAXIMUM_MC;
    }

    /**
     * Calculate what should be the user's minimum CAP in order to achieve user's target CAP and display to user.
     */
    public void showResultsToUser(double targetCap,int targetGradedMC) {
        int totalMcToTarget = currentPerson.getCurrentMcAfterSU() + targetGradedMC;
        double targetCapxTargetMC = (double) totalMcToTarget * targetCap;
        double neededCap = (targetCapxTargetMC - currentPerson.getCurrentTotalMcxGrade()) / (double) targetGradedMC;
        if (isValidCap(neededCap)) {
            printTargetResultPossible(targetCap, targetGradedMC, neededCap);
        } else {
            printTargetResultImpossible(targetCap, targetGradedMC, neededCap);
        }
    }

    /**
     * Prints out message for user when the target result cannot be achieved.
     *
     * @param targetCap user's targeted CAP.
     * @param targetGradedMC user's targeted MC.
     */
    private void printTargetResultImpossible(double targetCap, int targetGradedMC, double neededCap) {
        System.out.println("OOPS!! Looks like in order to achieve your target CAP of " + targetCap
                + " with you target MCs of " + targetGradedMC + ".");
        System.out.println("You have to achieve a CAP of " + formatCapToString(neededCap)
                + " for your next " + targetGradedMC + " MCs which is not possible!");
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
