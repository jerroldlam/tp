package seedu.duke.global.objects;

import java.util.ArrayList;
import java.util.HashMap;

//@@author jerroldlam
/**
 * Class representing the user for PlanNUS.
 */
public class Person {

    private String personName;
    private double currentTotalMcxGrade;
    private int currentMc;
    private int currentMcAfterSU;
    private ArrayList<PartialModule> modulesList;
    private HashMap<String, ArrayList<Integer>> modulesAddedMap;

    public Person(String personName) {
        this.currentTotalMcxGrade = 0.0;
        this.currentMc = 0;
        this.currentMcAfterSU = 0;
        this.personName = personName;
        this.modulesList = new ArrayList<>();
        this.modulesAddedMap = new HashMap<>();
    }

    public String getPersonName() {
        return personName;
    }

    public double getCurrentTotalMcxGrade() {
        return currentTotalMcxGrade;
    }

    public void setCurrentTotalMcxGrade(double currentTotalMcxGrade) {
        this.currentTotalMcxGrade = currentTotalMcxGrade;
    }

    public int getCurrentMc() {
        return currentMc;
    }

    public void setCurrentMc(int currentMc) {
        this.currentMc = currentMc;
    }

    public int getCurrentMcAfterSU() {
        return currentMcAfterSU;
    }

    public void setCurrentMcAfterSU(int currentMcAfterSU) {
        this.currentMcAfterSU = currentMcAfterSU;
    }

    public ArrayList<PartialModule> getModulesList() {
        return modulesList;
    }

    public HashMap<String, ArrayList<Integer>> getModulesAddedMap() {
        return modulesAddedMap;
    }

    public void setModulesAddedMap(HashMap<String, ArrayList<Integer>> modulesAddedMap) {
        this.modulesAddedMap = modulesAddedMap;
    }
}
