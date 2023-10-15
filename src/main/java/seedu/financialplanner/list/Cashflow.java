package seedu.financialplanner.list;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Cashflow {

    protected static double balance = 0;
    protected double amount;
    protected String type;
    protected int recur;

    public Cashflow(double amount, String type, int recur) {
        this.amount = amount;
        this.type = type;
        this.recur = recur;
    }

    public Cashflow() {
        this.amount = 0;
        this.type = null;
        this.recur = 0;
    }
    //@author mhadidg-reused
    //Reused from https://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
    public double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    //@author mhadidg
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("####0.00");

        String string = "Type: " + type + System.lineSeparator()
                + "Amount:" + decimalFormat.format(round(amount, 2));

        if (recur != 0) {
            string += System.lineSeparator() + "Recurring every: " + recur + "days.";
        }

        return string;
    }

    public String formatBalance() {
        DecimalFormat decimalFormat = new DecimalFormat("####0.00");

        return decimalFormat.format(round(Cashflow.balance, 2));
    }

    public String formatString() {
        return this.amount + " | " + this.type + " | " + this.recur;
    }
}
