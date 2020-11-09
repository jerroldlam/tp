package seedu.duke.apps.capcalculator.commands;

import seedu.duke.apps.capcalculator.commons.SetSuUtils;
import seedu.duke.apps.capcalculator.exceptions.CapCalculatorException;
import seedu.duke.global.Command;
import seedu.duke.global.objects.PartialModule;
import seedu.duke.global.objects.Person;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

//@@author JuZihao
/**
 * Class representing the set S/U by modules command for CAP Calculator.
 */
public class SetSuByModuleCommand extends Command {

    private SetSuUtils setSuUtils;
    private ArrayList<PartialModule> suList;

    public SetSuByModuleCommand(Person currentPerson, Ui ui) {
        this.setSuUtils = new SetSuUtils(currentPerson, ui);
    }

    /**
     * Function to calculate the best CAP after S/Uing some specific modules.
     */
    @Override
    public void execute() {
        try {
            int numberOfModulesToSu = setSuUtils.promptUserForNumberOfModules();
            suList = setSuUtils.getSuListByModule(numberOfModulesToSu);
            setSuUtils.showSuListToUser(suList);
            setSuUtils.showResultsToUser(suList);
        } catch (CapCalculatorException e) {
            System.out.println(e.getMessage());
        }
    }
}
