package seedu.cafectrl.command;

import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Sales;
import seedu.cafectrl.ui.Ui;

public class ShowSalesCommand extends Command {
    public static final String COMMAND_WORD = "show_sales";
    private Sales sales;
    private Ui ui;
    private Menu menu;

    public ShowSalesCommand(Sales sales, Ui ui, Menu menu) {
        this.sales = sales;
        this.ui = ui;
        this.menu = menu;
    }

    @Override
    public void execute() {
        sales.printSales(ui, menu);
    }
}
