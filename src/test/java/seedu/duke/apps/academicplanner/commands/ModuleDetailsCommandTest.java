package seedu.duke.apps.academicplanner.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.duke.apps.academicplanner.exceptions.AcademicException;
import seedu.duke.apps.moduleloader.ModuleLoader;

//@@author vanessa-kang
class ModuleDetailsCommandTest {
    private static final String VALID_CODE_TEST = "cs2101".toUpperCase();
    private static final String VALID_CODE_RESULT_CODE = "CS2101";
    private static final String VALID_CODE_RESULT_TITLE = "Effective Communication for Computing Professionals";

    private static final String INVALID_CODE_TEST = "ccccc".toUpperCase();
    private static final String INVALID_CODE_ERROR_MESSAGE = INVALID_CODE_TEST
            + " IS NOT OFFERED BY NUS\n"
            + "Exiting current command back to Academic Planner Main Menu.";

    ModuleLoader allModules;
    ModuleDetailsCommand detailer;
    String moduleCode;

    @BeforeEach
    void setup() {
        try {
            allModules = new ModuleLoader();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void moduledetails_validcode_success() {
        try {
            moduleCode = VALID_CODE_TEST;
            detailer = new ModuleDetailsCommand(allModules, moduleCode);
            detailer.execute();
            assertEquals(VALID_CODE_RESULT_CODE,detailer.module.getModuleCode());
            assertEquals(VALID_CODE_RESULT_TITLE,detailer.module.getTitle());
        } catch (AcademicException e) {
            e.printStackTrace();
        }
    }

    @Test
    void moduledetails_invalidcode_exceptionThrown() {
        try {
            moduleCode = INVALID_CODE_TEST;
            detailer = new ModuleDetailsCommand(allModules, moduleCode);
            detailer.execute();
        } catch (AcademicException e) {
            assertEquals(INVALID_CODE_ERROR_MESSAGE, e.getMessage());
        }
    }

}
