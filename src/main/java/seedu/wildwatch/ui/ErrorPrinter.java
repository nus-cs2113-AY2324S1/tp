package seedu.wildwatch.ui;

public class ErrorPrinter {
    public static void corruptLoggerMessagePrinter() {
        System.out.println("Logger is corrupted.\nUnable to be configured");
    }

    public static void customInvalidInputMessagePrinter(String e) {
        System.out.println(e);
    }

    /**
     * Prints an error message to the console.
     *
     * @param exception The exception that occurred.
     */
    public static void errorMessagePrinter(Exception exception) {
        System.out.println("An error occurred: " + exception.getMessage());
    }

    /**
     * Prints out appropriate message when invalid input is typed into the program.
     */
    public static void invalidInputMessagePrinter() {
        System.out.println("OOPS!!! Format of command is incorrect.");
    }

    /**
     * Prints out appropriate message when the input date is invalid
     */
    public static void invalidDateMessagePrinter() {
        System.out.println("OOPS!!! Invalid Date input :-(");
    }

    public static void invalidIndexMessagePrinter() {
        System.out.println("OOPS!!! Invalid Index input :-(");
    }
}
