package seedu.financialplanner.commands;

import seedu.financialplanner.enumerations.CashflowCategory;
import seedu.financialplanner.list.CashflowList;
import seedu.financialplanner.utils.Ui;

public class DeleteCashflowCommand extends AbstractCommand{

    protected CashflowCategory category = null;
    protected int index;

    public DeleteCashflowCommand(RawCommand rawCommand) throws IllegalArgumentException {
        String stringIndex;
        String stringCategory = null;

        if (rawCommand.args.size() == 1) {
            stringIndex = rawCommand.args.get(0);
        } else if (rawCommand.args.size() == 2) {
            stringCategory = rawCommand.args.get(0);
            stringIndex = rawCommand.args.get(1);
        } else {
            throw new IllegalArgumentException("Incorrect arguments.");
        }

        try {
            index = Integer.parseInt(stringIndex);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Index must be an integer");
        }

        if (stringCategory != null) {
            try {
                category = CashflowCategory.valueOf(stringCategory.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Entry must be either income or expense");
            }
        }
    }

    @Override
    public void execute() {
        if (category == null) {
            handleDeleteCashflowWithoutCategory();
            return;
        }
        switch (category) {
        case INCOME:
        case EXPENSE:
            handleDeleteCashflowWithCategory();
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
