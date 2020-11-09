package seedu.duke.storage.commons;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.global.objects.Person;
import seedu.duke.storage.exceptions.SaveFileException;

//@@author Khenus
class SaveUtilsTest {
    private Person person;
    private String wrongFileName = "";

    @BeforeEach
    void setup() {
        person = new Person("test");
    }

    @Test
    void save_saveFileExceptionTesting_success() {
        SaveUtils saver = new SaveUtils(person, wrongFileName);
        Exception exception = assertThrows(SaveFileException.class, () -> {
            saver.save();
        });

        assertTrue(exception.getMessage().contains("Error accessing save file!"));
    }
}
