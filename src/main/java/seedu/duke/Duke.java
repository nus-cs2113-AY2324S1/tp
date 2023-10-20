//@@author wendelinwemhoener & kherlenbayasgalan

package seedu.duke;

import seedu.duke.flashcard.Flashcard;
import seedu.duke.flashcard.FlashcardComponent;
import seedu.duke.calendar.CalendarManager;
import seedu.duke.calendar.Event;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public Duke() {}

    public static void main(String[] args) {
        new Duke().run();
    }

    private void runCalendar() {
        System.out.print("Enter your command: ");

        CalendarManager manager = new CalendarManager(new ArrayList<Event>());

        Scanner scanner = new Scanner(System.in);
        String input;
        boolean isDone = false;

        while (!isDone) {
            input = scanner.nextLine();

            if (input.equals("end program")) {
                System.out.println("Bye bye");
                break;
            } else if (input.equals("flashcard")) {
                System.out.println("    Switched to flashcard functions");
                runFlashcard();
            } else if (manager.validCommand(input)) {
                manager.startCalendar(input);
            } else {
                System.out.println("Invalid command! Enter a valid command.");
            }
        }
    }

    private void runFlashcard() {
        FlashcardComponent fc = new FlashcardComponent(new ArrayList<Flashcard>());

        Scanner scanner = new Scanner(System.in);
        String input;
        boolean shouldTerminate = false;

        System.out.print("Enter your command: ");
        while (!shouldTerminate) {
            input = scanner.nextLine();

            if (input.equals("end program")) {
                System.out.println("Bye bye");
                break;
            } else if (input.equals("calendar")) {
                System.out.println("    Switched to calendar functions");
                runCalendar();
            } else if (fc.isResponsible(input)) {
                fc.processInput(input);
            } else {
                System.out.println("    Invalid command! Sorry; please try again.");
            }
        }
    }

    private void run() {
        String flashcardOrCalendar;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Do you choose flashcard or calendar? ");
        flashcardOrCalendar = scanner.nextLine();

        if (flashcardOrCalendar.equals("flashcard")) {
            runFlashcard();
        } else if (flashcardOrCalendar.equals("calendar")) {
            runCalendar();
        } else {
            System.out.println("Invalid command! Sorry; please try again.");
            run();
        }
    }
}
