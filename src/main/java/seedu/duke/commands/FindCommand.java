package seedu.duke.commands;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.Year;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

import seedu.duke.financialrecords.Expense;
import seedu.duke.financialrecords.Income;
import seedu.duke.ui.Ui;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.io.File;

public class FindCommand extends Command {


    private static final Logger LOGGER = Logger.getLogger(FindCommand.class.getName());
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
    public FindCommand(ArrayList<Income> incomes, ArrayList<Expense> expenses, String type,
                       String category, String description, String dateString, Ui ui) throws KaChinnnngException {

        if (!type.equalsIgnoreCase("income") && !type.equalsIgnoreCase("expense")) {
            throw new KaChinnnngException("Invalid type. Please use 'income' or 'expense'.");
        }

        this.incomes = incomes;
        this.expenses = expenses;
        this.type = type;
        this.category = category;
        this.description = description;
        this.date = parseDate(dateString);  // Convert date string to LocalDate
        this.ui = ui;

        LOGGER.info("FindCommand initialised with type:" + type);
    }

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
            LOGGER.info("Found " + matchingExpenses.size() + " matching expenses");
            ui.showMatchingExpenses(matchingExpenses);  // Assuming you have such a method in Ui
        } else if ("income".equalsIgnoreCase(type)) {
            for (Income i : incomes) {
                if (matchesCriteria(i)) {
                    matchingIncomes.add(i);
                }
            }
            LOGGER.info("Found " + matchingIncomes.size() + " matching incomes");
            ui.showMatchingIncomes(matchingIncomes);  // Assuming you have such a method in Ui
        } else {
            LOGGER.log(Level.WARNING, "Invalid type: " + type);
        }
    }




    private boolean matchesDate(LocalDate dateToCheck) {
        if (this.date != null) {
            if (isSearchByMonth) {
                return dateToCheck.getMonthValue() == this.date.getMonthValue();
            } else {
                return dateToCheck.isEqual(this.date);
            }
        }
        return true; // If this.date is null, then any date matches the criteria
    }

    private boolean matchesCategory(String categoryToCheck) {
        return this.category == null || (categoryToCheck != null && categoryToCheck.equalsIgnoreCase(this.category));
    }

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

    private boolean matchesCriteria(Expense e) {
        return matchesDate(e.getDate()) && matchesCategory(e.getCategory()) && matchesDescription(e.getDescription());
    }

    private boolean matchesCriteria(Income i) {
        return matchesDate(i.getDate()) && matchesCategory(i.getCategory()) && matchesDescription(i.getDescription());
    }



    private LocalDate parseDate(String dateString) throws KaChinnnngException {
        if (dateString == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        LocalDate parsedDate = null;

        try {
            parsedDate = LocalDate.parse(dateString, formatter);
            return parsedDate;
        } catch (DateTimeParseException e) {
            // Check if dateString is just a month
            for (Month month : Month.values()) {
                if (month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH).equalsIgnoreCase(dateString)) {
                    isSearchByMonth = true;
                    parsedDate = LocalDate.of(Year.now().getValue(), month, 1);
                    assert parsedDate.getMonth() == month : "parsedDate month should be " + month;
                    return parsedDate;
                }
            }
            LOGGER.log(Level.WARNING, "failed to parse date" + dateString);
            throw new KaChinnnngException("Invalid date format. Please use format like 20/Oct/2023.");
        }
    }
}

