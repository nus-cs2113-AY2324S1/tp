package seedu.cafectrl.command;

import seedu.cafectrl.CafeCtrl;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Sales;
import seedu.cafectrl.ui.Ui;

import java.util.logging.Logger;

public class ListTotalSales extends Command {
    public static final String COMMAND_WORD = "list_total_sales";
    public static final String MESSAGE_USAGE = "To show sales for all days:\n" + COMMAND_WORD;

    private static Logger logger = Logger.getLogger(CafeCtrl.class.getName());
    private Sales sales;
    private Ui ui;
    private Menu menu;

    public ListTotalSales(Sales sales, Ui ui, Menu menu) {
        this.sales = sales;
        this.ui = ui;
        this.menu = menu;
    }

    @Override
    public void execute() {
        logger.info("Executing ShowSalesCommand...");
        sales.printSales(ui, menu);
    }
}
