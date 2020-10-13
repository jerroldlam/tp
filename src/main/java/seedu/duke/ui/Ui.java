package seedu.duke.ui;

import java.util.Scanner;

/**
 * Class representing the ui for PlanNUS.
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public Scanner getScanner() {
        return this.scanner;
    }
}
