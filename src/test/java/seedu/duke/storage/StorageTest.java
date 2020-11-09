package seedu.duke.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.apps.moduleloader.exceptions.ModuleLoaderException;
import seedu.duke.global.objects.Person;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author Khenus
class StorageTest {
    private Storage storage;
    private ByteArrayOutputStream outContent;
    private Person person;

    @BeforeEach
    public void setup() throws ModuleLoaderException {
        ModuleLoader allModules = new ModuleLoader();
        storage = new Storage(allModules);
        person = new Person("test");
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void loader_loadingFileMissing_success() {
        String notFoundCheckString = "Save file not found!\nIt will be automatically created.";
        String corruptedCheckString = "Save file corrupted!";
        String successfulCheckString = "module loaded.";
        String multipleSuccessfulCheckString = "modules loaded.";
        String emptyFileCheckString = "Save file is empty. No module loaded.";
        storage.loader(person);

        boolean fileNotFound = outContent.toString().contains(notFoundCheckString);
        boolean corrupted = outContent.toString().contains(corruptedCheckString);
        boolean successful = outContent.toString().contains(successfulCheckString);
        boolean multiple = outContent.toString().contains(multipleSuccessfulCheckString);
        boolean emptyFile = outContent.toString().contains(emptyFileCheckString);

        assertTrue(fileNotFound || corrupted || successful || multiple || emptyFile);
    }

    @Test
    public void saver_savingDataToFile_success() {
        String checkString = "Data successfully saved!";
        storage.saver(person);

        assertTrue(outContent.toString().contains(checkString));
    }
}