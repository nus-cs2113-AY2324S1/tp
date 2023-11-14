package seedu.financialplanner.commands;

import seedu.financialplanner.cashflow.CashflowList;
import seedu.financialplanner.commands.utils.Command;
import seedu.financialplanner.commands.utils.RawCommand;
import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.utils.Ui;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class FindCommand extends Command {
    public static final String NAME = "find";

    public static final String USAGE =
            "find <KEYWORD>";
    public static final String EXAMPLE =
            "find buy coffee";
    private final String description;

    /**
     * Constructor of the command to find cashflow.
     *
     * @param rawCommand The input from the user.
     * @throws IllegalArgumentException if erroneous inputs are detected.
     */
    public FindCommand(RawCommand rawCommand) {
        this.description = String.join(" ", rawCommand.args);
        if (!rawCommand.extraArgs.isEmpty()) {
            String unknownExtraArgument = new ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }

    /**
     * Executes the command to find.
     */
    @Override
    public void execute() {
        CashflowList cashflowList = CashflowList.getInstance();
        Ui ui = Ui.getInstance();
        WatchList watchList = WatchList.getInstance();
        ArrayList<String> foundedFinancialList = new ArrayList<>();
        for (int i = 0; i < cashflowList.list.size(); i++) {
            if (cashflowList.list.get(i).toString().toLowerCase().contains(description.toLowerCase())) {
                String output = cashflowList.list.get(i).toString() + " | Index: " + (i + 1);
                foundedFinancialList.add(output);
            }
        }
        if (!foundedFinancialList.isEmpty()) {
            ui.showMessage("Here are the matching financial records in your financial list:");
        } else {
            ui.showMessage("There is no matching financial record in your financial list.");
        }
        for (String foundedFinancialRecord : foundedFinancialList) {
            ui.showMessage(foundedFinancialRecord);
        }

    }
}
