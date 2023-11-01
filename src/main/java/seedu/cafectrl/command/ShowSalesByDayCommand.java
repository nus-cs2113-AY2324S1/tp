package seedu.cafectrl.command;

import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Sales;
import seedu.cafectrl.ui.Ui;

public class ShowSalesByDayCommand extends Command{
    public static final String COMMAND_WORD = "show_sale";

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
