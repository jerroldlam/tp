package seedu.duke.apps.capcalculator.commons;

import seedu.duke.apps.capcalculator.exceptions.CapCalculatorException;
import seedu.duke.global.objects.PartialModule;
import seedu.duke.global.objects.Person;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;
import static seedu.duke.apps.capcalculator.commons.CalculatorUtils.formatCapToString;

//@@author JuZihao
/**
 * Class representing common functions for the set S/U commands.
 */
public class SetSuUtils {
    private static final String VALID_SEMESTERS = "Valid semesters are integers from 1 to 10, inclusive.";
    private static final String PROMPT_SU_SEMESTER_VALUE = "Please enter a semester you wish to S/U.";
    private static final String SU_SUGGESTION_PROMPT = "In order to obtain the highest Cap possible,"
            + "the modules you should be S/Uing are:";
    private static final String SINGLE_SU_SUGGESTION_PROMPT = "In order to obtain the highest Cap possible,"
            + "the module you should be S/Uing is:";
    private static final String NO_SUGGESTION_PROMPT = "Looks like you do not have to S/U any modules!";
    private static final String PROMPT_MODULE_VALUE = "Please enter how many modules you wish to S/U.";
    private static final String INVALID_SEMESTER_ERROR = "Looks like the semester you entered is not valid!";
    private static final String NOT_NUMBER_ERROR = "Looks like the semester you entered is not an integer!";
    private static final String INVALID_NUMBER_ERROR = "Looks like the number of modules you "
            + "entered is not an integer!";
    private static final String INVALID_MODULE_ERROR = "Looks like you did not take the module you entered "
            + "or the module you entered has a grade that cannot be S/Ued!";
    private static final String NUMBER_OUT_OF_BOUND_ERROR = "Looks like the number you entered "
            + "is more than the numbers of modules you took.";
    private static final String EMPTY_LIST_ERROR = "Looks like your S/U list is empty!";
    private static final String DUPLICATE_MODULE_ERROR = "Looks like you have already added this module!";
    private static final int STARTING_SEMESTER_INDEX = 1;
    private static final int FINAL_SEMESTER_INDEX = 10;
    private static final int DISPLAY_PREFIX = 1;

    private Person currentPerson;
    private Ui ui;
    private Scanner in;

    /**
     * Instantiates a new Set su utils.
     *
     * @param currentPerson the current person
     * @param ui that deals with interaction with user
     */
    public SetSuUtils(Person currentPerson, Ui ui) {
        this.currentPerson = currentPerson;
        this.in = ui.getScanner();
        this.ui = ui;
    }

    /**
     * Prints prompt and help messages for user to enter module's grade.
     *
     * @return the semester entered by user
     * @throws CapCalculatorException if the semester entered is not valid
     */
    public int promptUserForSemester() throws CapCalculatorException {
        try {
            System.out.println(PROMPT_SU_SEMESTER_VALUE);
            System.out.println(VALID_SEMESTERS);
            int semester = Integer.parseInt(in.nextLine().trim());
            if (isValidSemester(semester)) {
                return semester;
            } else {
                throw new CapCalculatorException(INVALID_SEMESTER_ERROR);
            }
        } catch (NumberFormatException e) {
            throw new CapCalculatorException(NOT_NUMBER_ERROR);
        }
    }

    /**
     * Prompt user to enter number of modules.
     *
     * @return number of modules entered by user
     * @throws CapCalculatorException if the number entered is not valid
     */
    public int promptUserForNumberOfModules() throws CapCalculatorException {
        try {
            System.out.println(PROMPT_MODULE_VALUE);
            int numberOfModules = Integer.parseInt(in.nextLine().trim());
            if (isValidNumber(numberOfModules)) {
                return numberOfModules;
            }
            throw new CapCalculatorException(NUMBER_OUT_OF_BOUND_ERROR);
        } catch (NumberFormatException e) {
            throw new CapCalculatorException(INVALID_NUMBER_ERROR);
        }
    }

    /**
     * Returns a sorted S/U list by semester.
     *
     * @param semesterToSu the semester to S/U
     * @return the S/U list by semester
     * @throws CapCalculatorException if the S/U list is empty
     */
    public ArrayList<PartialModule> getSuListBySemester(int semesterToSu) throws CapCalculatorException {
        ArrayList<PartialModule> suList = (ArrayList<PartialModule>) currentPerson.getModulesList().stream()
                .filter((partialModule) -> partialModule.getSemesterIndex() == semesterToSu)
                .filter(this::isValidSuModule)
                .collect(toList());
        if (isEmptyList(suList)) {
            throw new CapCalculatorException(EMPTY_LIST_ERROR);
        } else {
            suList.sort(Comparator.comparing(PartialModule::getCap));
            return suList;
        }
    }

    /**
     * Given a S/U list, show results to user.
     *
     * @param suList the S/U list
     */
    public void showResultsToUser(ArrayList<PartialModule> suList) {
        double currentCap = currentPerson.getCurrentTotalMcxGrade() / (double)currentPerson.getCurrentMcAfterSU();
        int currentGradedMCs = currentPerson.getCurrentMcAfterSU();
        showInitialCapToUser(currentCap, currentGradedMCs);
        ui.printStars();
        showCapAfterEachSu(suList, currentCap, currentPerson.getCurrentTotalMcxGrade(), currentGradedMCs);
    }

    /**
     * Display cap after each S/U.
     *
     * @param suList the S/U list
     * @param currentCap the current cap
     * @param totalMcxGrade the total MC multiply by the grade
     * @param currentGradedMCs the current graded MCs
     */
    public void showCapAfterEachSu(ArrayList<PartialModule> suList, double currentCap,
                                    double totalMcxGrade, int currentGradedMCs) {
        double bestCap = currentCap;
        int bestGradedMCs = currentGradedMCs;
        int numberOfModulesToSU = 0;

        for (PartialModule module : suList) {
            totalMcxGrade -= module.getCap() * module.getModuleCredit();
            currentGradedMCs -= module.getModuleCredit();
            System.out.println("S/U your module of " + module.getModuleCode() + " with grade " + module.getGrade()
                    + " will give you a CAP of: " + formatCapToString(totalMcxGrade / (double)currentGradedMCs));
            System.out.println("Your graded MCs after S/Uing this module is: " + currentGradedMCs);
            ui.printNewLine();
            if (bestCap < (totalMcxGrade / (double)currentGradedMCs)) {
                bestCap = totalMcxGrade / (double)currentGradedMCs;
                bestGradedMCs = currentGradedMCs;
                numberOfModulesToSU++;
            }
        }
        showBestResultsForSu(bestCap, bestGradedMCs);
        ui.printStars();
        showSuggestedSuOptions(suList, numberOfModulesToSU);
    }

    /**
     * Display the suggested S/U options.
     *
     * @param suList the S/U list
     * @param numberOfModulesToSU the number of modules to S/U
     */
    public void showSuggestedSuOptions(ArrayList<PartialModule> suList, int numberOfModulesToSU) {
        if (numberOfModulesToSU > 1) {
            System.out.println(SU_SUGGESTION_PROMPT);
            for (int i = 0; i < numberOfModulesToSU; i++) {
                PartialModule moduleToSu = suList.get(i);
                System.out.println(moduleToSu.getModuleCode() + " with grade " + moduleToSu.getGrade()
                        + " and modular credit of " + moduleToSu.getModuleCredit() + ".");
            }
        } else if (numberOfModulesToSU == 1) {
            System.out.println(SINGLE_SU_SUGGESTION_PROMPT);
            PartialModule moduleToSu = suList.get(0);
            System.out.println(moduleToSu.getModuleCode() + " with grade " + moduleToSu.getGrade()
                    + " and modular credit of " + moduleToSu.getModuleCredit() + ".");
        } else {
            System.out.println(NO_SUGGESTION_PROMPT);
        }
    }

    /**
     * Display best results after S/Uing.
     *
     * @param bestCap the best cap
     * @param bestGradedMCs the best graded MCs
     */
    public void showBestResultsForSu(double bestCap, int bestGradedMCs) {
        System.out.println("Your highest CAP possible is: " + formatCapToString(bestCap)
                + " with a graded MC of " + bestGradedMCs);
    }

    /**
     * Display initial cap to user.
     *
     * @param currentCap the current cap
     * @param currentGradedMCs the current graded MCs
     */
    public void showInitialCapToUser(double currentCap, int currentGradedMCs) {
        System.out.println("Your CAP without S/U any module is: " + formatCapToString(currentCap));
        System.out.println("Your graded MCs without S/U any module is: " + currentGradedMCs);
    }

    /**
     * Display the S/U list to user.
     *
     * @param suList the S/U list
     */
    public void showSuListToUser(ArrayList<PartialModule> suList) {
        System.out.println("Modules in your current S/U list: ");
        for (PartialModule modules : suList) {
            System.out.println("   " + modules.getModuleCode() + "   " + modules.getGrade());
        }
    }

    /**
     * Main function to obtain the S/U list given some module codes.
     * Returns the S/U list.
     *
     * @param numberOfModulesToSu number entered by user
     * @return the S/U list
     * @throws CapCalculatorException if the module is not valid
     */
    public ArrayList<PartialModule> getSuListByModule(int numberOfModulesToSu) throws CapCalculatorException {
        ArrayList<PartialModule> filteredList = (ArrayList<PartialModule>) currentPerson.getModulesList().stream()
                .filter(this::isValidSuModule)
                .collect(toList());

        ArrayList<PartialModule> suList = new ArrayList<>();

        for (int i = 0; i < numberOfModulesToSu; i++) {
            promptUserForModuleCode(i + DISPLAY_PREFIX);
            String moduleCode = in.nextLine().trim();
            PartialModule moduleToSu = getSuModule(filteredList,moduleCode);
            addModuleToSuList(suList, moduleToSu);
        }
        suList.sort(Comparator.comparing(PartialModule::getCap));
        return suList;
    }

    /**
     * Add the module to be S/Ued into the list.
     *
     * @param suList the list of modules to be S/Ued
     * @param moduleToSu the module to be added into the list
     * @throws CapCalculatorException if the module is already in the list
     */
    public void addModuleToSuList(ArrayList<PartialModule> suList,
                                   PartialModule moduleToSu) throws CapCalculatorException {
        if (suList.contains(moduleToSu)) {
            throw new CapCalculatorException(DUPLICATE_MODULE_ERROR);
        }
        suList.add(moduleToSu);
    }

    /**
     * Returns the module that the user want to S/U given the module code.
     *
     * @param filteredList the list of valid modules to S/U
     * @param moduleCode the target module that user want to S/U
     * @return the module to be S/Ued
     * @throws CapCalculatorException if there is no valid module found
     */
    public PartialModule getSuModule(ArrayList<PartialModule> filteredList,
                                      String moduleCode) throws CapCalculatorException {
        for (PartialModule module : filteredList) {
            if (module.getModuleCode().equalsIgnoreCase(moduleCode)) {
                return module;
            }
        }
        throw new CapCalculatorException(INVALID_MODULE_ERROR);
    }

    /**
     * Print prompt for user to enter the module code to S/U.
     *
     * @param displayIndex index to be displayed ot user
     */
    private void promptUserForModuleCode(int displayIndex) {
        System.out.println("What is your " + getAbbreviations(displayIndex) + "module?");
    }

    /**
     * Returns true when the number of modules entered by user is more than zero,
     * and less than the total modules taken by user, else return false.
     *
     * @param numberOfModulesToSu the number entered by user
     * @return boolean
     */
    private boolean isValidNumber(int numberOfModulesToSu) {
        return (numberOfModulesToSu > 0 && numberOfModulesToSu <= currentPerson.getModulesList().size());
    }

    /**
     * Returns true when the module is a valid modules to be S/Ued,
     * else return false.
     *
     * @param partialModule the modules to be checked
     * @return boolean
     */
    private boolean isValidSuModule(PartialModule partialModule) {
        return partialModule.getCap() >= 0;
    }

    /**
     * Returns true if the SU list is empty else false.
     *
     * @param suList the list of modules to be S/Ued
     * @return boolean boolean
     */
    private boolean isEmptyList(ArrayList<PartialModule> suList) {
        return suList.size() == 0;
    }

    /**
     * Function to return the abbreviation for the number.
     *
     * @param number number to return the abbreviation for
     * @return string
     */
    private String getAbbreviations(int number) {
        if (number % 10 == 1 & number != 11) {
            return number + "st ";
        } else if (number % 10 == 2 & number != 12) {
            return number + "nd ";
        } else if (number % 10 == 3 & number != 13) {
            return number + "rd ";
        } else {
            return number + "th ";
        }
    }

    //@@author jerroldlam
    /**
     * Returns true if semesterIndex is a valid semesterIndex,
     * else returns false.
     *
     * @param semesterIndex semesterIndex to check
     * @return false
     */
    public static boolean isValidSemester(int semesterIndex) {
        return (semesterIndex >= STARTING_SEMESTER_INDEX && semesterIndex <= FINAL_SEMESTER_INDEX);
    }
}
