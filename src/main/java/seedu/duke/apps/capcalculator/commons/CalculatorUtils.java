package seedu.duke.apps.capcalculator.commons;

import seedu.duke.global.objects.PartialModule;
import seedu.duke.global.objects.Person;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

//@@author Khenus
/**
 * Class representing common functions for the CAP Calculator.
 */
public class CalculatorUtils {
    private static final int FROM_ADD = 1;
    private static final int FROM_EDIT = 2;
    private static final int FROM_REMOVE = 3;
    private static final DecimalFormat formatFinalCap = new DecimalFormat("#.##");
    protected static final double MAXIMUM_CAP = 5.00;
    protected static final double F_GRADE_CAP = 0.00;
    protected static final double U_GRADE_CAP = -2.00;
    protected static final double NON_GRADED_CAP = -3.00;
    protected static final double S_GRADE_CAP = -1.00;

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
        if (type == FROM_ADD && !isNonGraded(currentModule.getCap())) {
            updateCapFromAdd(currentModule);

        } else if (type == FROM_REMOVE) {
            if (!isNonGraded(currentModule.getCap())) {
                //Decreasing total MC regardless of SU
                currentPerson.setCurrentMc(currentPerson.getCurrentMc()
                        - currentModule.getModuleCredit());

                //Decreasing total MC after SU only if module is not SU
                if (!isSu(currentModule.getCap())) {
                    editCapGradedToSu(currentModule, currentModule.getCap());
                }
            }

        } else if (type == FROM_EDIT) {
            if (isFromSuToGraded(caps)) {
                //Case where previously was SU but new is not SU
                editCapSuToGraded(currentModule, caps[1]);

            }  else if (isFromGradedToSu(caps)) {
                //Case where previously was not SU but now is SU
                editCapGradedToSu(currentModule, caps[0]);

            } else if (isFromGradedToGraded(caps)) {
                //Case where previously and new cap are not SU but not the same
                editCapGradedToGraded(currentModule, caps);

            } else if (isFromGradedToNonGraded(caps)) {
                //Case where previously was graded but new is special
                currentPerson.setCurrentMc(currentPerson.getCurrentMc()
                        - currentModule.getModuleCredit());
                editCapGradedToSu(currentModule, caps[0]);

            } else if (isFromSuToNonGraded(caps)) {
                //Case where previously was S/U but new is special
                currentPerson.setCurrentMc(currentPerson.getCurrentMc()
                        - currentModule.getModuleCredit());

            } else if (isFromNonGradedToGraded(caps)) {
                //Case where previously was special but new is graded or S/U
                updateCapFromAdd(currentModule);
            }
        }
    }


    /**
     * Returns true if the module provided was from a letter grade,
     * else returns false.
     *
     * @param cap Academic points of original and edited module
     * @return boolean
     */
    private boolean isGraded(double cap) {
        return cap >= F_GRADE_CAP;
    }

    /**
     * Returns true if the module provided was from a S/U grade,
     * else returns false.
     *
     * @param cap Academic points of original and edited module
     * @return boolean
     */
    private boolean isSu(double cap) {
        return (cap == S_GRADE_CAP);
    }

    /**
     * Returns true if the module provided was from a special grade,
     * else returns false.
     *
     * @param cap Academic points of original and edited module
     * @return boolean
     */
    private boolean isNonGraded(double cap) {
        return cap == NON_GRADED_CAP || cap == U_GRADE_CAP;
    }

    /**
     * Returns true if edited module was from a letter grade to letter grade,
     * else returns false.
     *
     * @param caps Academic points of original and edited module
     * @return boolean
     */
    private boolean isFromGradedToGraded(double[] caps) {
        return caps[0] != caps[1] && isGraded(caps[0]) && isGraded(caps[1]);
    }

    /**
     * Returns true if edited module was from a letter grade to S/U grade,
     * else returns false.
     *
     * @param caps Academic points of original and edited module
     * @return boolean
     */
    private boolean isFromGradedToSu(double[] caps) {
        return isGraded(caps[0]) && isSu(caps[1]);
    }

    /**
     * Returns true if edited module was from a letter grade to a special grade,
     * else returns false.
     *
     * @param caps Academic points of original and edited module
     * @return boolean
     */
    private boolean isFromGradedToNonGraded(double[] caps) {
        return isGraded(caps[0]) && isNonGraded(caps[1]);
    }

    /**
     * Returns true if edited module was from a S/U grade to letter grade,
     * else returns false.
     *
     * @param caps Academic points of original and edited module
     * @return boolean
     */
    private boolean isFromSuToGraded(double[] caps) {
        return isSu(caps[0]) && isGraded(caps[1]);
    }

    /**
     * Returns true if edited module was from a S/U grade to special grade,
     * else returns false.
     *
     * @param caps Academic points of original and edited module
     * @return boolean
     */
    private boolean isFromSuToNonGraded(double[] caps) {
        return isSu(caps[0]) && isNonGraded(caps[1]);
    }

    /**
     * Returns true if edited module was from a special grade to graded grade,
     * else returns false.
     *
     * @param caps Academic points of original and edited module
     * @return boolean
     */
    private boolean isFromNonGradedToGraded(double[] caps) {
        return isNonGraded(caps[0]) && !isNonGraded(caps[1]);
    }

    /**
     * Updates CAP when User edits module from a letter grade to a letter grade.
     *
     * @param currentModule module that was edited
     * @param caps array of previous and new cap
     */
    private void editCapGradedToGraded(PartialModule currentModule, double[] caps) {
        double oldMCxGrade = caps[0] * currentModule.getModuleCredit();
        double newMCxGrade = caps[1] * currentModule.getModuleCredit();
        double mcxGradeToSet = newMCxGrade - oldMCxGrade;

        //Updating total academic points to this point
        currentPerson.setCurrentTotalMcxGrade(currentPerson.getCurrentTotalMcxGrade()
                + mcxGradeToSet);
    }

    /**
     * Updates CAP when User edits module from a letter grade to a S/U grade.
     *
     * @param currentModule module that was edited
     * @param cap user's CAP
     */
    private void editCapGradedToSu(PartialModule currentModule, double cap) {
        double mcxGradeToMinus = cap * currentModule.getModuleCredit();

        //Updating total MCs
        currentPerson.setCurrentMcAfterSU(currentPerson.getCurrentMcAfterSU()
                - currentModule.getModuleCredit());

        //Updated total Academic Points
        currentPerson.setCurrentTotalMcxGrade(currentPerson.getCurrentTotalMcxGrade()
                - mcxGradeToMinus);
    }

    /**
     * Updates CAP when User edits module from a S/U grade to a letter grade.
     *
     * @param currentModule module that was edited
     * @param cap user's CAP
     */
    private void editCapSuToGraded(PartialModule currentModule, double cap) {
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
            editCapSuToGraded(currentModule, currentModule.getCap());
        }
    }

    //@@author JuZihao
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

    /**
     * Returns a double value that has been rounded at a given decimal place.
     *
     * @param value to be rounded
     * @param places number of decimal points to be rounded
     * @return a double value that has been rounded
     */
    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
