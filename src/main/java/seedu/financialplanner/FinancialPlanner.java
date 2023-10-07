package seedu.financialplanner;

import seedu.financialplanner.ui.Ui;

public class FinancialPlanner {
    private Ui ui;

    public FinancialPlanner() {
        ui = new Ui();
    }

    public void run() {
        ui.welcomeMessage();
        ui.exitMessage();
    }

    public static void main(String[] args) {
        new FinancialPlanner().run();
    }
}
