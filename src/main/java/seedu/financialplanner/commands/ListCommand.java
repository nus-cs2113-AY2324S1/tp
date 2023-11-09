package seedu.financialplanner.commands;

import seedu.financialplanner.cashflow.Cashflow;
import seedu.financialplanner.cashflow.CashflowList;
import seedu.financialplanner.cashflow.Expense;
import seedu.financialplanner.cashflow.Income;
import seedu.financialplanner.commands.utils.Command;
import seedu.financialplanner.commands.utils.RawCommand;
import seedu.financialplanner.enumerations.CashflowCategory;
import seedu.financialplanner.utils.Ui;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ListCommand extends Command {
    public static final String NAME = "list";

    public static final String USAGE =
            "list [income/expense/recurring]";

    public static final String EXAMPLE =
            "list" + "\n" +
            "list recurring";
    protected CashflowCategory category = null;

    public ListCommand(RawCommand rawCommand) throws IllegalArgumentException {
        String stringCategory = null;
        int indexToDelete = 0;
        ArrayList<Integer> blankArgsList = new ArrayList<>();
        for (String string : rawCommand.args) {
            if (string.isBlank()) {
                Integer toAdd = indexToDelete;
                blankArgsList.add(toAdd);
            }
            indexToDelete++;
        }
        int counter = 0;
        for (Integer integer : blankArgsList) {
            indexToDelete = integer - counter;
            rawCommand.args.remove(indexToDelete);
            counter++;
        }

        if (rawCommand.args.size() == 1) {
            stringCategory = rawCommand.args.get(0);
        } else if (rawCommand.args.size() > 1) {
            throw new IllegalArgumentException("Incorrect arguments.");
        }

        if (stringCategory != null) {
            try {
                category = CashflowCategory.valueOf(stringCategory.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Entry must be either income, expense or recurring.");
            }
        }
    }

    private boolean shouldPrintCashFlow(Cashflow cashflow) {
        if (category == null) {
            return true;
        }
        if (cashflow instanceof Income && category.equals(CashflowCategory.INCOME)) {
            return true;
        }
        if (cashflow instanceof Expense && category.equals(CashflowCategory.EXPENSE)) {
            return true;
        }
        if (cashflow.getRecur() > 0 && !cashflow.getHasRecurred()) {
            return category.equals(CashflowCategory.RECURRING);
        }
        return false;
    }

    @Override
    public void execute() throws Exception {
        Ui ui = Ui.getInstance();

        List<Cashflow> cashflowList = CashflowList.getInstance().list;
        List<Cashflow> cashflowToBePrinted = new ArrayList<>();
        for (Cashflow flow : cashflowList) {
            if (!shouldPrintCashFlow(flow)) {
                continue;
            }
            cashflowToBePrinted.add(flow);
        }

        if (cashflowToBePrinted.isEmpty()) {
            ui.showMessage("No matching cashflow.");
            return;
        }

        ui.showMessage(String.format("You have %d matching cashflows:", cashflowToBePrinted.size()));
        for (int i = 0; i < cashflowToBePrinted.size(); i += 1) {
            ui.showMessage((i + 1) + ": " + cashflowToBePrinted.get(i));
        }
        if (category == null) {
            ui.showMessage("Balance: " + ui.formatBalance(Cashflow.getBalance()));
        } else if (category.equals(CashflowCategory.INCOME)) {
            ui.showMessage("Income Balance: " + ui.formatBalance(Cashflow.getIncomeBalance()));
        } else if (category.equals(CashflowCategory.EXPENSE)) {
            ui.showMessage("Expense Balance: " + ui.formatBalance(Cashflow.getExpenseBalance()));
        }
    }
}
