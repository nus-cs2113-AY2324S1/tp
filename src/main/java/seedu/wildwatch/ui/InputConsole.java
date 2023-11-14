package seedu.wildwatch.ui;

import java.util.Scanner;

public class InputConsole {
    private static Scanner in = new Scanner(System.in);

    /**
     * Returns input of the user from the console.
     *
     * @return The user's input as a trimmed string.
     */
    public static String inputRetriever() {
        return in.nextLine().trim();
    }
}
