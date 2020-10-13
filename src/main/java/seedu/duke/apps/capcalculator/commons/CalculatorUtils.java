package seedu.duke.apps.capcalculator.commons;

import seedu.duke.objects.PartialModule;
import seedu.duke.objects.Person;
import java.text.DecimalFormat;

/**
 * Class representing common functions for the CAP Calculator.
 */
public class CalculatorUtils {
    private static final int FROM_ADD = 1;
    private static final int FROM_EDIT = 2;
    private static final int FROM_REMOVE = 3;
    private static final DecimalFormat formatFinalCap = new DecimalFormat("#.##");
    protected static final double MAXIMUM_CAP = 5.00;

    private final Person currentPerson;

    public CalculatorUtils(Person currentPerson) {
        this.currentPerson = currentPerson;
    }

    /**
     * Update Cap after every change in current module list.
     *
     * @param type An int storing the type of function calling update cap
     * @param currentModule A PartialModule object storing current module
     * @param caps An optional number of double storing old cap and new cap (If you send in old, must send in new also)
     */
    public void updateCap(int type, PartialModule currentModule, double... caps) {
        // Caps is an array, 0 being oldCap, 1 being newCap
        if (type == FROM_ADD) {
            updateCapFromAdd(currentModule);

        } else if (type == FROM_REMOVE) {
            //Decreasing total MC regardless of SU
            currentPerson.setCurrentMc(currentPerson.getCurrentMc()
                    - currentModule.getModuleCredit());

            //Decreasing total MC after SU only if module is not SU
            if (currentModule.getCap() != -1.00) {
                editCapNonSuToSu(currentModule, currentModule.getCap());
            }

        } else if (type == FROM_EDIT) {
            if (isFromSuToNonSu(caps)) {
                //Case where previously was SU but new is not SU
                editCapSuToNonSu(currentModule, caps[1]);

            }  else if (isFromNonSuToSu(caps)) {
                //Case where previously was not SU but now is SU
                editCapNonSuToSu(currentModule, caps[0]);

            } else if (isFromNonSuToNonSu(caps)) {
                //Case where previously and new cap are not SU but not the same
                editCapNonSuToNonSu(currentModule, caps);
            }
        }
    }

    /**
     * Returns true if edited module was from a special grade to special grade,
     * else returns false.
     *
     * @param caps Academic points of original and edited module
     * @return boolean
     */
    private boolean isFromNonSuToNonSu(double[] caps) {
        return caps[0] != caps[1];
    }

    /**
     * Returns true if edited module was from a special grade to letter grade,
     * else returns false.
     *
     * @param caps Academic points of original and edited module
     * @return boolean
     */
    private boolean isFromNonSuToSu(double[] caps) {
        return caps[0] != -1.00 && caps[1] == -1.00;
    }

    /**
     * Returns true if edited module was from a special grade to letter grade,
     * else returns false.
     *
     * @param caps Academic points of original and edited module
     * @return boolean
     */
    private boolean isFromSuToNonSu(double[] caps) {
        return caps[0] == -1.00 && caps[1] != -1.00;
    }

    /**
     * Updates CAP when User edits module from a special grade to a special grade.
     *
     * @param currentModule module that was edited
     * @param caps array of previous and new cap
     */
    private void editCapNonSuToNonSu(PartialModule currentModule, double[] caps) {
        double oldMCxGrade = caps[0] * currentModule.getModuleCredit();
        double newMCxGrade = caps[1] * currentModule.getModuleCredit();
        double mcxGradeToSet = newMCxGrade - oldMCxGrade;

        //Updating total academic points to this point
        currentPerson.setCurrentTotalMcxGrade(currentPerson.getCurrentTotalMcxGrade()
                + mcxGradeToSet);
    }

    /**
     * Updates CAP when User edits module from a special grade to a letter grade.
     *
     * @param currentModule module that was edited
     * @param cap user's CAP
     */
    private void editCapNonSuToSu(PartialModule currentModule, double cap) {
        double mcxGradeToMinus = cap * currentModule.getModuleCredit();

        //Updating total MCs
        currentPerson.setCurrentMcAfterSU(currentPerson.getCurrentMcAfterSU()
                - currentModule.getModuleCredit());

        //Updated total Academic Points
        currentPerson.setCurrentTotalMcxGrade(currentPerson.getCurrentTotalMcxGrade()
                - mcxGradeToMinus);
    }

    /**
     * Updates CAP when User edits module from a letter grade to a special grade.
     *
     * @param currentModule module that was edited
     * @param cap user's CAP
     */
    private void editCapSuToNonSu(PartialModule currentModule, double cap) {
        double newMCxGrade = cap * currentModule.getModuleCredit();

        //Updating total MCs
        currentPerson.setCurrentMcAfterSU(currentPerson.getCurrentMcAfterSU()
                + currentModule.getModuleCredit());

        //Updated total Academic Points
        currentPerson.setCurrentTotalMcxGrade(currentPerson.getCurrentTotalMcxGrade()
                + newMCxGrade);
    }

    /**
     * Updates CAP when User adds in a new module.
     *
     * @param currentModule module that is newly added
     */
    private void updateCapFromAdd(PartialModule currentModule) {
        //Incrementing total MC regardless of SU
        int moduleCredit = currentModule.getModuleCredit();
        currentPerson.setCurrentMc(currentPerson.getCurrentMc() + moduleCredit);

        //Incrementing total MC after SU only if module is not SU
        if (currentModule.getCap() != -1.00) {
            editCapSuToNonSu(currentModule, currentModule.getCap());
        }
    }

    /**
     * Returns CAP score as a string.
     *
     * @param academicPoint academic point to parse
     * @return string of academic point
     */
    public static String formatCapToString(double academicPoint) {
        if (isNaN(academicPoint)) {
            return "0";
        }
        return formatFinalCap.format(academicPoint);
    }

    /**
     * Returns true if CAP is NaN
     * else returns false.
     *
     * @param academicPoint academic point to check
     * @return boolean
     */
    public static boolean isNaN(double academicPoint) {
        return (academicPoint != academicPoint);
    }
}
