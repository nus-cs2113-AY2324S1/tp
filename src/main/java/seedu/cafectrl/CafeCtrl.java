package seedu.cafectrl;

import seedu.cafectrl.command.Command;
import seedu.cafectrl.data.*;
import seedu.cafectrl.parser.Parser;
import seedu.cafectrl.storage.Storage;
import seedu.cafectrl.ui.Messages;
import seedu.cafectrl.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * CafeCtrl application's entry point.
 * Initializes the application and starts the interaction with the user.
 */
public class CafeCtrl {
    private final Ui ui;
    private Menu menu;
    private Command command;
    private Pantry pantry;
    private OrderList orderList;
    private Sales sales;
    private CurrentDate currentDate;
    private Storage storage;

    /**
     * Private constructor for the CafeCtrl class, used for initializing the user interface and menu list.
     */
    private CafeCtrl() throws FileNotFoundException {
        this.ui = new Ui();
        this.ui.showToUser(Messages.INITIALISE_STORAGE_MESSAGE);
        this.storage = new Storage(this.ui);
        this.menu = this.storage.loadMenu();
        this.pantry = this.storage.loadPantryStock();
        //this.orderList = this.storage.loadOrderList();
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
    private void run() throws IOException {
        ui.printLine();
        do {
            try {
                String fullUserInput = ui.receiveUserInput();
                command = Parser.parseCommand(menu, fullUserInput, ui, pantry, orderList, sales, currentDate);
                command.execute();
                orderList = setOrderList();
                orderList.printOrderList();
                System.out.println("Overall Earnings: $" + sales.getTotalSales());
            } catch (Exception e) {
                ui.showToUser(e.getMessage());
            } finally {
                ui.printLine();
            }
        } while (!command.isExit());
        //this.storage.saveAll(this.menu, this.orderList, this.pantry);
    }

    public static void main(String[] args) throws IOException {
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

