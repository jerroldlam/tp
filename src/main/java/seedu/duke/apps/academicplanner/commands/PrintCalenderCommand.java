package seedu.duke.apps.academicplanner.commands;

import seedu.duke.apps.academicplanner.commons.AcademicCalendarSorter;
import seedu.duke.apps.academicplanner.commons.ModuleValidator;
import seedu.duke.apps.academicplanner.commons.PrintUtils;
import seedu.duke.apps.academicplanner.exceptions.AcademicException;
import seedu.duke.global.Command;
import seedu.duke.global.objects.PartialModule;
import seedu.duke.global.objects.Person;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

//@@author vanessa-kang
/**
 * Class representing a print module command from the academic planner.
 */
public class PrintCalenderCommand extends Command {
    private static final String FULL_PRINT_COMMAND = "Full";
    private static final String EMPTY_MODULE_LIST = "Your academic calendar is currently empty!";
    private static final String ERROR_INVALID_COMMAND = "Invalid command entered";
    private static final String ERROR_INVALID_SEMESTER = "Invalid semester entered.";
    private static final String PROMPT_USER = "Printing command received.\n"
            + "\tTo print the whole Academic Calendar, type <Full>\n"
            + "\tTo print the desired semester only, type a number from 1 to 10, inclusive.";

    private PrintUtils printUtils = new PrintUtils();
    private final ArrayList<PartialModule> modulesList;
    private Scanner in;
    private AcademicCalendarSorter sorter;

    /**
     * Default constructor for print calendar command.
     * @param currentPerson current user
     * @param in PlanNUS ui
     */
    public PrintCalenderCommand(Person currentPerson, Scanner in) {
        this.modulesList = currentPerson.getModulesList();
        sorter = new AcademicCalendarSorter(modulesList);
        this.in = in;
    }

    /**
     * Execution of print command where user will be prompted of choice for full calendar or semester.
     */
    @Override
    public void execute() throws AcademicException {
        if (modulesList.size() > 0) {

            System.out.println(PROMPT_USER);
            String userInput = in.nextLine().trim();

            if (userInput.equalsIgnoreCase(FULL_PRINT_COMMAND)) {
                printFullCalendar();
            } else {
                try {
                    int selectedSemester = Integer.parseInt(userInput);
                    if (ModuleValidator.isValidSemester(selectedSemester)) {
                        sortAndPrint(selectedSemester);
                    } else {
                        throw new AcademicException(ERROR_INVALID_SEMESTER);
                    }
                } catch (NumberFormatException e) {
                    throw new AcademicException(ERROR_INVALID_COMMAND);
                }
            }
        } else {
            System.out.println(EMPTY_MODULE_LIST);
        }
    }

    //@@author jerroldlam
    /**
     * Prints full academic calendar.
     */
    private void printFullCalendar() {
        ArrayList<PartialModule> sortedBySem = new ArrayList<>(modulesList);
        sortedBySem.sort(Comparator.comparing(PartialModule::getSemesterIndex));
        printUtils.printFullCalendar(sortedBySem);
    }

    /**
     * Sorts and prints the semester's module.
     *
     * @param selectedSemester semester to print
     */
    private void sortAndPrint(int selectedSemester) {
        ArrayList<PartialModule> sortedList = sorter.sortBySemester(selectedSemester);
        printUtils.printBySemester(sortedList, selectedSemester);
    }
}
