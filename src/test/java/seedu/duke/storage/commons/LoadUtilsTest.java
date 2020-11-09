package seedu.duke.storage.commons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.apps.moduleloader.exceptions.ModuleLoaderException;
import seedu.duke.global.objects.Person;
import seedu.duke.storage.exceptions.LoadFileException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author Khenus
class LoadUtilsTest {
    private Person person;
    private String wrongFileName = "PlanNus.wrong";
    private ModuleLoader allModules;

    @BeforeEach
    void setup() throws ModuleLoaderException {
        person = new Person("test");
        allModules = new ModuleLoader();
    }

    @Test
    void load_LoadFileExceptionTesting_success() {
        LoadUtils loader = new LoadUtils(person, wrongFileName, allModules);
        Exception exception = assertThrows(LoadFileException.class, () -> {
            loader.load();
        });

        assertTrue(exception.getMessage().contains("Save file not found!"));
    }
}
