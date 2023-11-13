package seedu.nuscents.ui;

public class Messages {
    public static final String LINE = "-------------------------------------------------------------------------------"
            + "--------------";
    public static final String LOGO = " __   __  __   __   ____   _____ _____ __   __ ________   ____\n"
            + "|  \\ |  |  |  |  |/     / /   __|     |  \\ |  |__    __|/     /\n"
            + "|   \\|  |  |  |  |\\   __\\|   /  |   __|   \\|  |  |  |   \\   __\\\n"
            + "|       |  |  |  | \\__   |  |   |   __|       |  |  |    \\__   |\n"
            + "|       |  |__|  |/      |   \\__|     |       |  |  |   /      |\n"
            + "|__|\\___|________|______/ \\_____|_____|__|\\___|  |__|   |_____/\n";
    public static final String MESSAGE_EXIT = "Thank you for using NUScents. Hope to see you again soon!";
    public static final String HELP_MENU =
            "- `list` :" + System.lineSeparator() +
                    "    List all entries." + System.lineSeparator() +
                    "- `allowance /amt AMT /date DATE /desc DESC [/note NOTE] [/cat CAT]` :" + System.lineSeparator() +
                    "    Add an allowance." + System.lineSeparator() +
                    "- `expense /amt AMT /date DATE /desc DESC [/note NOTE] [/cat CAT]` :" + System.lineSeparator() +
                    "    Add an expense." + System.lineSeparator() +
                    "- `delete INDEX` :" + System.lineSeparator() +
                    "    Delete an entry." + System.lineSeparator() +
                    "- `budget AMOUNT` :" + System.lineSeparator() +
                    "    Set a budget amount for expenses." + System.lineSeparator() +
                    "- `filter CATEGORY` :" + System.lineSeparator() +
                    "    Filter by category." + System.lineSeparator() +
                    "- `view INDEX` :" + System.lineSeparator() +
                    "    View entry detail." + System.lineSeparator() +
                    "- `edit INDEX ENTRY` :" + System.lineSeparator() +
                    "    Edit an entry (same format as adding an allowance/expense)." + System.lineSeparator() +
                    "- `exit` :" + System.lineSeparator() +
                    "    Exit program.";

    public static final String MESSAGE_UNKNOWN = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String MESSAGE_EMPTY_ALLOWANCE = "OOPS!!! Invalid input format for adding an allowance.";
    public static final String MESSAGE_EMPTY_EXPENSE = "OOPS!!! Invalid input format for adding an expense.";
    public static final String MESSAGE_INVALID_AMOUNT = "OOPS!!! Amount +ve float with at most 2 d.p. less than 100000";
    public static final String MESSAGE_INVALID_DATE = "OOPS!!! The format of the date is invalid.";
    public static final String MESSAGE_INVALID_INDEX = "OOPS!!! This is an invalid transaction index.";
    public static final String MESSAGE_INVALID_INDEX_ARGUMENTS = "OOPS!!! The input should consist only of digits.";
    public static final String MESSAGE_EMPTY_LIST = "You have not made any transactions!";
    public static final String MESSAGE_INVALID_LIST = "OOPS!!! The correct format is 'list' alone.";
    public static final String MESSAGE_INVALID_HELP = "OOPS!!! The correct format is 'help' alone.";
    public static final String MESSAGE_EMPTY_BUDGET = "OOPS!!! The budget amount cannot be empty.";
    public static final String MESSAGE_INVALID_BUDGET = "OOPS!!! The budget amount requires a +ve valid float value";
    public static final String MESSAGE_INVALID_BUDGET_FLOAT_DP = "OOPS!!! The budget amount cannot exceed 2 d.p.";
    public static final String MESSAGE_INVALID_BUDGET_SIZE = "OOPS!!! The budget amount cannot exceed 100000";
    public static final String MESSAGE_EMPTY_INDEX = "OOPS!!! The index of a delete/view/edit command cannot be empty.";
    public static final String MESSAGE_EMPTY_EDIT = "OOPS!!! Invalid input format for editing a transaction.";
    public static final String MESSAGE_FATAL_ERROR = "OOPS!!! Fatal error occurred...";
    public static final String MESSAGE_FILE_ACCESS_ERROR = "OOPS!!! A problem occurred while accessing the data file."
            + " Ensure you have the correct permissions.";
    public static final String MESSAGE_UNKNOWN_EXPENSE_CATEGORY = "OOPS!!! The category you provided is not a "
            + "valid category.\n"
            + "Valid categories are FOOD/ENTERTAINMENT/TRANSPORTATION/UTILITY/RENT/OTHERS.";
    public static final String MESSAGE_UNKNOWN_ALLOWANCE_CATEGORY = "OOPS!!! The category you provided is not a "
            + "valid category.\n "
            + "Valid categories are SALARY/ALLOWANCE/INVESTMENTS/GIFTS/OTHERS.";
    public static final String MESSAGE_UNKNOWN_FILTER_CATEGORY = "OOPS!!! The category you provided is not a "
            + "valid category.\n"
            + "Valid categories are "
            + "SALARY/ALLOWANCE/INVESTMENTS/GIFTS/FOOD/ENTERTAINMENT/TRANSPORTATION/UTILITY/RENT/OTHERS.";
    public static final String MESSAGE_CORRUPTED_DATA_ERROR = "Transaction not read from corrupt data file.";
}
