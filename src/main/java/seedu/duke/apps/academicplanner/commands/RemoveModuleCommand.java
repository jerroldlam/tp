package seedu.duke.apps.academicplanner.commands;

import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.apps.academicplanner.commons.ModuleValidator;
import seedu.duke.apps.academicplanner.commons.RemoveUtils;
import seedu.duke.apps.academicplanner.exceptions.AcademicException;
import seedu.duke.globalcommons.Command;
import seedu.duke.objects.PartialModule;
import seedu.duke.objects.Person;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class representing an remove module command from the academic planner.
 */
public class RemoveModuleCommand extends Command {
    private static final String EXITING_CURRENT_COMMAND = "Exiting current command back to Academic Planner Main Menu.";
    private static final String ERROR_INVALID_COMMAND = "INVALID COMMAND";
    private static final String ERROR_NOT_ADDED = "You have not added this module into your list yet";

    private RemoveUtils removeUtils;
    private ModuleValidator moduleValidator;
    private HashMap<String, PartialModule> modulesAddedMap;
    private String moduleCode;

    public RemoveModuleCommand(ModuleLoader allModules, Person currentPerson, Scanner in, String moduleCode) {
        this.removeUtils = new RemoveUtils(currentPerson);
        this.moduleValidator = new ModuleValidator(allModules, currentPerson);
        this.modulesAddedMap = currentPerson.getModulesAddedMap();
        this.moduleCode = moduleCode;
    }

    /**
     * Removes moduleCode from user's academic calendar if it exists,
     * else prompts user of error.
     */
    @Override
    public void execute() {
        try {
            if (moduleValidator.isModTakenByUser(moduleCode)) {
                modulesAddedMap.remove(moduleCode);
                removeUtils.removeModuleFromUserModuleList(moduleCode);
            } else {
                throw new AcademicException(ERROR_NOT_ADDED);
            }
        } catch (Exception e) {
            System.out.println(ERROR_INVALID_COMMAND);
            System.out.println(EXITING_CURRENT_COMMAND);
        }
    }
}
