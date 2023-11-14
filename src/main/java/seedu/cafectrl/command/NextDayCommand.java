package seedu.cafectrl.command;

import seedu.cafectrl.CafeCtrl;
import seedu.cafectrl.data.CurrentDate;
import seedu.cafectrl.data.Sales;
import seedu.cafectrl.data.OrderList;
import seedu.cafectrl.ui.Ui;

import java.util.logging.Logger;

//@@author Cazh1
public class NextDayCommand extends Command {
    public static final String COMMAND_WORD = "next_day";
    public static final String MESSAGE_USAGE = "To travel to next day:\n"
            + COMMAND_WORD;

    private static Logger logger = Logger.getLogger(CafeCtrl.class.getName());
    private final Ui ui;
    private final Sales sales;
    private final CurrentDate currentDate;

    public NextDayCommand(Ui ui, Sales sales, CurrentDate currentDate) {
        this.ui = ui;
        this.sales = sales;
        this.currentDate = currentDate;
    }

    /**
     * Changes the current day to the next day.
     * Checks if an orderList exist in Sales by comparing
     * the intended Day vs the number of days accounted for in Sales
     *
     * If orderList does not exist, new OrderList is added to Sales
     * The days accounted for in Sales is incremented
     */
    @Override
    public void execute() {
        logger.info("Executing NextDayCommand...");
        ui.printLine();
        currentDate.nextDay();
        int nextDay = currentDate.getCurrentDay();
        if (nextDay > sales.getDaysAccounted()) {
            OrderList newOrderList = new OrderList();
            sales.addOrderList(newOrderList);
            sales.nextDay();
        }
        ui.showNextDay();
        ui.showToUser("Today is Day " + (currentDate.getCurrentDay() + 1));
    }
}
