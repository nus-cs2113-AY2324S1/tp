package seedu.financialplanner.commands;

import seedu.financialplanner.enumerations.CashflowCategory;
import seedu.financialplanner.list.CashflowList;
import seedu.financialplanner.utils.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteCashflowCommand extends AbstractCommand{
    private static Logger logger = Logger.getLogger("Financial Planner Logger");
    protected CashflowCategory category = CashflowCategory.EMPTY;
    protected int index;

    public DeleteCashflowCommand(RawCommand rawCommand) throws IllegalArgumentException {
        String stringIndex;
        String stringCategory;

        if (rawCommand.args.size() == 1) {
            stringIndex = rawCommand.args.get(0);
        } else {
            stringCategory = rawCommand.args.get(0);

            handleInvalidCategory(stringCategory);

            stringIndex = rawCommand.args.get(1);
        }

        try {
            index = Integer.parseInt(stringIndex);
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, "Invalid argument for index");
            throw new IllegalArgumentException("Index must be an integer");
        }

        if (index == 0) {
            logger.log(Level.WARNING, "Invalid value for index");
            throw new IllegalArgumentException("Index must be within the list");
        }
    }

    private void handleInvalidCategory(String stringCategory) {
        try {
            category = CashflowCategory.valueOf(stringCategory.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, "Invalid arguments for CashflowCategory");
            throw new IllegalArgumentException("Entry must be either income or expense");
        }
    }

    @Override
    public void execute() {
        assert category.equals(CashflowCategory.INCOME) || category.equals(CashflowCategory.EXPENSE);
        assert index != 0;

        switch (category) {
        case INCOME:
        case EXPENSE:
            handleDeleteCashflowWithCategory();
            break;
        case EMPTY:
            handleDeleteCashflowWithoutCategory();
            break;
        default:
            logger.log(Level.SEVERE, "Unreachable default case reached");
            Ui.INSTANCE.showMessage("Unidentified entry.");
            break;
        }
    }

    private void handleDeleteCashflowWithoutCategory() {
        try {
            CashflowList.INSTANCE.delete(index);
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Index out of list");
            throw new IllegalArgumentException("Index must be within the list");
        }
    }

    private void handleDeleteCashflowWithCategory() {
        try {
            CashflowList.INSTANCE.deleteCashflow(category, index);
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Index out of list");
            throw new IllegalArgumentException("Index must be within the list");
        }
    }
}
