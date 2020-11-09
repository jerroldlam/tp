package seedu.duke.apps.capcalculator.commands;

import seedu.duke.global.Command;

//@author Ju Zihao
/**
 * Class representing a print help command from the academic planner.
 */
public class PrintHelpCommand extends Command {

    private static final String COMMANDS_LIST = "Available commands are:\n"
            + "\tcurrent\n"
            + "\tset target\n"
            + "\tset su\n"
            + "\tacadplan\n"
            + "\thelp\n"
            + "\texit";

    public PrintHelpCommand() {
        //Constructor is intentionally left blank
    }

    /**
     * Prints commands list for academic planner.
     */
    public void execute() {
        System.out.println(COMMANDS_LIST);
    }
}
