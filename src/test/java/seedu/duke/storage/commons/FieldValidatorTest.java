package seedu.duke.storage.commons;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.apps.academicplanner.commons.ModuleValidator;
import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.apps.moduleloader.exceptions.ModuleLoaderException;
import seedu.duke.global.objects.Person;
import seedu.duke.storage.exceptions.CorruptedSaveFileException;

//@@author Khenus
class FieldValidatorTest {
    private Person currentPerson;

    @BeforeEach
    void setup() {
        try {
            currentPerson = new Person("Bob");
        } catch (Exception e) {
            fail();
        }
    }


    @Test
    void validate_allFieldsValid_success() throws ModuleLoaderException {
        int[] details = {0, 0, 0, 0, 0, 0};
        String[] fieldItems = {"1", "CG1111", "A", "6"};
        ModuleValidator moduleValidator = new ModuleValidator(new ModuleLoader(), currentPerson);
        FieldValidator validator = new FieldValidator(details, fieldItems, moduleValidator, currentPerson);

        int[] detailsCheck = {1, 0, 0, 0, 0, 0};

        boolean isEntryValid = validator.validate();
        assertEquals(true, isEntryValid);
        assertArrayEquals(detailsCheck, details);
    }

    @Test
    void validate_invalidSemester_success() throws ModuleLoaderException {
        int[] details = {0, 0, 0, 0, 0, 0};
        String[] fieldItems = {"-1", "CG1111", "A", "6"};
        ModuleValidator moduleValidator = new ModuleValidator(new ModuleLoader(), currentPerson);
        FieldValidator validator = new FieldValidator(details, fieldItems, moduleValidator, currentPerson);

        int[] detailsCheck = {0, 1, 1, 0, 0, 0};

        boolean isEntryValid = validator.validate();
        assertEquals(false, isEntryValid);
        assertArrayEquals(detailsCheck, details);
    }

    @Test
    void validate_invalidModule_success() throws ModuleLoaderException {
        int[] details = {0, 0, 0, 0, 0, 0};
        String[] fieldItems = {"1", "CG11111", "A", "6"};
        ModuleValidator moduleValidator = new ModuleValidator(new ModuleLoader(), currentPerson);
        FieldValidator validator = new FieldValidator(details, fieldItems, moduleValidator, currentPerson);

        int[] detailsCheck = {0, 1, 0, 1, 0, 0};

        boolean isEntryValid = validator.validate();
        assertEquals(false, isEntryValid);
        assertArrayEquals(detailsCheck, details);
    }

    @Test
    void validate_invalidGrade_success() throws ModuleLoaderException {
        int[] details = {0, 0, 0, 0, 0, 0};
        String[] fieldItems = {"1", "CG1111", "Z", "6"};
        ModuleValidator moduleValidator = new ModuleValidator(new ModuleLoader(), currentPerson);
        FieldValidator validator = new FieldValidator(details, fieldItems, moduleValidator, currentPerson);

        int[] detailsCheck = {0, 1, 0, 0, 1, 0};

        boolean isEntryValid = validator.validate();
        assertEquals(false, isEntryValid);
        assertArrayEquals(detailsCheck, details);
    }

    @Test
    void validate_invalidModuleCredit_success() throws ModuleLoaderException {
        int[] details = {0, 0, 0, 0, 0, 0};
        String[] fieldItems = {"1", "CG1111", "A", "-50"};
        ModuleValidator moduleValidator = new ModuleValidator(new ModuleLoader(), currentPerson);
        FieldValidator validator = new FieldValidator(details, fieldItems, moduleValidator, currentPerson);

        int[] detailsCheck = {0, 1, 0, 0, 0, 1};

        boolean isEntryValid = validator.validate();
        assertEquals(false, isEntryValid);
        assertArrayEquals(detailsCheck, details);
    }

    @Test
    void validate_invalidFieldsPriority_success() throws ModuleLoaderException {
        int[] details = {0, 0, 0, 0, 0, 0};
        String[] fieldItems = {"-1", "CG11111", "Z", "-50"};
        ModuleValidator moduleValidator = new ModuleValidator(new ModuleLoader(), currentPerson);
        FieldValidator validator = new FieldValidator(details, fieldItems, moduleValidator, currentPerson);

        int[] detailsCheck = {0, 1, 1, 0, 0, 0};

        boolean isEntryValid = validator.validate();
        assertEquals(false, isEntryValid);
        assertArrayEquals(detailsCheck, details);
    }

    @Test
    public void printLoadDetails_corruptedSaveFile_success() {
        assertThrows(CorruptedSaveFileException.class, () -> {
            FieldValidator.printLoadDetails(false, 0);
        });
    }

    @Test
    public void printLoadDetails_allEntryAreValid_zeroEntry_success() throws CorruptedSaveFileException {
        String checkString = "Save file is empty. No module loaded.";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        FieldValidator.printLoadDetails(true, 0);

        assertTrue(outContent.toString().contains(checkString));
    }

    @Test
    public void printLoadDetails_allEntryAreValid_oneEntry_success() throws CorruptedSaveFileException {
        String checkString = "1 module loaded.";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        FieldValidator.printLoadDetails(true, 1);

        assertTrue(outContent.toString().contains(checkString));
    }

    @Test
    public void printLoadDetails_allEntryAreValid_multipleEntry_success() throws CorruptedSaveFileException {
        String checkString = "10 modules loaded.";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        FieldValidator.printLoadDetails(true, 10);

        assertTrue(outContent.toString().contains(checkString));
    }
}