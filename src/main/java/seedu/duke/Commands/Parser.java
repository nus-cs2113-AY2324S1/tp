package seedu.duke.Commands;

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
