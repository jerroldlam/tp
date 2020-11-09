package seedu.duke.storage;

import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.global.objects.Person;
import seedu.duke.storage.commons.LoadUtils;
import seedu.duke.storage.commons.SaveUtils;
import seedu.duke.storage.exceptions.LoadFileException;
import seedu.duke.storage.exceptions.SaveFileException;

//@@author harryleecp
public class Storage {
    private static final String FILE_NAME = "PlanNUS.txt";
    private ModuleLoader allModules;

    public Storage(ModuleLoader allModules) {
        this.allModules = allModules;
    }

    public void loader(Person currentPerson) {
        LoadUtils loadHandler = new LoadUtils(currentPerson, FILE_NAME, allModules);

        try {
            loadHandler.load();
        } catch (LoadFileException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saver(Person currentPerson) {
        SaveUtils saveHandler = new SaveUtils(currentPerson, FILE_NAME);

        try {
            saveHandler.save();
        } catch (SaveFileException e) {
            System.out.println(e.getMessage());
        }
    }
}
