package seedu.duke.apps.academicplanner.commands;

import seedu.duke.apps.academicplanner.commons.ModuleValidator;
import seedu.duke.apps.academicplanner.commons.RemoveUtils;
import seedu.duke.apps.academicplanner.exceptions.AcademicException;
import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.global.Command;
import seedu.duke.global.objects.Person;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

//@@author harryleecp
/**
 * Class representing an remove module command from the academic planner.
 */
public class RemoveModuleCommand extends Command {
    private static final String ERROR_INVALID_MODULE = "The module you entered is not offered by NUS";
    private static final String ERROR_NOT_ADDED = "You have not added this module into your list yet";
    private static final String MODULE_REMOVED = "Module removed successfully.";

    private RemoveUtils removeUtils;
    private ModuleValidator moduleValidator;
    private Person currentPerson;
    private String moduleCode;
    private Storage storage;
    private Ui ui;

    public RemoveModuleCommand(ModuleLoader allModules, Person currentPerson,
                               Ui ui, String moduleCode, Storage storage) {
        this.removeUtils = new RemoveUtils(ui, currentPerson);
        this.moduleValidator = new ModuleValidator(allModules, currentPerson);
        this.moduleCode = moduleCode;
        this.currentPerson = currentPerson;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Removes moduleCode from user's academic calendar if it exists,
     * else prompts user of error.
     */
    @Override
    public void execute() throws AcademicException {
        if (!moduleValidator.isModOfferedByNus(moduleCode)) {
            throw new AcademicException(ERROR_INVALID_MODULE);
        } else if (moduleValidator.isModTakenByUser(moduleCode)) {
            removeUtils.removeModuleFromUserModuleList(moduleCode);
            System.out.println(MODULE_REMOVED);
            storage.saver(currentPerson);
        } else {
            throw new AcademicException(ERROR_NOT_ADDED);
        }
    }
}
