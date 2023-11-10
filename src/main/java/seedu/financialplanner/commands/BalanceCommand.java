package seedu.financialplanner.commands;

import seedu.financialplanner.cashflow.Cashflow;
import seedu.financialplanner.commands.utils.Command;
import seedu.financialplanner.commands.utils.RawCommand;
import seedu.financialplanner.utils.Ui;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Represents a command for displaying balance.
 */
@SuppressWarnings("unused")
public class BalanceCommand extends Command {
    public static final String NAME = "balance";

    public static final String USAGE =
            "balance";
    public static final String EXAMPLE =
            "balance";
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
