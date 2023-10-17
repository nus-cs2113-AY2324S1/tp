package seedu.duke.parser;

/*
 * parser class to parse user input
 */
public class Parser {
    /**
     * This method is used to parse the user input.
     * This method is used by the Main class in the application
     *
     * @param fullCommand String containing the user input
     * @return String containing the command to be executed
     */
    public static String parse(String fullCommand) {
        String trimmedCommand = fullCommand.trim();
        String commandLowerCase = trimmedCommand.toLowerCase();

        if (commandLowerCase.equals("bye")) {
            return "exit";
        } else if (commandLowerCase.startsWith("add income")) {
            return "add_income";
        } else if (commandLowerCase.equals("list income")) {
            return "list_income";
<<<<<<< HEAD:src/main/java/seedu/duke/Parser/Parser.java
        } else if (commandLowerCase.startsWith("add expense")) {
            return "add_expense";
        } else if (commandLowerCase.equals(("list expense"))) {
            return "list_expense";
=======
        } else if (commandLowerCase.equals("help")) {
            return "help";
>>>>>>> 8f9b494475f77182a35a655b40d0fefb1fcade21:src/main/java/seedu/duke/parser/Parser.java
        }
        return "invalid";
    }
}
