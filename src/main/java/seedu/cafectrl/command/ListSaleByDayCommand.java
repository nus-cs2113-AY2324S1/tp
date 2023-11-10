package seedu.cafectrl.command;

import seedu.cafectrl.CafeCtrl;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Sales;
import seedu.cafectrl.ui.ErrorMessages;
import seedu.cafectrl.ui.Ui;

import java.util.logging.Logger;

//@@author NaychiMin
public class ListSaleByDayCommand extends Command {
    public static final String COMMAND_WORD = "list_sale";
    public static final String MESSAGE_USAGE = "To show sales for a chosen day:\n"
            + COMMAND_WORD + " day/DAY_TO_DISPLAY\n"
            + "Example: " + COMMAND_WORD + " day/1";

    private static Logger logger = Logger.getLogger(CafeCtrl.class.getName());
    private final int day;
    private final Ui ui;
    private final Sales sales;
    private final Menu menu;

    public ListSaleByDayCommand(int day, Ui ui, Sales sales, Menu menu) {
        this.day = day;
        this.ui = ui;
        this.sales = sales;
        this.menu = menu;
    }

    @Override
    public void execute() {
        logger.info("Executing ShowSalesByDayCommand...");
        try {
            sales.printSaleByDay(ui, menu, day);
        } catch (Exception e) {
            ui.showToUser(ErrorMessages.INVALID_SALE_DAY);
        }
    }
}
