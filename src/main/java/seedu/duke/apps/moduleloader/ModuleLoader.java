package seedu.duke.apps.moduleloader;

import com.google.gson.Gson;
import seedu.duke.apps.moduleloader.exceptions.ModuleLoaderException;
import seedu.duke.global.LoggingTool;
import seedu.duke.global.objects.FullModule;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author Khenus
/**
 * Class representing function to load all modules offered by NUS.
 */
public class ModuleLoader {
    private static final int TOTAL_NUMBER_OF_MODULES = 12436;
    public static final String NEW_LINE = "\n";
    public static final String MISSING_MODULE_DATA = "Data for Modules not found!";
    public static final String TERMINATION = "Terminating program...";
    public static final String CORRUPTED_MODULE_DATA = "Data for Modules corrupted!";
    public static final String LOG_FILE_NAME = "logs/ModuleLoader.log";
    public static final String LOGGER_NAME = "ModuleLoader";

    private Map<String, Integer> moduleMap;
    private FullModule[] moduleFullDetails;
    private static Logger logger;
    private static FileHandler fh;

    /**
     * Default constructor for to load all modules from JSON file.
     */
    public ModuleLoader() throws ModuleLoaderException {
        try {
            File file = new File("logs");
            file.mkdirs();
            fh = new FileHandler(LOG_FILE_NAME);
            logger = new LoggingTool(LOGGER_NAME, fh).initialize();

            moduleMap = new HashMap<>();
            Gson gson = new Gson();

            InputStream in = ModuleLoader.class.getResourceAsStream("/ModuleData.json");
            Reader jsonReader = new InputStreamReader(in);
            moduleFullDetails = gson.fromJson(jsonReader, FullModule[].class);

            for (int index = 0; index < moduleFullDetails.length; index++) {
                moduleMap.put(moduleFullDetails[index].getModuleCode(), index);
            }

            if (moduleFullDetails.length != TOTAL_NUMBER_OF_MODULES) {
                throw new ModuleLoaderException(CORRUPTED_MODULE_DATA + NEW_LINE + TERMINATION);
            }

            logger.log(Level.INFO, "All Module successfully loaded");
            fh.close();

        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "Error while loading all modules: " + e.getMessage());
            fh.close();
            throw new ModuleLoaderException(MISSING_MODULE_DATA + NEW_LINE + TERMINATION);
        } catch (IOException e) {
            throw new ModuleLoaderException("Logger failed to initialize" + NEW_LINE + TERMINATION);
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
