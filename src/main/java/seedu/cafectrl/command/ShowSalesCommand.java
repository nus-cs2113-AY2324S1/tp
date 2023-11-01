package seedu.cafectrl.command;

import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Sales;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.ui.ErrorMessages;
import seedu.cafectrl.ui.Ui;

public class ShowSalesCommand extends Command{
    public static final String COMMAND_WORD = "show_sales";
    private Sales sales;
    private Menu menu;

    protected Ui ui;

    public ShowSalesCommand(Sales sales, Ui ui, Menu menu) {
        this.ui = ui;
        this.menu = menu;
        this.sales = sales;
    }

    @Override
    public void execute() {
        sales.printSales(ui, menu);
    }
}
