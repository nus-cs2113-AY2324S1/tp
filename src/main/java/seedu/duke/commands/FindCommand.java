package seedu.duke.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.YearMonth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.duke.financialrecords.Expense;
import seedu.duke.financialrecords.Income;
import seedu.duke.ui.Ui;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.io.File;

/**
 * Represents a command to find and display financial records (either incomes or expenses) based on specific criteria.
 * The criteria include type (income/expense), category, description, and date.
 * This command supports searching by a specific date or by a month. If a date isn't provided, it will not be used
 * as a filter. Similarly, if the category or description isn't provided, they won't be used as filters.
 */
public class FindCommand extends Commands {

    private static final Logger LOGGER = Logger.getLogger(FindCommand.class.getName());
    private static final List<String> VALID_EXPENSE_CAT = Arrays.asList("food", "transport", "utilities");
    private final ArrayList<Income> incomes;
    private final ArrayList<Expense> expenses;
    private final String type;
    private final String category;
    private final String description;
    private final LocalDate date;
    private final Ui ui;
    private boolean isSearchByMonth = false;



    static {
        try {
            File dir = new File("logs");
            if (!dir.exists()) {
                if(!dir.mkdirs()) {
                    throw new KaChinnnngException("Failed to create directory " + dir.getAbsolutePath());
                }
            }
            FileHandler fh = new FileHandler("logs/FindCommand.log", true);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            LOGGER.addHandler(fh);
            LOGGER.setLevel(Level.ALL);
            LOGGER.setUseParentHandlers(false);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error creating log file", e);
        }
    }

    /**
     * Constructs a new FindCommand with the given criteria.
     *
     * @param incomes      The list of incomes to search from.
     * @param expenses     The list of expenses to search from.
     * @param type         The type of financial record to search for (either "income" or "expense").
     * @param category     The category to filter the results by.
     * @param description  The description to filter the results by.
     * @param dateString   The date (or month) to filter the results by. Expected format: dd/MMM/yyyy.
     * @param ui           The UI component to display results.
     *
     * @throws KaChinnnngException If an invalid type is provided or if there's an error parsing the date.
     */
    public FindCommand(ArrayList<Income> incomes, ArrayList<Expense> expenses, String type,
                       String category, String description, String dateString, Ui ui) throws KaChinnnngException {

        if (type == null || !type.equalsIgnoreCase("income") &&
                !type.equalsIgnoreCase("expense")) {
            throw new KaChinnnngException("Invalid type. Please use 'income' or 'expense'.");
        }

        if ("expense".equalsIgnoreCase(type) && category != null &&
                !VALID_EXPENSE_CAT.contains(category.toLowerCase())) {
            throw new KaChinnnngException("Invalid expense category provided! Allowed categories are: "
                    + VALID_EXPENSE_CAT);
        }


        this.incomes = incomes;
        this.expenses = expenses;
        this.type = type;
        this.category = category;
        this.description = description;
        this.date = parseDate(dateString);  // Convert date string to LocalDate
        this.ui = ui;

        LOGGER.log(Level.INFO, String.format("FindCommand initialised with type: %s", type));
    }

    /**
     * Executes the find command. Searches through the provided list of incomes or expenses and displays
     * matching results based on the given criteria.
     *
     * @throws KaChinnnngException If there's an error during execution, such as an invalid type.
     */
    @Override
    public void execute() throws KaChinnnngException {
        LOGGER.info("Executing FindCommand");

        assert incomes != null : "incomes should not be null";
        assert expenses != null : "expenses should not be null";

        ArrayList<Expense> matchingExpenses = new ArrayList<>();
        ArrayList<Income> matchingIncomes = new ArrayList<>();

        if ("expense".equalsIgnoreCase(type)) {
            for (Expense e : expenses) {
                if (matchesCriteria(e)) {
                    matchingExpenses.add(e);
                }
            }
            LOGGER.log(Level.INFO, String.format("Found %d matching expenses", matchingExpenses.size()));
            if (matchingExpenses.isEmpty()) {
                Ui.showLineDivider();
                ui.printMessage("No matching expenses found.");
            } else {
                ui.showMatchingExpenses(matchingExpenses);
            }
        } else if ("income".equalsIgnoreCase(type)) {
            for (Income i : incomes) {
                if (matchesCriteria(i)) {
                    matchingIncomes.add(i);
                }
            }
            LOGGER.log(Level.INFO,String.format("Found %d matching incomes", matchingIncomes.size()));
            if (matchingIncomes.isEmpty()) {
                Ui.showLineDivider();
                ui.printMessage("No matching incomes found.");
            } else {
                ui.showMatchingIncomes(matchingIncomes);
            }
        } else {
            LOGGER.log(Level.WARNING, "Invalid type: " + type);
        }
    }

    /**
     * Determines if a given date matches the criteria specified in the command.
     *
     * @param dateToCheck  The date to be checked.
     * @return True if the date matches the criteria, false otherwise.
     */
    private boolean matchesDate(LocalDate dateToCheck) {
        if (this.date != null) {
            if (isSearchByMonth) {
                return dateToCheck.getMonthValue() == this.date.getMonthValue()
                        && dateToCheck.getYear() == this.date.getYear();
            } else {
                return dateToCheck.isEqual(this.date);
            }
        }
        return true; // If this.date is null, then any date matches the criteria
    }

    /**
     * Determines if a given category matches the criteria specified in the command.
     *
     * @param categoryToCheck The category to be checked.
     * @return True if the category matches the criteria, false otherwise.
     */
    private boolean matchesCategory(String categoryToCheck) {
        return this.category == null || (categoryToCheck != null && categoryToCheck.equalsIgnoreCase(this.category));
    }

    /**
     * Determines if a given description matches the criteria specified in the command.
     * The description matches if it contains all the keywords specified in the command.
     * If no description is specified in the command, then any description matches the criteria.
     *
     * @param descriptionToCheck The description to be checked.
     * @return True if the description matches the criteria, false otherwise.
     */
    private boolean matchesDescription(String descriptionToCheck) {
        if (this.description != null) {
            String[] keywords = this.description.split("\\s+");
            for (String keyword : keywords) {
                if (!descriptionToCheck.toLowerCase().contains(keyword.toLowerCase())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Determines if a given expense matches the criteria specified in the command.
     *
     * @param e The expense to be checked.
     * @return True if the expense matches the criteria, false otherwise.
     */
    private boolean matchesCriteria(Expense e) {
        return matchesDate(e.getDate()) &&
                matchesCategory(e.getCategory()) &&
                matchesDescription(e.getDescription()) &&
                e.getAmount() != 0;
    }

    /**
     * Determines if a given income matches the criteria specified in the command.
     *
     * @param i The income to be checked.
     * @return True if the income matches the criteria, false otherwise.
     */
    private boolean matchesCriteria(Income i) {
        return matchesDate(i.getDate()) &&
                matchesCategory(i.getCategory()) &&
                matchesDescription(i.getDescription()) &&
                i.getAmount() != 0;
    }


    /**
     * Converts a date string into a LocalDate object. Supports full date strings and month names.
     *
     * @param dateString Date as a string in the format dd/MMM/yyyy or as a month name.
     * @return LocalDate representation of the provided dateString.
     * @throws KaChinnnngException If the date string is in an invalid format.
     */
    private LocalDate parseDate(String dateString) throws KaChinnnngException {
        if (dateString == null) {
            return null;
        }

        DateTimeFormatter formatterDayMMM = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        DateTimeFormatter formatterDayMM = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterMonthMM = DateTimeFormatter.ofPattern("MM/yyyy");
        DateTimeFormatter formatterMonthMMM = DateTimeFormatter.ofPattern("MMM/yyyy");
        LocalDate parsedDate = null;

        try {
            parsedDate = LocalDate.parse(dateString, formatterDayMMM);
        } catch (DateTimeParseException e1) {
            try {
                parsedDate = LocalDate.parse(dateString, formatterDayMM);
            } catch (DateTimeParseException e2) {
                try {
                    parsedDate = YearMonth.parse(dateString, formatterMonthMM).atDay(1);
                    isSearchByMonth = true;
                } catch (DateTimeParseException e3) {
                    try {
                        parsedDate = YearMonth.parse(dateString, formatterMonthMMM).atDay(1);
                        isSearchByMonth = true;
                    } catch (DateTimeParseException e4) {
                        LOGGER.log(Level.WARNING, "Failed to parse date " + dateString, e4);
                        throw new KaChinnnngException("Invalid date format. " +
                                "Please use formats like 20/Oct/2023, 20/10/2023, 10/2023, or Oct/2023.");
                    }
                }
            }
        }
        return parsedDate;
    }
}
