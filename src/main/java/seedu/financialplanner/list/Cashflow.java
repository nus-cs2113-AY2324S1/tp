package seedu.financialplanner.list;

public class Cashflow {

    protected static double balance = 0;
    protected double value;
    protected String type;
    protected int recur;

    public Cashflow(double value, String type, int recur) {
        this.value = value;
        this.type = type;
        this.recur = recur;
    }

    public Cashflow() {
        this.value = 0;
        this.type = null;
        this.recur = 0;
    }

    public String formatString() {
        return this.value + " | " + this.type + " | " + this.recur;
    }

    public String getType() {
        return type;
    }

    public double getValue() {
        return value;
    }

}
