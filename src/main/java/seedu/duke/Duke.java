//@@author wendelinwemhoener

package seedu.duke;

import seedu.duke.flashcard.Flashcard;
import seedu.duke.flashcard.FlashcardComponent;
import seedu.duke.flashcard.FlashcardDirectory;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public Duke() {}

    public static void main(String[] args) {
        new Duke().run();
    }

    private void run() {
        FlashcardComponent fc = new FlashcardComponent(new ArrayList<Flashcard>());

        Scanner scanner = new Scanner(System.in);
        String input;
        boolean shouldTerminate = false;

        while (!shouldTerminate) {
            input = scanner.nextLine();

            if (fc.isResponsible(input)) {
                fc.processInput(input);
            }
        }

    }
}
