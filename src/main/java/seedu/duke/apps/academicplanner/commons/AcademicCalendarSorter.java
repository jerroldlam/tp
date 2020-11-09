package seedu.duke.apps.academicplanner.commons;

import seedu.duke.global.objects.PartialModule;
import java.util.ArrayList;

//@@author jerroldlam
/**
 * Class representing sorter for academic calendar.
 */
public class AcademicCalendarSorter {
    private static final String ERROR_EMPTY_CALENDAR = "Your academic calendar is empty.";
    private ArrayList<PartialModule> userModuleList;

    public AcademicCalendarSorter(ArrayList<PartialModule> moduleList) {
        userModuleList = moduleList;
    }

    /**
     * Sorts module list by semester index and return module list of chosen semester.
     *
     * @param semesterIndex semesterIndex to search for
     * @return module list of the chosen semester
     */
    public ArrayList<PartialModule> sortBySemester(int semesterIndex) {

        if (userModuleList.size() < 1) {
            System.out.println(ERROR_EMPTY_CALENDAR);
        }
        ArrayList<PartialModule> sortedList = processCalendar(semesterIndex);
        return sortedList;
    }

    /**
     * Collects modules which are from a given semester into an arraylist and returns it.
     *
     * @param semesterIndex semester to sort by
     * @return sortedList
     */
    public ArrayList<PartialModule> processCalendar(int semesterIndex) {
        ArrayList<PartialModule> sortedList = new ArrayList<>();
        for (PartialModule m : userModuleList) {
            if (m.getSemesterIndex() == semesterIndex) {
                sortedList.add(m);
            }
        }
        return sortedList;
    }
}
