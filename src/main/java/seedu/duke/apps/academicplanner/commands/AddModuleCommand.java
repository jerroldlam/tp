package seedu.duke.apps.academicplanner.commands;

import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.apps.academicplanner.commons.AddUtils;
import seedu.duke.apps.academicplanner.commons.ModuleValidator;
import seedu.duke.apps.academicplanner.exceptions.AcademicException;
import seedu.duke.globalcommons.Command;
import seedu.duke.objects.Person;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class representing an add module command from the academic planner.
 */
public class AddModuleCommand extends Command {
    private static final String ERROR_INVALID_COMMAND = "INVALID COMMAND";
    private static final String ERROR_INVALID_SEMESTER_INDEX = "INVALID SEMESTER INDEX";
    private static final String ERROR_INVALID_GRADE = "INVALID GRADE VALUE";
    private static final String ERROR_NOT_OFFERED = " IS NOT OFFERED BY NUS";
    private static final String ERROR_DUPLICATE_MOD = "You already have this mod on your calendar!";
    private static final String VALID_GRADES = "Valid grades are:\n"
            + "\tLetter Grades: A+, A, A-, B+, B, B-, C+, C, D+, D, F\n"
            + "\tSpecial Grades: CS, CU, S, U, W, IC, IP, AUD, WU, EXE\n"
            + "\tIf you have yet to have a grade for the module: NT";
    private static final String VALID_SEMESTERS = "Valid semesters are integers from 1 to 10, inclusive";

    private static final Logger logger = Logger.getLogger("AddModuleCommand.java");
    private AddUtils addUtils;
    private ModuleValidator moduleValidator;
    private Scanner in;
    private String moduleCode;

    public AddModuleCommand(ModuleLoader allModules, Person currentPerson, Scanner in, String moduleCode) {
        this.addUtils = new AddUtils(allModules, currentPerson);
        this.moduleValidator = new ModuleValidator(allModules, currentPerson);
        this.in = in;
        this.moduleCode = moduleCode;
    }

    /**
     * Adds a module to the user's academic calendar if it exists in FullModule,
     * else does not add module into user's academic calendar.
     * Validates user's input semester and grade.
     * If either is invalid, does not add module into user's academic calendar.
     */
    @Override
    public void execute() throws AcademicException {
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO,"Executing add command.");
        if (!moduleValidator.isModOfferedByNus(moduleCode)) {
            logger.log(Level.WARNING,"Module entered not offered by NUS.");
            throw new AcademicException(moduleCode + ERROR_NOT_OFFERED);
        }

        if (moduleValidator.isModTakenByUser(moduleCode)) {
            logger.log(Level.WARNING,"Module entered is duplicated.");
            throw new AcademicException(ERROR_DUPLICATE_MOD);
        }

        promptUserToEnterSemester();
        String userInput = in.nextLine().trim();

        int semesterValue;
        try {
            semesterValue = Integer.parseInt(userInput);
        } catch (Exception e) {
            logger.log(Level.WARNING,"Semester entered is not an integer.");
            throw new AcademicException(ERROR_INVALID_COMMAND);
        }

        if (!moduleValidator.isValidSemester(semesterValue)) {
            logger.log(Level.WARNING,"Semester entered is invalid.");
            throw new AcademicException(ERROR_INVALID_SEMESTER_INDEX);
        }

        promptUserToEnterGrade();
        String gradeValue = in.nextLine().trim().toUpperCase();

        if (!moduleValidator.isValidGrade(gradeValue)) {
            logger.log(Level.WARNING,"Grade entered is invalid.");
            throw new AcademicException(ERROR_INVALID_GRADE);
        }
        int moduleCredit = addUtils.getModuleCreditForModule(moduleCode);
        assert semesterValue > 0;
        assert moduleCredit >= 0;
        addUtils.addModuleToUser(moduleCode, semesterValue, gradeValue, moduleCredit);
        logger.log(Level.INFO,"Finish executing add command.");
    }

    /**
     * Prints prompt and help messages for user to enter module's grade.
     */
    private void promptUserToEnterGrade() {
        System.out.println("Grade received for " + moduleCode.toUpperCase() + "?");
        System.out.println(VALID_GRADES);
    }

    /**
     * Prints prompt and help messages for user to enter module's semester taken.
     */
    private void promptUserToEnterSemester() {
        System.out.println("Semester you plan to take " + moduleCode.toUpperCase() + "?");
        System.out.println(VALID_SEMESTERS);
    }
}
