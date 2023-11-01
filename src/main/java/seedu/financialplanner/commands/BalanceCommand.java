package seedu.financialplanner.commands;

import seedu.financialplanner.cashflow.Cashflow;
import seedu.financialplanner.utils.Ui;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Represents a command for displaying balance.
 */
public class BalanceCommand extends Command {
    private final Ui ui = Ui.getInstance();
    
    public BalanceCommand(RawCommand rawCommand) {
        if (!rawCommand.extraArgs.isEmpty()) {
            String unknownExtraArgument = new ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }

    /**
     * Executes the command to display balance.
     */
    @Override
    public void execute() {
        ui.showMessage("Balance: " + getBalanceString());
    }

    private String getBalanceString() {
        DecimalFormat decimalFormat = new DecimalFormat("####0.00");
        return decimalFormat.format(Cashflow.round(Cashflow.getBalance(), 2));
    }
}
