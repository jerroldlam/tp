package seedu.duke.apps.academicplanner.commands;

import seedu.duke.globalcommons.Command;
import seedu.duke.objects.PartialModule;
import seedu.duke.objects.Person;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Class representing a print module command from the academic planner.
 */
public class PrintCalenderCommand extends Command {

    private static final String EMPTY_MODULE_LIST = "Your academic calendar is currently empty!";
    private static final String INDENT = "     ";

    private final ArrayList<PartialModule> modulesList;

    public PrintCalenderCommand(Person currentPerson) {
        this.modulesList = currentPerson.getModulesList();
    }

    @Override
    public void execute() {

        if (modulesList.size() != 0) {
            ArrayList<PartialModule> sortedBySem = new ArrayList<>(modulesList);
            sortedBySem.sort(Comparator.comparing(PartialModule::getSemesterIndex));

            int currSem = 0;
            int newSem;

            for (PartialModule item : sortedBySem) {
                newSem = item.getSemesterIndex();
                if (newSem != (currSem)) {
                    currSem = newSem;
                    System.out.println(INDENT + "SEMESTER " + currSem);
                }
                int spacing = 8 + (8 - item.getModuleCode().length());
                System.out.println(item.getModuleCode()
                        + printSpace(spacing)
                        + item.getGrade());
            }
        } else {
            System.out.println(EMPTY_MODULE_LIST);
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
