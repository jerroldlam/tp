package seedu.duke.storage.commons;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import static seedu.duke.apps.academicplanner.commons.SharedUtils.updateHashmap;
import seedu.duke.global.objects.PartialModule;
import seedu.duke.global.objects.Person;
import seedu.duke.storage.exceptions.SaveFileException;

//@@author harryleecp
public class SaveUtils {
    private static final String NEWLINE = "\n";
    private static final String SEPARATOR = "|";

    private Person currentPerson;
    private String fileName;

    /**
     * Default constructor for handling save.
     *
     * @param currentPerson a Person object storing information for current person
     * @param fileName a String for name of save file
     */
    public SaveUtils(Person currentPerson, String fileName) {
        this.currentPerson = currentPerson;
        this.fileName = fileName;
    }

    /**
     * Function to save person object into save file.
     *
     * @throws SaveFileException Exception thrown if there is an error accessing save file
     */
    public void save() throws SaveFileException {
        try {
            ArrayList<PartialModule> sortedBySem = currentPerson.getModulesList();
            sortedBySem.sort(Comparator.comparing(PartialModule::getSemesterIndex));

            updateHashmap(currentPerson.getModulesList(), currentPerson);

            String textToSave = "";

            for (PartialModule item : sortedBySem) {
                textToSave += item.getSemesterIndex() + SEPARATOR
                        + item.getModuleCode() + SEPARATOR
                        + item.getGrade() + SEPARATOR
                        + item.getModuleCredit() + NEWLINE;
            }

            FileWriter fw = new FileWriter(fileName);
            fw.write(textToSave);
            fw.close();
            System.out.println("Data successfully saved!");
        } catch (Exception e) {
            throw new SaveFileException("Error accessing save file!");
        }
    }
}
