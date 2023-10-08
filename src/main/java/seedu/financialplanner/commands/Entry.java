package seedu.financialplanner.commands;

import seedu.financialplanner.list.FinancialList;

public class Entry extends Command{
    private static final String INCOME = "income";
    protected String entryType;
    protected String[] parameters;
    protected FinancialList list;

    public Entry(String entryParameters, FinancialList list) {
        String[] split = entryParameters.split(" ", 2);
        this.entryType = split[0];
        String restOfInput = split[1];
        this.parameters = restOfInput.split(" ");
        this.list = list;
        addEntry();
    }

    private int determineRecur() {
        if (parameters.length == 3) {
            String recur = parameters[2].substring(2);
            return Integer.parseInt(recur);
        }
        return 0;
    }

    private void addEntry() {
        switch (entryType) {
        case INCOME:
            double value = Double.parseDouble(parameters[0].substring(2));
            String type = parameters[1].substring(2);
            int recur = determineRecur();
            list.addIncome(value, type, recur);
            break;
        default:
            System.out.println("Unidentified entry.");
            break;
        }
    }
}
