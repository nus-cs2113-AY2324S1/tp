package seedu.cafectrl.command;

import seedu.cafectrl.CurrentDate;
import seedu.cafectrl.Sales;
import seedu.cafectrl.data.OrderList;
import seedu.cafectrl.ui.Ui;

public class PreviousDayCommand extends Command{
    public static final String COMMAND_WORD = "previous_day";

    protected Ui ui;
    protected Sales sales;
    protected OrderList orderList;
    protected CurrentDate currentDate;

    public PreviousDayCommand(Ui ui, Sales sales, OrderList orderList, CurrentDate currentDate) {
        this.ui = ui;
        this.sales = sales;
        this.orderList = orderList;
        this.currentDate = currentDate;
    }

    @Override
    public void execute() {
        ui.printLine();
        int currentDay = currentDate.getCurrentDay();
        currentDate.previousDay();
        if (orderList.getSize() == 0) {
            return;
        }
        /*if (currentDay >= sales.getOrderListSize()) {
            System.out.println("Prev: Adding orderList for day " + currentDay);
            sales.addOrderList(orderList);
        } else {*/
            System.out.println("Prev: Setting orderList for day " + currentDay);
            sales.setOrderLists(currentDay, orderList);
        //}
    }
}
