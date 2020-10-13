package seedu.duke.apps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.apps.moduleloader.exceptions.ModuleLoaderException;
import seedu.duke.objects.FullModule;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ModuleLoaderTest {
    private static final int TOTAL_NUMBER_OF_MODULES = 12436;
    private static final int INDEX_OF_CS1010 = 1786;

    private ModuleLoader allModules;

    @BeforeEach
    public void setup() {
        try {
            allModules = new ModuleLoader();
        } catch (ModuleLoaderException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void getModuleMap_loadAllModuleData_success() {
        Map<String, Integer> moduleMap = allModules.getModuleMap();

        assertEquals(moduleMap.get("CS1010"), INDEX_OF_CS1010);
    }

    @Test
    void getModuleFullDetails_loadAllModuleData_success() {
        FullModule[] modules = allModules.getModuleFullDetails();

        assertEquals(modules.length, TOTAL_NUMBER_OF_MODULES);
        assertEquals(modules[1786].toString(), "{\"additionalProperties\":{},"
                + "\"attributes\":[true,false,false,false,false,false,false,false,false],"
                + "\"corequisite\":\"\",\"department\":\"Computer Science\",\"faculty\":\"Computing\","
                + "\"moduleCode\":\"CS1010\",\"moduleCredit\":4,"
                + "\"preclusion\":\"CS1010E, CS1010J, CS1010S, CS1010X, CS1010XCP, CS1101S\","
                + "\"prerequisite\":\"\",\"semester\":[1,2],\"title\":\"Programming Methodology\"}");
    }

    @Test
    void getModuleFullDetails_loadAllModuleData_fail() {
        Exception exception = assertThrows(ModuleLoaderException.class, () -> {
            allModules = new ModuleLoader(false);
        });

        assertTrue(exception.getMessage().contains("Data for Modules corrupted!\nTerminating program..."));
    }
}