package seedu.duke.apps.academicplanner.commons;

import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.apps.capcalculator.commons.CalculatorUtils;
import seedu.duke.global.objects.PartialModule;
import seedu.duke.global.objects.Person;
import java.util.ArrayList;
import java.util.HashMap;

//@@author jerroldlam
/**
 * Class representing add module utilities from the add module command.
 */
public class AddUtils {
    private static final int FROM_ADD = 1;
    private static final int EMPTY = 0;

    private final ModuleLoader allModules;
    private final ArrayList<PartialModule> modulesList;
    private final HashMap<String, ArrayList<Integer>> modulesAddedMap;
    private final CalculatorUtils calculatorUtils;

    /**
     * Default constructor for AddUtils.
     *
     * @param allModules all modules offered by NUS
     * @param currentPerson current User
     */
    public AddUtils(ModuleLoader allModules, Person currentPerson) {
        this.allModules = allModules;
        this.modulesList = currentPerson.getModulesList();
        this.modulesAddedMap = currentPerson.getModulesAddedMap();
        this.calculatorUtils = new CalculatorUtils(currentPerson);
    }

    /**
     * Creates a new instance of a module of the parameters and adds it into the
     * hashmap and array list of user modules.
     *
     * @param moduleCode module to be added
     * @param semesterValue semester that module is taken in
     * @param gradeValue grade achieved for module
     * @param moduleCredit module's credit weightage
     */
    public void addModuleToUser(String moduleCode, int semesterValue,
                                String gradeValue, int moduleCredit, int... from) {
        PartialModule newModuleToAdd = new PartialModule(moduleCode, semesterValue, gradeValue, moduleCredit);
        populate(moduleCode, newModuleToAdd);
        calculatorUtils.updateCap(FROM_ADD, newModuleToAdd);
        if (from.length == 0) {
            System.out.println(newModuleToAdd.getModuleCode()
                    + " added into Semester " + semesterValue + ".");
        }
    }

    /**
     * Populates the user's arraylist and hashmap with the new module.
     *
     * @param moduleCode module code to be entered
     * @param newModuleToAdd module object to be added
     */
    private void populate(String moduleCode, PartialModule newModuleToAdd) {
        modulesList.add(newModuleToAdd);
        ArrayList<Integer> addedModuleIndex = modulesAddedMap.get(moduleCode);

        if (addedModuleIndex == null || addedModuleIndex.size() == EMPTY) {
            ArrayList<Integer> integerArrayToAdd = new ArrayList<>();
            integerArrayToAdd.add(modulesList.size() - 1);
            modulesAddedMap.put(moduleCode, integerArrayToAdd);
        } else {
            addedModuleIndex.add(modulesList.size() - 1);
            modulesAddedMap.put(moduleCode, addedModuleIndex);
        }
    }

    /**
     * Function to return module credits for module code.
     *
     * @param moduleCode string of module code
     * @return int of module credit
     */
    public int getModuleCreditForModule(String moduleCode) {
        int mapIndex = allModules.getModuleMap().get(moduleCode);
        int moduleCredit = allModules.getModuleFullDetails()[mapIndex].getModuleCredit();
        assert moduleCredit >= 0;
        return moduleCredit;
    }
}
