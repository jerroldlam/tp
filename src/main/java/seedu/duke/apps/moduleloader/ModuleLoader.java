package seedu.duke.apps.moduleloader;

import com.google.gson.Gson;
import seedu.duke.apps.moduleloader.exceptions.ModuleLoaderException;
import seedu.duke.objects.FullModule;
import java.io.InputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Class representing function to load all modules offered by NUS.
 */
public class ModuleLoader {
    private static final int TOTAL_NUMBER_OF_MODULES = 12436;
    public static final String NEW_LINE = "\n";
    public static final String MISSING_MODULE_DATA = "Data for Modules not found!";
    public static final String TERMINATION = "Terminating program...";
    public static final String CORRUPTED_MODULE_DATA = "Data for Modules corrupted!";

    private Map<String, Integer> moduleMap;
    private FullModule[] moduleFullDetails;

    /**
     * Default constructor for to load all modules from JSON file.
     */
    public ModuleLoader() throws ModuleLoaderException {
        try {
            moduleMap = new HashMap<>();
            Gson gson = new Gson();

            InputStream in = ModuleLoader.class.getResourceAsStream("/ModuleData.json");
            Reader jsonReader = new InputStreamReader(in);
            moduleFullDetails = gson.fromJson(jsonReader, FullModule[].class);

            for (int i = 0; i < moduleFullDetails.length; i++) {
                moduleMap.put(moduleFullDetails[i].getModuleCode(), i);
            }

            if (moduleFullDetails.length != TOTAL_NUMBER_OF_MODULES) {
                throw new ModuleLoaderException(CORRUPTED_MODULE_DATA + NEW_LINE + TERMINATION);
            }
        } catch (NullPointerException e) {
            throw new ModuleLoaderException(MISSING_MODULE_DATA + NEW_LINE + TERMINATION);
        }
    }

    /**
     * Constructor for checking condition where loading all module from JSON file fails.
     * Used in development only.
     *
     * @param isProperCreation boolean indicating non-proper creation of module initializer
     */
    public ModuleLoader(boolean isProperCreation) throws ModuleLoaderException {
        throw new ModuleLoaderException(CORRUPTED_MODULE_DATA + NEW_LINE + TERMINATION);
    }

    /**
     * Getter for module map.
     *
     * @return Map
     */
    public Map<String, Integer> getModuleMap() {
        return this.moduleMap;
    }

    /**
     * Getter for full module details.
     *
     * @return An Array of FullModule
     */
    public FullModule[] getModuleFullDetails() {
        return this.moduleFullDetails;
    }
}
