package seedu.cafectrl;

import seedu.cafectrl.command.Command;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.OrderList;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.parser.Parser;
import seedu.cafectrl.ui.Ui;

/**
 * CafeCtrl application's entry point.
 * Initializes the application and starts the interaction with the user.
 */
public class CafeCtrl {
    private final Ui ui;
    private final Menu menu;
    private Command command;
    private Pantry pantry;
    private OrderList orderList;
    private Sales sales;
    private CurrentDate currentDate;

    /**
     * Private constructor for the CafeCtrl class, used for initializing the user interface and menu list.
     */
    private CafeCtrl() {
        ui = new Ui();
        menu = new Menu();
        pantry = new Pantry(ui);
        currentDate = new CurrentDate();
        sales = new Sales();
    }

    private void setup() {
        ui.showWelcome();
    }
    
    /**
     * The main loop of the CafeCtrl application.
     *
     * <p> This method consistently receives user input, parses commands, and executes the respective command
     * until the user enters a "bye" command, terminating the application.</p>
     */
    private void run() {
        ui.printLine();
        do {
            try {
                String fullUserInput = ui.receiveUserInput();
                command = Parser.parseCommand(menu, fullUserInput, ui, pantry, orderList, sales, currentDate);
                command.execute();
                orderList = setOrderList();
                orderList.printOrderList();
            } catch (Exception e) {
                ui.showToUser(e.getMessage());
            } finally {
                ui.printLine();
            }
        } while (!command.isExit());
    }

    public static void main(String[] args) {
        CafeCtrl cafeCtrl = new CafeCtrl();
        cafeCtrl.setup();
        cafeCtrl.run();
    }

    public OrderList setOrderList() {
        int currentDay = currentDate.getCurrentDay();
        System.out.println("Set current orderList to day: " + currentDay);
        //orderList.printOrderList();
        if (sales.isNewOrderList()) {
            sales.resetNewOrderList();
            OrderList newOrderList = new OrderList();
            sales.addOrderList(newOrderList);
            return newOrderList;
        }
        return sales.getOrderList(currentDay);
    }
}

