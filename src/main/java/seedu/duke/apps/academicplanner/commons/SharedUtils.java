package seedu.duke.apps.academicplanner.commons;

import seedu.duke.apps.academicplanner.exceptions.AcademicException;
import seedu.duke.global.objects.PartialModule;
import seedu.duke.global.objects.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

//@@author Khenus

/**
 * A class that handles verification for the addition, edition and removal of duplicated entries.
 */
public class SharedUtils {
    private static final int FROM_EDIT = 2;
    private static final int FROM_REMOVE = 3;

    /**
     * Default constructor for SharedUtils.
     */
    public SharedUtils() {
        //This constructor is intentionally left blank
    }

    /**
     * Return all occurrences of a certain modules code.
     *
     * @param currentPerson Person object containing all user information
     * @param moduleCode String containing the current module code to be checked
     *
     * @return ArrayList of Partial Module containing information of all occurrences of the module code
     */
    public static ArrayList<PartialModule> getAllOccurrencesOfModule(Person currentPerson, String moduleCode) {
        ArrayList<PartialModule> modulesAddedList = currentPerson.getModulesList();
        ArrayList<Integer> occurrenceList = currentPerson.getModulesAddedMap().get(moduleCode);

        ArrayList<PartialModule> allOccurrencesOfModule = new ArrayList<>();

        if (occurrenceList == null) {
            return allOccurrencesOfModule;
        }

        for (int i = 0; i < occurrenceList.size(); i++) {
            allOccurrencesOfModule.add(modulesAddedList.get(occurrenceList.get(i)));
        }

        return allOccurrencesOfModule;
    }

    /**
     * Prints all occurrences of a specific module without index.
     *
     * @param allOccurrence ArrayList containing information of all occurrence of a specific module
     */
    public static void printAllOccurrencesOfModule(ArrayList<PartialModule> allOccurrence) {
        System.out.println("\nPrevious attempt(s) for " + allOccurrence.get(0).getModuleCode() + ":");
        System.out.println("\t Semester    Grade");

        for (int i = 0; i < allOccurrence.size(); i++) {
            int currentSemester = allOccurrence.get(i).getSemesterIndex();
            String currentGrade = allOccurrence.get(i).getGrade();
            String toPrint = String.format("\t    %02d        %s  ", currentSemester, currentGrade);

            System.out.println(toPrint);
        }
    }

    /**
     * Prints all occurrences of a specific module with index.
     *
     * @param currentPerson Person object containing all user information
     * @param moduleCode String containing the current module code to be checked
     */
    public static void printAllOccurrencesOfModuleToEdit(Person currentPerson, String moduleCode) {
        ArrayList<PartialModule> allOccurrence = getAllOccurrencesOfModule(currentPerson, moduleCode);

        System.out.println("\nPrevious occurrence(s) for " + allOccurrence.get(0).getModuleCode() + ":");
        System.out.println("\tIndex   Semester    Grade");

        for (int i = 0; i < allOccurrence.size(); i++) {
            int currentSemester = allOccurrence.get(i).getSemesterIndex();
            String currentGrade = allOccurrence.get(i).getGrade();
            String toPrint = String.format("\t  %d.      %02d        %s  ",  i + 1, currentSemester, currentGrade);

            System.out.println(toPrint);
        }
    }

    /**
     * Returns the index of ModuleMap to be edited.
     *
     * @param in Unified scanner class from UI
     * @param moduleCode Module code to be edited
     * @param currentPerson Information regarding current user
     * @param from Location in which this function is called
     *
     * @return int containing the index of ModuleMap to be edited
     *
     * @throws AcademicException Throws exception when the entered value is invalid
     */
    public static int getEntryToBeEdited(Scanner in, String moduleCode, Person currentPerson, int from)
            throws AcademicException {
        ArrayList<Integer> moduleIndexList = currentPerson.getModulesAddedMap().get(moduleCode);

        int indexToUpdate = 0;

        if (moduleIndexList.size() > 1) {
            if (from == FROM_EDIT) {
                System.out.print("Please enter the index of entry to be edited:");
            } else if (from == FROM_REMOVE) {
                System.out.print("Please enter the index of entry to be removed:");
            }

            printAllOccurrencesOfModuleToEdit(currentPerson, moduleCode);

            String entryToEdit = in.nextLine().trim();

            try {
                indexToUpdate = parseInt(entryToEdit) - 1;
            } catch (Exception e) {
                throw new AcademicException("The value you entered is not an integer!");
            }

            if (!(indexToUpdate >= 0 && indexToUpdate < moduleIndexList.size())) {
                throw new AcademicException("The index you entered does not exist within the list!");
            }
        }

        return indexToUpdate;
    }

    /**
     * Checks if the semester to be edited already exist.
     *
     * @param semesterValue The semester value the user wish to change
     * @param currentPerson Information of the current user
     * @param moduleCode Module code to be edited
     * @throws AcademicException Throws exception if the semester to be change already exists
     */
    public static void verifyRepeatedSemester(int semesterValue, Person currentPerson,
        String moduleCode, ArrayList<PartialModule> userModuleList) throws AcademicException {
        ArrayList<Integer> moduleIndexList = currentPerson.getModulesAddedMap().get(moduleCode);

        for (int i = 0; i < moduleIndexList.size(); i++) {
            if (semesterValue == userModuleList.get(moduleIndexList.get(i)).getSemesterIndex()) {
                String errorMessage = String.format("%s already exist in semester %d!", moduleCode, semesterValue);
                throw new AcademicException(errorMessage);
            }
        }
    }

    /**
     * Returns true if module is not allowed to be shifted forward,
     * else returns false.
     *
     * @param newSemester semester to shift to
     * @param userModuleList user's module list
     * @param moduleCode module code
     * @return boolean
     */
    public static boolean notAllowedSemesterUpdateForward(int newSemester, ArrayList<PartialModule> userModuleList,
            String moduleCode) {
        int latestPassSemester = getLatestPassSemester(userModuleList, moduleCode);

        if (newSemester > latestPassSemester && latestPassSemester != 0) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if module is not allowed to be shifted backward,
     * else returns false.
     *
     * @param newSemester semester to shift to
     * @param userModuleList user's module list
     * @param moduleCode module code
     * @return boolean
     */
    public static boolean notAllowedSemesterUpdateBackward(int newSemester, ArrayList<PartialModule> userModuleList,
            String moduleCode) {
        int latestFailSemester = getLatestFailSemester(userModuleList, moduleCode);

        if (newSemester < latestFailSemester && latestFailSemester != 0) {
            return true;
        }
        return false;
    }

    /**
     * Checks user is updating from fail to passing grade.
     *
     * @param previousGrade User's previous grade
     * @param newGrade User's new grade
     *
     * @return User is updating from fail to passing grade.
     */
    public static boolean fromFailingToPass(String previousGrade, String newGrade) {
        ModuleValidator validator = new ModuleValidator();

        boolean isFailPrevious = validator.isRetakeGrade(previousGrade);
        boolean isFailNew = validator.isRetakeGrade(newGrade);

        return (isFailPrevious == true && isFailNew == false);
    }


    /**
     * Returns the latest semester taken for the module.
     *
     * @param modulesAddedList List of modules added
     * @param indexArrayList List of indexes for all occurrences of current module
     *
     * @return latestSemester the latest semester
     */
    public static int getLatestSemester(ArrayList<PartialModule> modulesAddedList, ArrayList<Integer> indexArrayList) {
        int latestSemester = modulesAddedList.get(indexArrayList.get(0)).getSemesterIndex();

        for (int index = 0; index < indexArrayList.size(); index++) {
            Integer currentIndexForModule = indexArrayList.get(index);
            int currentSemester = modulesAddedList.get(currentIndexForModule).getSemesterIndex();

            if (currentSemester > latestSemester) {
                latestSemester = currentSemester;
            }
        }
        return latestSemester;
    }

    /**
     * Returns the occurrence of the module taken in the latest semester.
     *
     * @param modulesAddedList List of modules added
     * @param indexArrayList List of indexes for all occurrences of current module
     *
     * @return latestSemester the occurrence of the module taken in the latest semester
     */
    public static PartialModule getLatestSemesterModule(ArrayList<PartialModule> modulesAddedList,
            ArrayList<Integer> indexArrayList) {
        PartialModule latestSemesterModule = modulesAddedList.get(indexArrayList.get(0));

        for (int index = 0; index < indexArrayList.size(); index++) {
            Integer currentIndexForModule = indexArrayList.get(index);
            PartialModule currentSemesterModule = modulesAddedList.get(currentIndexForModule);

            if (currentSemesterModule.getSemesterIndex() > latestSemesterModule.getSemesterIndex()) {
                latestSemesterModule = currentSemesterModule;
            }
        }
        return latestSemesterModule;
    }

    /**
     * Returns semester index of latest failed module.
     *
     * @param modulesAddedList user list of module
     * @param moduleCode module code
     * @return latest fail semester
     */
    public static int getLatestFailSemester(ArrayList<PartialModule> modulesAddedList, String moduleCode) {
        ModuleValidator validator = new ModuleValidator();
        int latestFailSemester = 0;
        for (PartialModule m : modulesAddedList) {
            if (m.getModuleCode().equalsIgnoreCase(moduleCode) && validator.isRetakeGrade(m.getGrade())
                    && m.getSemesterIndex() > latestFailSemester) {
                latestFailSemester = m.getSemesterIndex();
            }
        }
        return latestFailSemester;
    }

    /**
     * Returns semester index of latest pass module.
     *
     * @param modulesAddedList user list of module
     * @param moduleCode module code
     * @return latest pass semester
     */
    public static int getLatestPassSemester(ArrayList<PartialModule> modulesAddedList, String moduleCode) {
        ModuleValidator validator = new ModuleValidator();
        int latestPassSemester = 0;
        for (PartialModule m : modulesAddedList) {
            if (m.getModuleCode().equalsIgnoreCase(moduleCode) && !validator.isRetakeGrade(m.getGrade())
                    && m.getSemesterIndex() > latestPassSemester) {
                latestPassSemester = m.getSemesterIndex();
            }
        }
        return latestPassSemester;
    }

    /**
     * Gets the module with the largest cap for the current person given a certain module code.
     *
     * @param currentPerson Person object
     * @param moduleCode Module code to be checked
     *
     * @return Module with the largest cap
     */
    public static PartialModule getLargestCapForModule(Person currentPerson, String moduleCode) {
        ArrayList<PartialModule> modulesAddedList = currentPerson.getModulesList();
        ArrayList<Integer> indexArrayList = currentPerson.getModulesAddedMap().get(moduleCode);

        PartialModule moduleWithLargestCap = null;

        if (indexArrayList != null) {
            moduleWithLargestCap = modulesAddedList.get(indexArrayList.get(0));

            for (int index = 0; index < indexArrayList.size(); index++) {
                Integer currentIndexForModule = indexArrayList.get(index);
                PartialModule currentModule = modulesAddedList.get(currentIndexForModule);

                if (currentModule.getCap() > moduleWithLargestCap.getCap()) {
                    moduleWithLargestCap = currentModule;
                }
            }
        }

        return moduleWithLargestCap;
    }

    //@@author harryleecp

    /**
     * Update user's module map with information from user's module list.
     *
     * @param modulesList list of user's modules
     * @param currentPerson current user
     */
    public static void updateHashmap(ArrayList<PartialModule> modulesList, Person currentPerson) {
        HashMap<String, ArrayList<Integer>> newModuleAddedMap = new HashMap<>();
        for (int index = 0; index < modulesList.size(); index++) {
            String currentModuleCode = modulesList.get(index).getModuleCode();

            if (newModuleAddedMap.containsKey(currentModuleCode)) {
                newModuleAddedMap.get(currentModuleCode).add(index);
            } else {
                ArrayList<Integer> newIndexArray = new ArrayList<>();
                newIndexArray.add(index);
                newModuleAddedMap.put(modulesList.get(index).getModuleCode(), newIndexArray);
            }
        }
        currentPerson.setModulesAddedMap(newModuleAddedMap);
    }
}
