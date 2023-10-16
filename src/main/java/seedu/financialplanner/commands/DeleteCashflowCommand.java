package seedu.financialplanner.commands;

import seedu.financialplanner.enumerations.CashflowCategory;
import seedu.financialplanner.list.CashflowList;
import seedu.financialplanner.utils.Ui;

public class DeleteCashflowCommand extends AbstractCommand{

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
            throw new IllegalArgumentException("Index must be an integer");
        }
    }

    private void handleInvalidCategory(String stringCategory) {
        try {
            category = CashflowCategory.valueOf(stringCategory.toUpperCase());
        } catch (IllegalArgumentException e) {
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
            Ui.INSTANCE.showMessage("Unidentified entry.");
            break;
        }
    }

    private void handleDeleteCashflowWithoutCategory() {
        try {
            CashflowList.INSTANCE.delete(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Index must be within the list.");
        }
    }

    private void handleDeleteCashflowWithCategory() {
        try {
            CashflowList.INSTANCE.deleteCashflow(category, index);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Index must be within the list.");
        }
    }
}
