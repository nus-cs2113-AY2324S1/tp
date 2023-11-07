package seedu.duke.commands;


public class UsageInstructions {
    // Shows a message linking the user to the user guide of our app

    /**
     * The `UsageInstructions` class provides usage instructions for the application,
     * including command formats and a link to the user guide.
     */
    public UsageInstructions() {

    }

    /**
     * Displays usage instructions for the application, including command formats and a link to the user guide.
     */
    public void getHelp(){
        // Shows a message linking the user to the user guide of our app
        System.out.println("You can access our user guide by " +
                "https://ay2324s1-cs2113-t18-3.github.io/tp/UserGuide.html\n");
        // Displays the various commands that user can use including the respective formats of the commands
        System.out.println("Functions and their format:\n");
        System.out.println("Adding an entry: add\nFormat:\n" +
                "add income /de <description> /date <date> /amt [currency] <amount>\n" +
                "add expense /cat <category> /type <type> " +
                "/de <description> /date <date> /amt <amount>\n");
        System.out.println("Listing all entries: list\nFormat:\n" + "list\n" + "list income\n" +
                "list expense\nlist currencies\nlist exchange rates\n");
        System.out.println("Deleting an entry: delete\nFormat:\ndelete income <index_pos>\n" +
                "delete expense <index_pos>\n");
        System.out.println("Edit an entry: edit\nFormat:\n" +
                "edit income <index> /de <description> /date <date> /amt [currency] <amount>\n" +
                "edit expense <index> /cat <catergory> /type <type> /de <description> /date <date> " +
                "/amt [currency] <amount>\n");
        System.out.println("Check balance of income: balance\nFormat: " + "balance\n");
        System.out.println("Update exchange rate: update exchange rate\nFormat: " + "update exchange rate " +
                "<supported_currency> <rate>\n");
        System.out.println("Finding an entry: find\nFormat:\n" +
                "find /t income /de [description] /date [date]\n" +
                "find /t expense /cat [category] /type [type] /de [description] /date [date]\n");
        System.out.println("Clearing all entries: clear\nFormat:\n" +
                "clear income/expense\n" +
                "clear all\n");
        System.out.println("Exiting the program: exit\nFormat: " + "exit");

    }
}
