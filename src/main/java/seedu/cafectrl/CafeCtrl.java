package seedu.cafectrl;

import seedu.cafectrl.command.Command;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.parser.Parser;
import seedu.cafectrl.storage.MenuStorage;
import seedu.cafectrl.ui.Messages;
import seedu.cafectrl.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * CafeCtrl application's entry point.
 * Initializes the application and starts the interaction with the user.
 */
public class CafeCtrl {
    private final Ui ui;
    private final MenuStorage menuStorage;
    private Menu menu;
    private Command command;
    private Pantry pantry;
    private OrderList orderList;

    /**
     * Private constructor for the CafeCtrl class, used for initializing the user interface and menu list.
     */
    private CafeCtrl(String menuFilePath) {
        ui = new Ui();
        menuStorage = new MenuStorage(menuFilePath, ui);
        pantry = new Pantry(ui);

        ui.showToUser(Messages.INITIALISE_STORAGE_MESSAGE);
        try {
            ArrayList<Dish> dishes = menuStorage.loadData();
            menu = new Menu(dishes);
        } catch (FileNotFoundException e) {
            ui.showToUser(Messages.LOAD_MENU_FILE_ERROR_MESSAGE);
            menu = new Menu();
        }
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
                command = Parser.parseCommand(menu, fullUserInput, ui, pantry, orderList);
                command.execute();
            } catch (Exception e) {
                ui.showToUser(e.getMessage());
            } finally {
                ui.printLine();
            }
        } while (!command.isExit());

        try {
            menuStorage.storeData();
        } catch (IOException e) {
            ui.showToUser(e.getMessage());
        }
    }

    public static void main(String[] args) {
        CafeCtrl cafeCtrl = new CafeCtrl("data/menu.txt");
        cafeCtrl.setup();
        cafeCtrl.run();
    }
}

