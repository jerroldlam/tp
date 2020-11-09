package seedu.duke.apps.academicplanner.commons;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static seedu.duke.apps.academicplanner.commons.SharedUtils.getAllOccurrencesOfModule;
import seedu.duke.global.objects.PartialModule;
import seedu.duke.global.objects.Person;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author Khenus
class SharedUtilsTest {
    @Test
    void getAllOccurrencesOfModule_noOccurrence_success() {
        Person testPerson = new Person("test");
        String testCode = "CS1010";

        ArrayList<PartialModule> allOccurrence = getAllOccurrencesOfModule(testPerson, testCode);

        assertEquals(allOccurrence.size(), 0);
    }
}