package seedu.financialplanner.commands;

import seedu.financialplanner.list.FinancialList;

public class Entry extends Command{
    private static final String INCOME = "income";
    private static final String EXPENSE = "expense";
    protected String entryType;
    protected String parameters;
    protected FinancialList list;

    public Entry(String entryParameters, FinancialList list) {
        String[] split = entryParameters.split(" ", 2);
        this.entryType = split[0];
        this.parameters = split[1];
        this.list = list;
        addEntry();
    }

    private int determineRecur() {
        if (parameters.contains("r/")) {
            int indexOfRecur = parameters.indexOf("r/");
            String recur = parameters.substring(indexOfRecur + 2).trim();
            return Integer.parseInt(recur);
        }
        return 0;
    }

    private void addEntry() {
        int recur = determineRecur();
        int indexOfAmount = parameters.indexOf("a/");
        int indexOfType = parameters.indexOf("t/");
        double value = Double.parseDouble(parameters.substring(indexOfAmount + 2, indexOfType).trim());
        String type;
        if (recur == 0) {
            type = parameters.substring(indexOfType + 2).trim();
        } else {
            int indexOfRecur = parameters.indexOf("r/");
            type = parameters.substring(indexOfType + 2, indexOfRecur).trim();
        }

        switch (entryType) {
        case INCOME:
            list.addIncome(value, type, recur);
            break;
        case EXPENSE:
            list.addExpense(value, type, recur);
            break;
        default:
            System.out.println("Unidentified entry.");
            break;
        }
    }
}
