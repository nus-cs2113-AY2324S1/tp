package seedu.financialplanner;

import seedu.financialplanner.commands.Command;
import seedu.financialplanner.commands.Exit;
import seedu.financialplanner.utils.Parser;
import seedu.financialplanner.utils.Ui;

public class FinancialPlanner {
    private Ui ui;

    public FinancialPlanner() {
        ui = new Ui();
    }

    public void run() {
        ui.welcomeMessage();
        String input;
        Command command = null;

        while (!(command instanceof Exit)) {
            input = ui.input();
            command = Parser.parse(input);
            command.execute(ui);
        }
        ui.exitMessage();
    }

    public static void main(String[] args) {
        new FinancialPlanner().run();
    }
}
