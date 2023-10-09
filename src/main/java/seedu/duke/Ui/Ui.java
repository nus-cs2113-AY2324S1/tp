package seedu.duke.Ui;
import java.util.Scanner;
import java.util.ArrayList;


public class Ui {
    private static final String lineDivider = "____________________________________________________________";

    public Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public static void printWelcomeMessage() {
        System.out.println(lineDivider);
        System.out.println("Welcome to KaChinnnngggg! How may i assist you today?");
        System.out.println(lineDivider);
    }

    public static void printGoodbyeMessage() {
        System.out.println(lineDivider);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(lineDivider);
    }
}
