package seedu.wildwatch.ui;

public class LinePrinter {
    private static final int NUMBER_OF_UNDERSCORES = 60;

    /**
     * Prints horizontal lines that segment the output and input in the UI.
     */
    public static void printHorizontalLines() {
        for (int i = 0; i < NUMBER_OF_UNDERSCORES; i++) {
            System.out.print("_");
        }
        System.out.print(System.lineSeparator());
    }
}
