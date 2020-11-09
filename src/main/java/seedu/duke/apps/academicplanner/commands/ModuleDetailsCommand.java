package seedu.duke.apps.academicplanner.commands;

import seedu.duke.apps.academicplanner.commons.ModuleValidator;
import seedu.duke.apps.academicplanner.commons.PrintUtils;
import seedu.duke.apps.academicplanner.exceptions.AcademicException;
import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.global.Command;
import seedu.duke.global.objects.FullModule;

//@@author vanessa-kang
public class ModuleDetailsCommand extends Command {

    private static final String ERROR_NOT_OFFERED = " IS NOT OFFERED BY NUS";

    private final ModuleLoader allModules;
    private String moduleCode;
    private ModuleValidator moduleValidator;
    private PrintUtils printUtils = new PrintUtils();
    FullModule module;

    public ModuleDetailsCommand(ModuleLoader allModules, String moduleCode) {
        this.allModules = allModules;
        this.moduleCode = moduleCode;
        this.moduleValidator = new ModuleValidator(allModules);
    }

    @Override
    public void execute() throws AcademicException {
        validateModuleCode();
        int mapIndex = allModules.getModuleMap().get(moduleCode);
        module = allModules.getModuleFullDetails()[mapIndex];
        printUtils.printModuleDetails(module);
    }

    private void validateModuleCode() throws AcademicException {
        if (!moduleValidator.isModOfferedByNus(moduleCode)) {
            throw new AcademicException(moduleCode + ERROR_NOT_OFFERED);
        }
    }

}
