package seedu.wildwatch.command;

public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    /**
     * Requires UPDATE
     */
    private static final String helpCommandMessage =
            "_________________________________________________________________\n" +
            "1. Get help - shows the list of commands available for WildWatch\n" +
            "     Format: help\n" +
            "     Example: \n" +
            "       help\n" +
            "_________________________________________________________________\n";
    private static final String addCommandMessage =
            "_________________________________________________________________\n" +
            "2.  To add a new wildlife\n" +
            "   Standard Mode:\n" +
            "     Format: add D/<DATE> S/<SPECIES> N/<NAME> {R/<REMARKS>}\n" +
            "       DATE should be in the format DD-MM-YYYY\n" +
            "     Examples: \n" +
            "       add D/02-03-2023 S/Annam Leaf Turtle N/Ariel R/Injured left flipper\n\n" +
            "   Interactive Mode:\n" +
            "     Format: add i/\n" +
            "     Example: \n" +
            "       add i/\n" +
            "_________________________________________________________________\n";

    private static final String listCommandMessage =
            "_________________________________________________________________\n" +
            "3. To list all wildlife entries,\n" +
            "     Format: list\n" +
            "     Example: \n" +
            "       list\n" +
            "_________________________________________________________________\n";

    private static final String deleteCommandMessage =
            "_________________________________________________________________\n" +
            "4. To delete a wildlife entry\n" +
            "     Format: delete <INDEX>\n" +
            "       The index refers to the index number shown in the displayed observation list.\n" +
            "       Note:\n" +
            "           - The index must be a positive integer:\n" +
            "           - Deleted items are not recoverable:\n" +
            "     Example: \n" +
            "       delete 1\n" +
            "_________________________________________________________________\n";


    private static final String editCommandMessage =
            "_________________________________________________________________\n" +
            "5. To edit a wildlife entry\n" +
            "     Format: edit I/<INDEX> {D/<DATE>} {S/<SPECIES>} {N/<NAME>} {R/<REMARKS>}\n" +
            "       The index refers to the index number shown in the displayed observation list.\n" +
            "       Note:\n" +
            "           - The index must be a positive integer:\n" +
            "           - DATE, SPECIES, NAME, REMARKS arguments are optional, but at least 1 is required.\n" +
            "     Example: \n" +
            "       edit I/1 D/02-03-2023 S/Annam Leaf Turtle N/Ariel R/Injured left flipper\n" +
            "_________________________________________________________________\n";

    private static final String summaryCommandMessage =
            "_________________________________________________________________\n" +
            "6. To show a summary\n" +
            "     Format: summary {<SPECIES>}\n" +
            "       Note:\n" +
            "           - <SPECIES> argument is optional\n" +
            "     Examples: \n" +
            "       summary \n" +
            "       summary Annam Leaf Turtle\n" +
            "_________________________________________________________________\n";

    private static final String exportCommandMessage =
            "_________________________________________________________________\n" +
            "7. To export wildlife data\n" +
            "     Format: export {<FILENAME>}\n" +
            "       Note:\n" +
            "           - <FILENAME> argument is optional\n" +
            "           - <FILENAME> argument if specified should end with a \".csv\" extension\n" +
            "     Examples: \n" +
            "       export \n" +
            "       export WildOnes.csv\n" +
            "_________________________________________________________________\n";

    private static final String findCommandMessage =
            "_________________________________________________________________\n" +
            "8. To find wildlife entries\n" +
            "     Format: find <SEARCH>\n" +
            "       Note:\n" +
            "           - SEARCH argument could contain the full date, or part of species, name, or remarks.\n" +
            "     Example: \n" +
            "       find Annam Leaf Turtle\n" +
            "_________________________________________________________________\n";

    private static final String byeCommandMessage =
            "_________________________________________________________________\n" +
            "9. To exit the program\n" +
            "     Format: bye\n" +
            "     Example: \n" +
            "       bye \n" +
            "_________________________________________________________________\n";

    private static final String helpPage =
            "---------------------------HELP PAGE-----------------------------\n" +
            helpCommandMessage +
            addCommandMessage +
            listCommandMessage +
            deleteCommandMessage +
            editCommandMessage +
            summaryCommandMessage +
            exportCommandMessage +
            findCommandMessage +
            byeCommandMessage +
            "\n" +
            "-------------------------HELP PAGE END---------------------------\n";

    private static final String helpPageShort = "\n" +
            "---------------------------HELP PAGE (short)-----------------------------\n" +
            "Adding Entries: add D/<DATE> S/<SPECIES> N/<NAME> {R/<REMARKS>}\n" +
            "Adding Entries (Interactive): add i/\n" +
            "Deleting Entries: delete INDEX\n" +
            "Listing Entries: list\n" +
            "Finding Entries: find <SEARCH>\n" +
            "Editing Entries: edit I/<INDEX> {D/<DATE>} {S/<SPECIES>} {N/<NAME>} {R/<REMARKS>}\n" +
            "Summarizing Entries: summary {<SPECIES>}\n" +
            "Exporting Entries: export {<FILENAME>}\n" +
            "Exit: bye\n" +
            "See our User Guide: https://ay2324s1-cs2113t-w11-2.github.io/tp/UserGuide.html" +
            "\n" +
            "-------------------------HELP PAGE (short) END---------------------------\n";


    private static final String needHelpPage =
            "Please type \"help\" if you need help.";

    private String input;

    public HelpCommand(String input) {
        this.input = input;
    }

    public static void printNeedHelpMessage() {
        System.out.println(needHelpPage);
    }

    /**
     * Print specific help message based on the command specified
     *
     * @return String helpPage
     */
    public static String getHelpPage() {
        return helpPage;
    }

    /**
     * Print specific help message based on the command specified
     *
     * @param command command
     */
    public void printMessageForCommand(String command) {
        switch (command) {
        case "add":
            System.out.println("No worries, I'm here to help!");
            System.out.println(addCommandMessage);
            break;
        case "list":
            System.out.println("No worries, I'm here to help!");
            System.out.println(listCommandMessage);
            break;
        case "delete":
            System.out.println("No worries, I'm here to help!");
            System.out.println(deleteCommandMessage);
            break;
        case "edit":
            System.out.println("No worries, I'm here to help!");
            System.out.println(editCommandMessage);
            break;
        case "summary":
            System.out.println("No worries, I'm here to help!");
            System.out.println(summaryCommandMessage);
            break;
        case "export":
            System.out.println("No worries, I'm here to help!");
            System.out.println(exportCommandMessage);
            break;
        case "find":
            System.out.println("No worries, I'm here to help!");
            System.out.println(findCommandMessage);
            break;
        case "bye":
            System.out.println("No worries, I'm here to help!");
            System.out.println(byeCommandMessage);
            break;
        case "full":
            System.out.println("No worries, I'm here to help!");
            System.out.println(helpPage);
            break;
        default:
            System.out.println("Oops, no such help command exists! :-(");
            break;
        }
    }

    /**
     * Prints out help page
     */
    public void execute() {
        if (input.isEmpty()) {
            System.out.println("No worries, I'm here to help!");
            System.out.println(helpPage);
            return;
        }

        String argument = input.replace("help", "").trim();
        if (argument.isEmpty()) {
            // print short version
            System.out.println("No worries, I'm here to help!");
            System.out.println(helpPageShort);
        } else {
            // e.g `help add`
            printMessageForCommand(argument);
        }
    }
}
