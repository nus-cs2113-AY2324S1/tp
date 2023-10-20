//@@author wendelinwemhoener & bayasgalankherlen

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
            System.out.print("Enter your command: ");
            input = scanner.nextLine();

            if (fc.isResponsible(input)) {
                fc.processInput(input);
            } else if (cm.isResponsible(input)) {
                cm.processInput(input);
            } else if (input.equals("exit program")) {
                System.out.println("    You are exiting TaskLinker! Bye!");
                break;
            } else {
                System.out.println("    Invalid command! Please try again.");
            }
        }

    }
}

