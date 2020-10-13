package seedu.duke.apps.academicplanner.commons;

import seedu.duke.apps.capcalculator.commons.CalculatorUtils;
import seedu.duke.objects.PartialModule;
import seedu.duke.objects.Person;
import java.util.ArrayList;

/**
 * Class representing remove module utilities from the remove module command.
 */
public class RemoveUtils {
    private static final int FROM_REMOVE = 3;

    private final ArrayList<PartialModule> modulesList;
    private final CalculatorUtils calculatorUtils;

    public RemoveUtils(Person currentPerson) {
        this.modulesList = currentPerson.getModulesList();
        this.calculatorUtils = new CalculatorUtils(currentPerson);
    }

    /**
     * Removes module from user's module list.
     * PartialModule must exist in user's module list.
     *
     * @param moduleCode module to remove.
     */
    public void removeModuleFromUserModuleList(String moduleCode) {
        int totalNumberOfModules = modulesList.size();
      
        for (PartialModule item : modulesList) {
            if (item.getModuleCode().equals(moduleCode)) {
                System.out.println(item.getModuleCode() + " has been removed from the list");
                calculatorUtils.updateCap(FROM_REMOVE, item);
                modulesList.remove(item);

                assert modulesList.size() == totalNumberOfModules - 1;
                return;
            }
        }
    }
}
