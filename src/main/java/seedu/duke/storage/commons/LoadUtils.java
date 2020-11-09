package seedu.duke.storage.commons;

import seedu.duke.apps.academicplanner.commons.AddUtils;
import seedu.duke.apps.academicplanner.commons.ModuleValidator;
import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.global.objects.Person;
import seedu.duke.storage.exceptions.CorruptedSaveFileException;
import seedu.duke.storage.exceptions.LoadFileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static seedu.duke.storage.commons.FieldValidator.printLoadDetails;

//@@author harryleecp
public class LoadUtils {
    private static final String NEW_LINE = "\n";
    private static final String TAB = "  ";

    private static final int SEMESTER_VALUE_POSITION = 0;
    private static final int MODULE_CODE_POSITION = 1;
    private static final int MODULE_GRADE_POSITION = 2;
    private static final int MODULE_CREDIT_POSITION = 3;

    private static final int MODULE_LOADED = 0;
    private static final int MODULE_LOADING_FAILED = 1;
    private static final int INVALID_SEMESTER = 2;
    private static final int INVALID_MODULE_CODE = 3;
    private static final int INVALID_GRADE = 4;
    private static final int INVALID_MC = 5;

    private static final int FROM_LOAD = 1;

    private ModuleLoader allModules;
    private AddUtils addUtils;
    private ModuleValidator moduleValidator;
    private Person currentPerson;
    private String fileName;

    /**
     * Default constructor for handling loading.
     *
     * @param currentPerson a Person object storing information for current person
     * @param fileName a String for name of save file
     * @param allModules A ModuleLoader containing all loaded modules
     */
    public LoadUtils(Person currentPerson, String fileName, ModuleLoader allModules) {
        this.currentPerson = currentPerson;
        this.fileName = fileName;
        this.allModules = allModules;
        this.moduleValidator = new ModuleValidator(allModules, currentPerson);
    }

    /**
     * Function to load save file.
     *
     * @throws LoadFileException Exception when the save file is corrupted or missing
     */
    public void load() throws LoadFileException {
        int[] details = new int[6];
        boolean isAllEntryValid = true;
        Scanner in;

        try {
            File f = new File(fileName);
            in = new Scanner(f);
            addUtils = new AddUtils(allModules, currentPerson);

            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] lineItems = line.split("\\|");

                for (int i = 0; i < lineItems.length; i++) {
                    lineItems[i] = lineItems[i].trim().toUpperCase();
                }

                boolean isEntryValid
                        = new FieldValidator(details, lineItems, moduleValidator, currentPerson).validate();

                if (!isEntryValid) {
                    isAllEntryValid = false;
                }

                if (isEntryValid) {
                    int currentSem = parseInt(lineItems[SEMESTER_VALUE_POSITION]);
                    String currentModuleCode = lineItems[MODULE_CODE_POSITION];
                    String currentModuleGrade = lineItems[MODULE_GRADE_POSITION];
                    int currentModuleCredit = parseInt(lineItems[MODULE_CREDIT_POSITION]);
                    addUtils.addModuleToUser(currentModuleCode, currentSem,
                            currentModuleGrade, currentModuleCredit, FROM_LOAD);
                }
            }
            in.close();
            printLoadDetails(isAllEntryValid, details[MODULE_LOADED]);
        } catch (FileNotFoundException e) {
            throw new LoadFileException("Save file not found!\nIt will be automatically created.");
        } catch (CorruptedSaveFileException e) {
            throw new LoadFileException("Save file corrupted! "
                    + "Details:" + NEW_LINE
                    + TAB + "Module loaded: " + details[MODULE_LOADED] + NEW_LINE
                    + TAB + "Module failed loading: " + details[MODULE_LOADING_FAILED] + NEW_LINE
                    + TAB + TAB + "- Invalid Semester: " + details[INVALID_SEMESTER] + NEW_LINE
                    + TAB + TAB + "- Invalid Module Code: " + details[INVALID_MODULE_CODE] + NEW_LINE
                    + TAB + TAB + "- Invalid Grade: " + details[INVALID_GRADE] + NEW_LINE
                    + TAB + TAB + "- Invalid Module Credit: " + details[INVALID_MC] + NEW_LINE
                    + NEW_LINE);
        }
    }
}
