package seedu.duke.apps.academicplanner.commons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.apps.moduleloader.exceptions.ModuleLoaderException;
import seedu.duke.global.objects.PartialModule;
import seedu.duke.global.objects.Person;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author jerroldlam
class AcademicCalendarSorterTest {
    Person currentPerson;
    AddUtils addUtils;
    ModuleLoader allModules;
    AcademicCalendarSorter sorter;

    @BeforeEach
    void setup() {
        try {
            currentPerson = new Person("Bobby");
            allModules = new ModuleLoader();
            addUtils = new AddUtils(allModules,currentPerson);
        } catch (ModuleLoaderException e) {
            e.printStackTrace();
        }
    }

    @Test
    void processCalendar_mixedList_success() {
        addUtils.addModuleToUser("CS1010",1,"A-",4);
        addUtils.addModuleToUser("CS1231",1,"B",4);
        addUtils.addModuleToUser("CG1111",1,"B+",6);
        addUtils.addModuleToUser("MA1511",1,"S",4);
        addUtils.addModuleToUser("MA1512",1,"S",4);

        addUtils.addModuleToUser("CS2040C",2,"A-",4);
        addUtils.addModuleToUser("CG1112",2,"B",4);
        addUtils.addModuleToUser("MA1508E",2,"S",4);

        sorter = new AcademicCalendarSorter(currentPerson.getModulesList());

        ArrayList<PartialModule> testList = sorter.processCalendar(1);
        assertEquals(testList.size(),5);
        testList = sorter.processCalendar(2);
        assertEquals(testList.size(),3);
    }

}
