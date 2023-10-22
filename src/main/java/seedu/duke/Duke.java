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
            } else {
                System.out.println("    Invalid command! Please try again.");
            }
        }

    }
}