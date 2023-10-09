package seedu.financialplanner;

import seedu.financialplanner.commands.Command;
import seedu.financialplanner.commands.Exit;
import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.list.FinancialList;
import seedu.financialplanner.utils.Parser;
import seedu.financialplanner.utils.Ui;

public class FinancialPlanner {
    private Ui ui;
    private WatchList watchList;
    private FinancialList financialList;
    public FinancialPlanner() {
        ui = new Ui();
        financialList = new FinancialList();
        watchList = new WatchList();
    }

    public void run() {
        ui.welcomeMessage();
        String input;
        Command command = null;

        while (!(command instanceof Exit)) {
            input = ui.input();
            command = Parser.parse(input);
            command.execute(ui, financialList, watchList);
        }
        ui.exitMessage();
    }

    public static void main(String[] args) {
        new FinancialPlanner().run();
    }
}
