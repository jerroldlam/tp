package seedu.duke.apps.academicplanner.commons;

import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.objects.PartialModule;
import seedu.duke.objects.Person;
import java.util.ArrayList;
import java.util.HashMap;

public class ModuleValidator {
    private static final int STARTING_SEMESTER_INDEX = 1;
    private static final int FINAL_SEMESTER_INDEX = 10;

    private final ModuleLoader allModules;
    private final ArrayList<PartialModule> modulesList;
    private final HashMap<String, PartialModule> modulesAddedMap;

    public ModuleValidator(ModuleLoader allModules, Person currentPerson) {
        this.allModules = allModules;
        this.modulesList = currentPerson.getModulesList();
        this.modulesAddedMap = currentPerson.getModulesAddedMap();
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
    public boolean isValidSemester(int semesterIndex) {
        return (semesterIndex >= STARTING_SEMESTER_INDEX && semesterIndex <= FINAL_SEMESTER_INDEX);
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
}
