package seedu.cafectrl.command;

import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Sales;
import seedu.cafectrl.ui.Ui;

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

    public ShowSalesByDayCommand(int day, Ui ui, Sales sales, Menu menu) {
        this.day = day;
        this.ui = ui;
        this.sales = sales;
        this.menu = menu;
    }

    @Override
    public void execute() {
        sales.printSaleByDay(ui, menu, day);
    }
}
