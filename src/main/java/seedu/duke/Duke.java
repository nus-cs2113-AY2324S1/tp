//@@author wendelinwemhoener

package seedu.duke;

import seedu.duke.calendar.CalendarManager;
import seedu.duke.flashcard.FlashcardComponent;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public Duke() {}

    public static void main(String[] args) {
        new Duke().run();
    }

    private void run() {
        FlashcardComponent fc = new FlashcardComponent();
        CalendarManager cm = new CalendarManager(new ArrayList<>());

        Scanner scanner = new Scanner(System.in);
        String input;
        boolean shouldTerminate = false;

        while (!shouldTerminate) {
            input = scanner.nextLine();

            if (fc.isResponsible(input)) {
                fc.processInput(input);
            } else if (cm.isResponsible(input)) {
                cm.processInput(input);
            } else if (input.startsWith("help")) {
                printHelp();
            } else {
                System.out.println("    Invalid command! Please try again.");
            }
        }

    }

    private void printHelp() {
        System.out.println("    If you need help, please consult our " +
                "user guide at https://ay2324s1-cs2113-f11-3.github" +
                ".io/tp/UserGuide.html");
        System.out.println();
        System.out.println("    Here is a quick overview over all available " +
                "commands: ");

        String[] commandFormats = new String[] {
            "list flashcards",
            "create flashcard",
            "review flashcards",
            "help",
        };

        for (String commandFormat : commandFormats) {
            System.out.println("    - " + commandFormat);
        }
    }
}
