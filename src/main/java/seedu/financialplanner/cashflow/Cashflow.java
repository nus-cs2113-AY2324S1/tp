package seedu.financialplanner.cashflow;

import seedu.financialplanner.enumerations.ExpenseType;
import seedu.financialplanner.enumerations.IncomeType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Cashflow {

    protected static double balance = 0;
    protected double amount;
    protected int recur;
    protected String description;
    protected LocalDate date;
    protected boolean hasRecurred;

    public Cashflow(double amount, int recur, String description) {
        this.amount = amount;
        this.recur = recur;
        this.description = description;
        if (recur != 0) {
            this.date = LocalDate.now();
        }
        this.hasRecurred = false;
    }
    public Cashflow(double amount, int recur, String description, LocalDate date, boolean hasRecurred) {
        this.amount = amount;
        this.recur = recur;
        this.description = description;
        this.date = date;
        this.hasRecurred = hasRecurred;
    }

    protected Cashflow() {
    }

    public static void clearBalance() {
        balance = 0;
    }

    public static void setBalance(double amount) {
        balance = amount;
    }

    public void deleteCashflowvalue() {
    }

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

    //@author Nick Bolton-reused
    //Reused from
    //https://stackoverflow.com/questions/1892765/how-to-capitalize-the-first-character-of-each-word-in-a-string
    public String capitalize(String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }
    //@author Nick Bolton

    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("####0.00");

        String string = "   Amount: " + decimalFormat.format(round(amount, 2));

        if (recur != 0) {
            string += System.lineSeparator() + "   Recurring every: " + recur + " days";
            string += ", starting from: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        if (description != null) {
            string += System.lineSeparator() + "   Description: " + description;
        }
        return string;
    }

    public String formatBalance() {
        DecimalFormat decimalFormat = new DecimalFormat("####0.00");

        return decimalFormat.format(round(Cashflow.balance, 2));
    }

    public double getAmount() {
        return this.amount;
    }

    public static double getBalance() {
        return balance;
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
