package seedu.duke.apps.academicplanner.commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.global.objects.FullModule;
import seedu.duke.global.objects.Person;

//@@author jerroldlam
/**
 * Class of a module validation object.
 */
public class ModuleValidator {
    private static final int STARTING_SEMESTER_INDEX = 1;
    private static final int FINAL_SEMESTER_INDEX = 10;

    private final ModuleLoader allModules;
    private final HashMap<String, ArrayList<Integer>> modulesAddedMap;

    /**
     * Default constructor for ModuleValidator.
     *
     * @param allModules all modules offered by NUS
     * @param currentPerson current User
     */
    public ModuleValidator(ModuleLoader allModules, Person currentPerson) {
        this.allModules = allModules;
        this.modulesAddedMap = currentPerson.getModulesAddedMap();
    }

    /**
     * Overload constructor for EditUtils.
     *
     * @param allModules all modules offered by NUS
     */
    public ModuleValidator(ModuleLoader allModules) {
        this.allModules = allModules;
        this.modulesAddedMap = null;
    }

    /**
     * Overload constructor for SharedUtils.
     *
     */
    public ModuleValidator() {
        this.allModules = null;
        this.modulesAddedMap = null;
    }

    /**
     * Returns true if module code is offered by NUS,
     * else returns false.
     * @param moduleCode input module code
     * @return boolean of module code in FullModule
     */
    public boolean isModOfferedByNus(String moduleCode) {
        boolean isOffered = allModules.getModuleMap().get(moduleCode) != null;
        return (isOffered);
    }

    /**
     * Returns true if module is in the user's academic calendar,
     * else returns false.
     *
     * @param moduleCode moduleCode to check
     * @return boolean
     */
    public boolean isModTakenByUser(String moduleCode) {
        return (modulesAddedMap.containsKey(moduleCode));
    }

    /**
     * Returns true if semsesterIndex is a valid semesterIndex,
     * else returns false.
     *
     * @param semesterIndex semesterIndex to check
     * @return false
     */
    public static boolean isValidSemester(int semesterIndex) {
        return (semesterIndex >= STARTING_SEMESTER_INDEX && semesterIndex <= FINAL_SEMESTER_INDEX);
    }

    /**
     * Gets the module credit for a certain module code from actual database.
     *
     * @param moduleCode Module code to be retrieved
     *
     * @return Module credit
     */
    public Integer getModuleCreditFromDatabase(String moduleCode) {
        Map<String, Integer> moduleMapReference = allModules.getModuleMap();
        FullModule[] moduleArray = allModules.getModuleFullDetails();

        Integer moduleCodeIndex = moduleMapReference.get(moduleCode);
        return moduleArray[moduleCodeIndex].getModuleCredit();
    }

    /**
     * Returns true if grade is a Grade option offered by NUS,
     * else returns false.
     *
     * @param grade grade to check
     * @return boolean
     */
    public boolean isValidGrade(String grade) {
        switch (grade.toUpperCase()) {
        case "A+":
            //Fallthrough
        case "A":
            //Fallthrough
        case "A-":
            //Fallthrough
        case "B+":
            //Fallthrough
        case "B":
            //Fallthrough
        case "B-":
            //Fallthrough
        case "C+":
            //Fallthrough
        case "C":
            //Fallthrough
        case "D+":
            //Fallthrough
        case "D":
            //Fallthrough
        case "F":
            //Fallthrough
        case "CS":           //Completed Satisfactorily
            //Fallthrough
        case "CU":           //Completed Unsatisfactorily
            //Fallthrough
        case "S":            //Satisfactory
            //Fallthrough
        case "U":            //Unsatisfactory
            //Fallthrough
        case "W":            //Withdrawn
            //Fallthrough
        case "IC":           //Incomplete
            //Fallthrough
        case "IP":           //In progress
            //Fallthrough
        case "AUD":          //Audit
            //Fallthrough
        case "WU":           //Withdrawn from University
            //Fallthrough
        case "EXE" :         //Exempted
            //Fallthrough
        case "NT":           //Not taken
            return true;
        default:
            return false;
        }
    }

    /**
     * Returns true if grade is a valid retakeable grade,
     * else returns false.
     *
     * @param grade grade to check
     * @return boolean
     */
    public boolean isRetakeGrade(String grade) {
        switch (grade.toUpperCase()) {
        case "F":
            //Fallthrough
        case "CU" :
            //Fallthrough
        case "U":
            //Fallthrough
        case "W":
            //Fallthrough
        case "AUD":
            //Fallthrough
        case "WU":
            //Fallthrough
        case "EXE":
            //Fallthrough
        case "IC":
            //Fallthrough
            return true;
        default:
            return false;
        }
    }
}
