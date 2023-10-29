package seedu.financialplanner.commands;

import seedu.financialplanner.enumerations.CashflowCategory;
import seedu.financialplanner.cashflow.Cashflow;
import seedu.financialplanner.cashflow.CashflowList;
import seedu.financialplanner.cashflow.Expense;
import seedu.financialplanner.cashflow.Income;
import seedu.financialplanner.utils.Ui;

import java.util.ArrayList;
import java.util.List;

public class ListCommand extends Command {
    protected CashflowCategory category = null;
    public ListCommand(RawCommand rawCommand) throws IllegalArgumentException{
        String stringCategory = null;

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
            ui.showMessage("No matching cashflow");
            return;
        }

        ui.showMessage(String.format("You have %d matching cashflow:", cashflowToBePrinted.size()));
        for (int i = 0; i < cashflowToBePrinted.size(); i += 1) {
            ui.showMessage((i + 1) + ": " + cashflowToBePrinted.get(i));
        }
    }
}
