package seedu.financialplanner.cashflow;

import seedu.financialplanner.enumerations.ExpenseType;
import seedu.financialplanner.enumerations.IncomeType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an income or expense.
 */
public abstract class Cashflow {

    protected static double balance = 0;
    protected static double incomeBalance = 0;
    protected static double expenseBalance = 0;
    protected double amount;
    protected int recur;
    protected String description;
    protected LocalDate date;
    protected boolean hasRecurred;
    protected final double MAX_AMOUNT = 999999999999.99;

    /**
     * Constructor for a cashflow. hasRecurred variable is set to false by default and date is initialised depending
     * on whether recur is set by the user.
     *
     * @param amount The value of the cashflow.
     * @param recur The number of days before the next automatic addition of the cashflow.
     * @param description The description of the cashflow.
     */
    public Cashflow(double amount, int recur, String description) {
        this.amount = amount;
        this.recur = recur;
        this.description = description;
        if (recur != 0) {
            this.date = LocalDate.now();
        }
        this.hasRecurred = false;
    }

    /**
     * Constructor for a cashflow.
     *
     * @param amount The value of the cashflow.
     * @param recur The number of days before the next automatic addition of the cashflow.
     * @param description The description of the cashflow.
     * @param date The date that the cashflow is added.
     * @param hasRecurred Whether the cashflow has recurred.
     */
    public Cashflow(double amount, int recur, String description, LocalDate date, boolean hasRecurred) {
        this.amount = amount;
        this.recur = recur;
        this.description = description;
        this.date = date;
        this.hasRecurred = hasRecurred;
    }

    protected Cashflow() {
    }

    /**
     * Sets the balance to 0.
     */
    public static void clearBalance() {
        balance = 0;
    }

    public static void setBalance(double amount) {
        balance = amount;
    }

    /**
     * Deletes the value of a cashflow from the balance.
     */
    public abstract void deleteCashflowValue();

    /**
     * Rounds a double to the specified number of decimal places. The rounding is done half-up.
     *
     * @param value The double to be rounded.
     * @param places The number of decimal places to round to.
     * @return The rounded double.
     */
    //@author mhadidg-reused
    //Reused from https://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    //@author mhadidg

    /**
     * Capitalizes the first letter of a provided string.
     *
     * @param line The input string to be capitalized.
     * @return The string that has been capitalized.
     */
    //@author Nick Bolton-reused
    //Reused from
    //https://stackoverflow.com/questions/1892765/how-to-capitalize-the-first-character-of-each-word-in-a-string
    public String capitalize(String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }
    //@author Nick Bolton

    /**
     * Formats the cashflow into an easy-to-read format to be output to the user.
     *
     * @return The formatted cashflow.
     */
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("####0.00");

        String string = "   Amount: " + decimalFormat.format(round(amount, 2));

        if (recur != 0) {
            string += System.lineSeparator() + "   Recurring every: " + recur + " days";
            string += ", date added: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            string += ", recurring on: " + date.plusDays(recur).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        if (description != null) {
            string += System.lineSeparator() + "   Description: " + description;
        }
        return string;
    }


    public double getAmount() {
        return this.amount;
    }

    public static double getBalance() {
        return balance;
    }

    public static double getIncomeBalance() {
        return incomeBalance;
    }

    public static double getExpenseBalance() {
        return expenseBalance;
    }

    public int getRecur() {
        return recur;
    }

    public void setRecur(int recur) {
        this.recur = recur;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean getHasRecurred() {
        return hasRecurred;
    }

    public void setHasRecurred(boolean hasRecurred) {
        this.hasRecurred = hasRecurred;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Formats the cashflow into a standard format to be saved into a text file.
     *
     * @return The formatted cashflow.
     */
    public String formatString() {
        String string;
        if (recur == 0) {
            string = " | 0 | false";
        } else {
            string = " | " + this.recur + " | " + this.hasRecurred;
            string += " | " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        if (description != null) {
            string += " | " + this.description;
        }

        return string;
    }

    public abstract ExpenseType getExpenseType();

    public abstract IncomeType getIncomeType();
}
