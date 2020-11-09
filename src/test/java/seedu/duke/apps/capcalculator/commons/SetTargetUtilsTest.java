package seedu.duke.apps.capcalculator.commons;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import seedu.duke.apps.capcalculator.exceptions.InvalidCapException;
import seedu.duke.apps.capcalculator.exceptions.InvalidCreditException;
import seedu.duke.global.objects.Person;

//@@author Khenus
class SetTargetUtilsTest {
    @Test
    public void getTargetCap_getCapFromUser_success() throws InvalidCapException {
        String input = "4.9";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scan = new Scanner(in);
        Person person = new Person("testing");

        SetTargetUtils utils = new SetTargetUtils(person, scan);

        assertEquals(4.9, utils.getTargetCap());
    }

    @Test
    public void getTargetCap_getCapFromUserLargerThan5_failure() {
        String input = "5.1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scan = new Scanner(in);
        Person person = new Person("testing");

        SetTargetUtils utils = new SetTargetUtils(person, scan);

        Exception exception = assertThrows(InvalidCapException.class, () -> {
            utils.getTargetCap();
        });

        assertTrue(exception.getMessage().contains("Your target CAP cannot be greater than the maximum CAP of 5!"));
    }

    @Test
    public void getTargetCap_getCapFromUserLesserThan0_failure() {
        String input = "-0.1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scan = new Scanner(in);
        Person person = new Person("testing");

        SetTargetUtils utils = new SetTargetUtils(person, scan);

        Exception exception = assertThrows(InvalidCapException.class, () -> {
            utils.getTargetCap();
        });

        assertTrue(exception.getMessage().contains("Your target CAP cannot be lower than the minimum CAP of 0!"));
    }

    @Test
    public void getTargetGradedMC_getTargetFromUser_success() throws InvalidCreditException {
        String input = "180";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scan = new Scanner(in);
        Person person = new Person("testing");

        SetTargetUtils utils = new SetTargetUtils(person, scan);

        assertEquals(180, utils.getTargetGradedMC());
    }

    @Test
    public void getTargetGradedMC_getTargetFromUserDouble_failure() {
        String input = "-1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scan = new Scanner(in);
        Person person = new Person("testing");

        SetTargetUtils utils = new SetTargetUtils(person, scan);

        Exception exception = assertThrows(InvalidCreditException.class, () -> {
            utils.getTargetGradedMC();
        });

        assertTrue(exception.getMessage().contains("Your target MC should be greater than 0!"));
    }
}