package seedu.cafectrl.command;

import seedu.cafectrl.CurrentDate;
import seedu.cafectrl.Sales;
import seedu.cafectrl.data.OrderList;
import seedu.cafectrl.ui.Ui;

public class NextDayCommand extends Command {

    public static final String COMMAND_WORD = "next_day";

    protected Ui ui;
    protected Sales sales;
    protected OrderList orderList;
    protected CurrentDate currentDate;

    public NextDayCommand(Ui ui, Sales sales, OrderList orderList, CurrentDate currentDate) {
        this.ui = ui;
        this.sales = sales;
        this.orderList = orderList;
        this.currentDate = currentDate;
    }

    @Override
    public void execute() {
        ui.printLine();
        int currentDay = currentDate.getCurrentDay();
        currentDate.nextDay();
        int nextDay = currentDate.getCurrentDay();
        if (nextDay >= sales.getOrderListSize()) {
            System.out.println("Set new orderlist");
            sales.setNewOrderList();
        }
        System.out.println("Setting orderList for " + currentDay);
        sales.setOrderLists(currentDay, orderList);

    }
}
