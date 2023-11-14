package seedu.duke.commands;

/**
 * The `UsageInstructions` class provides usage instructions for the application,
 */
public class UsageInstructions {

    /**
     * Initializes a new instance of the `UsageInstructions` class.
     */
    public UsageInstructions() {
    }

    /**
     * Displays usage instructions and a link to the user guide.
     */
    public void getHelp() {
        System.out.println("You can access our user guide by " +
                "https://ay2324s1-cs2113-t18-3.github.io/tp/UserGuide.html\n");
        System.out.println("Available functions for KaChinnnng:");
        System.out.println("Adding an entry: add");
        System.out.println("Listing all entries: list");
        System.out.println("Deleting an entry: delete");
        System.out.println("Edit an entry: edit");
        System.out.println("Check balance: balance");
        System.out.println("Update exchange rate: update exchange rate");
        System.out.println("Finding an entry: find");
        System.out.println("Clearing all entries: clear");
        System.out.println("Exit the program: exit");
        System.out.println("All the date input is in the format dd/MM/yyyy\n");
        System.out.println("If you need assistance with individual commands, use the help function");
        System.out.println("Format: help <command>");
    }

    /**
     * Displays usage instructions for the 'add' command.
     */
    public void getHelpAddFunction() {
        System.out.println("Creates a new entry for income or expenses in the program.");
        System.out.println("Values of income and expense added have to be lower than 1000000.");
        System.out.println("Income and Expense values take up to 2 decimal places.\n");
        System.out.println("Format:");
        System.out.println("add income /de <description> /date <date> /amt [currency] <amount>");
        System.out.println("add expense /cat <category> /type <type> /de <description> /date <date> /amt " +
                "[currency] <amount>\n");
        System.out.println("There are only 3 categories for expenses: Food, Transport, Utilities.");
        System.out.println("There are 3 types associated with Food category: Breakfast, Lunch, Dinner, " +
                "else it will default to OTHER.");
        System.out.println("There are 4 types associated with Transport category: Bus, Train, Taxi, Fuel, " +
                "else it will default to OTHER.");
        System.out.println("There are 3 types associated with Utilities category: Water, Electricity, Gas, " +
                "else it will default to OTHER.");
    }

    /**
     * Displays usage instructions for the 'list' command.
     */
    public void getHelpListFunction() {
        System.out.println("Shows a full list of both the expenses and income created by the user.");
        System.out.println("User can choose to view the list of income/expenses separately as well.");
        System.out.println("Show the foreign currencies supported by KaChinnnng.");
        System.out.println("List all updated exchange rates.\n");
        System.out.println("Format:\n" +
                "list\n" +
                "list incomes\n" +
                "list expenses\n" +
                "list currencies\n" +
                "list exchange rates");
    }

    /**
     * Displays usage instructions for the 'delete' command.
     */
    public void getHelpDeleteFunction() {
        System.out.println("Deletes an entry from the list of income/expenses.\n");
        System.out.println("Format:\n" +
                "delete income <index_pos>\n" +
                "delete expense <index_pos>");
    }

    /**
     * Displays usage instructions for the 'edit' command.
     */
    public void getHelpEditFunction() {
        System.out.println("Edit an entry from the list of income/expenses.\n");
        System.out.println("Format:\n" +
                "edit income <index> /de <description> /date <date> /amt [currency] <amount>\n" +
                "edit expense <index> /cat <category> /type <type> " +
                "/de <description> /date <date> /amt [currency] <amount>");
    }

    /**
     * Displays usage instructions for the 'balance' command.
     */
    public void getHelpBalanceFunction() {
        System.out.println("Check the balance for current financial records\n");
        System.out.println("Format: balance");
    }

    /**
     * Displays usage instructions for the 'update exchange rate' command.
     */
    public void getHelpUpdateExchangeRateFunction() {
        System.out.println("Update exchange rate of a specific foreign currency.\n");
        System.out.println("Format: update exchange rate <supported_currency> <rate>");
    }

    /**
     * Displays usage instructions for the 'find' command.
     */
    public void getHelpFindFunction() {
        System.out.println("Find an entry from the existing list of income/expenses.");
        System.out.println("At least one of the optional fields must be provided.\n");
        System.out.println("Format:\n" +
                "find /t income [/de description] [/date date]\n" +
                "find /t expense [/cat category] [/de description] [/date date]");
    }

    /**
     * Displays usage instructions for the 'clear' command.
     */
    public void getHelpClearFunction() {
        System.out.println("Clearing all entries from the list of income/expenses.\n");
        System.out.println("Format:\n" +
                "clear incomes\n" +
                "clear expenses\n" +
                "clear all");
    }

    /**
     * Displays usage instructions for the 'exit' command.
     */
    public void getHelpExitFunction() {
        System.out.println("Format: exit");
    }
}
