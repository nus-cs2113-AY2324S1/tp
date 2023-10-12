package seedu.duke.Parser;

/*
 * parser class to parse user input
 */
public class Parser {
    public static String parse(String fullCommand) {
        String trimmedCommand = fullCommand.trim();
        String commandLowerCase = trimmedCommand.toLowerCase();

        if (commandLowerCase.equals("bye")) {
            return "exit";
        }
        return "invalid";
    }
}
