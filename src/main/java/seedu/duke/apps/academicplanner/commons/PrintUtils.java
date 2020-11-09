package seedu.duke.apps.academicplanner.commons;

import seedu.duke.global.objects.PartialModule;
import seedu.duke.global.objects.FullModule;
import java.util.ArrayList;

//@@author jerroldlam
/**
 * Class representing the common print methods.
 */
public class PrintUtils {
    private static final String INDENT = "     ";
    private static final String ERROR_NO_MODULES = "You have no modules added in the semester you requested!";

    public PrintUtils() {
        //Constructor is intentionally left blank
    }

    /**
     * Prints out the sorted list of modules.
     *
     * @param sortedList list to be printed
     * @param selectedSemester semester chosen.
     */
    public void printBySemester(ArrayList<PartialModule> sortedList, int selectedSemester) {
        if (sortedList.size() < 1) {
            System.out.println(ERROR_NO_MODULES);
            return;
        }

        System.out.println(INDENT + "SEMESTER " + selectedSemester);
        for (PartialModule item : sortedList) {
            printCalendarLine(item);
        }
    }

    //@@author vanessa-kang
    /**
     * Prints a single entry of item.
     *
     * @param item module details to be printed
     */
    private void printCalendarLine(PartialModule item) {
        int spacing = 8 + (8 - item.getModuleCode().length());
        System.out.println(item.getModuleCode()
                + printSpace(spacing)
                + item.getGrade());
    }

    /**
     * Prints out contents of the Array list of partial modules in an intuitive format.
     *
     * @param sortedBySem arraylist of modules
     */
    public void printFullCalendar(ArrayList<PartialModule> sortedBySem) {
        int newSem;
        int currentSem = 0;
        for (PartialModule item : sortedBySem) {
            newSem = item.getSemesterIndex();
            if (newSem != currentSem) {
                currentSem = newSem;
                System.out.println(INDENT + "SEMESTER " + currentSem);
            }
            printCalendarLine(item);
        }
    }

    /**
     * Prints out detailed information about a module.
     *
     * @param module FullModule object, contains all information about a module.
     */
    public void printModuleDetails(FullModule module) {

        String semOfferedStr = module.getSemester().toString();

        System.out.println(module.getModuleCode() + " " + module.getTitle());
        System.out.println("\t" + "Department: " + module.getDepartment());
        System.out.println("\t" + "Faculty: " + module.getFaculty());
        System.out.println("\t" + "Credits: " + module.getModuleCredit() + "MCs");
        System.out.println("\t" + "Semesters offered: " + semOfferedStr.substring(1,semOfferedStr.length() - 1));
        System.out.println("\t" + "Prerequisites: "
                                + module.getPrerequisite().replaceAll("\n","\n\t" + printSpace(15)));
        System.out.println("\t" + "Corequisites: "
                                + module.getCorequisite().replaceAll("\n","\n\t" + printSpace(13)));
        System.out.println("\t" + "Preclusions: "
                                + module.getPreclusion().replaceAll("\n","\n\t"  + printSpace(13)));
    }

    /**
     * Prints out list of module codes that matches the user's search key.
     *
     * @param matchList arrayList of module codes that matches the search key.
     * @param maxResults maximum number of results to be printed.
     */

    public void printMatchModules(ArrayList<String> matchList, int maxResults) {

        String grammar = matchList.size() == 1 ? "module." : "modules.";

        System.out.println("Note: Only up to the first " + maxResults + " results are displayed.");
        System.out.println("Found " + matchList.size() + " matching " + grammar);
        for (String matches: matchList) {
            System.out.println(matches);
        }
    }

    /**
     * Prints num spaces for indentation.
     * @param num int of spaces to print
     * @return string of spaces
     */
    private String printSpace(int num) {
        String space = "";
        for (int i = 0; i < num; i++) {
            space += " ";
        }
        return space;
    }
}
