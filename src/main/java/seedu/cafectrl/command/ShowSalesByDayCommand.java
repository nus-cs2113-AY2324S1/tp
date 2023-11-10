package seedu.cafectrl.command;

import seedu.cafectrl.CafeCtrl;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Sales;
import seedu.cafectrl.ui.Ui;

import java.util.logging.Logger;

//@@author NaychiMin
public class ShowSalesByDayCommand extends Command {
    public static final String COMMAND_WORD = "show_sale";
    public static final String MESSAGE_USAGE = "To show sales for a chosen day:\n"
            + COMMAND_WORD + " day/DAY_TO_DISPLAY\n"
            + "Example: " + COMMAND_WORD + " day/1";

    private final int day;
    private final Ui ui;
    private final Sales sales;
    private final Menu menu;
    private static Logger logger = Logger.getLogger(CafeCtrl.class.getName());

    public ShowSalesByDayCommand(int day, Ui ui, Sales sales, Menu menu) {
        this.day = day;
        this.ui = ui;
        this.sales = sales;
        this.menu = menu;
    }

    @Override
    public void execute() {
        logger.info("Excecuting ShowSalesByDayCommand...");
        sales.printSaleByDay(ui, menu, day);
    }
}
