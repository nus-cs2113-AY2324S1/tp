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

public class FindCommand {

    private final ArrayList<Income> incomes;
    private final ArrayList<Expense> expenses;
    private final String type;
    private final String category;
    private final String description;
    private final LocalDate date;
    private final Ui ui;
    private boolean isSearchByMonth = false;

    public FindCommand(ArrayList<Income> incomes, ArrayList<Expense> expenses, String type,
                       String category, String description, String dateString, Ui ui) throws KaChinnnngException {
        this.incomes = incomes;
        this.expenses = expenses;
        this.type = type;
        this.category = category;
        this.description = description;
        this.date = parseDate(dateString);  // Convert date string to LocalDate
        this.ui = ui;
    }

    public void execute() throws KaChinnnngException {
        ArrayList<Expense> matchingExpenses = new ArrayList<>();
        ArrayList<Income> matchingIncomes = new ArrayList<>();

        if ("expense".equalsIgnoreCase(type)) {
            for (Expense e : expenses) {
                if (matchesCriteria(e)) {
                    matchingExpenses.add(e);
                }
            }
            ui.showMatchingExpenses(matchingExpenses);  // Assuming you have such a method in Ui
        } else if ("income".equalsIgnoreCase(type)) {
            for (Income i : incomes) {
                if (matchesCriteria(i)) {
                    matchingIncomes.add(i);
                }
            }
            ui.showMatchingIncomes(matchingIncomes);  // Assuming you have such a method in Ui
        }
    }

    private boolean matchesCriteria(Expense e) {
        if (this.category != null && (e.getCategory() == null || !e.getCategory().equalsIgnoreCase(this.category))) {
            return false;
        }
        if (this.date != null) {
            if (isSearchByMonth) {
                if (e.getDate().getMonthValue() != this.date.getMonthValue()) {
                    return false;
                }
            } else {
                if (!e.getDate().isEqual(this.date)) {
                    return false;
                }
            }
        }
        if (this.description != null) {
            String[] keywords = this.description.split("\\s+");
            boolean containsAllKeywords = true;
            for (String keyword : keywords) {
                if (!e.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                    containsAllKeywords = false;
                    break;
                }
            }
            return containsAllKeywords;
        }
        return true;
    }

    private boolean matchesCriteria(Income i) {
        if (this.date != null && !i.getDate().isEqual(this.date)) {
            return false;
        }
        if (this.category != null && !i.getCategory().equalsIgnoreCase(this.category.toLowerCase())) {
            return false;
        }
        if (this.description != null) {
            String[] keywords = this.description.split("\\s+");
            boolean containsAllKeywords = true;
            for (String keyword : keywords) {
                if (!i.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                    containsAllKeywords = false;
                    break;
                }
            }
            return containsAllKeywords;
        }
        return true;
    }


    private LocalDate parseDate(String dateString) throws KaChinnnngException {
        if (dateString == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");

        try {
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            // Check if dateString is just a month
            for (Month month : Month.values()) {
                if (month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH).equalsIgnoreCase(dateString)) {
                    isSearchByMonth = true;
                    return LocalDate.of(Year.now().getValue(), month, 1);
                }
            }
            throw new KaChinnnngException("Invalid date format. Please use format like 20/Oct/2023.");
        }
    }
}

