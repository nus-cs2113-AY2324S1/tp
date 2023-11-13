package seedu.cafectrl;

import seedu.cafectrl.command.Command;
import seedu.cafectrl.data.CurrentDate;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.Sales;
import seedu.cafectrl.parser.Parser;
import seedu.cafectrl.parser.ParserUtil;
import seedu.cafectrl.storage.Storage;
import seedu.cafectrl.ui.Ui;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;


/**
 * CafeCtrl application's entry point.
 * Initializes the application and starts the interaction with the user.
 */
public class CafeCtrl {

    private static final Logger logger = Logger.getLogger(CafeCtrl.class.getName());
    private final Ui ui;
    private final Storage storage;
    private final Pantry pantry;
    private final Menu menu;
    private final Sales sales;
    private final CurrentDate currentDate;

    private Command command;

    /**
     * Private constructor for the CafeCtrl class, used for initializing the user interface and menu list.
     */
    private CafeCtrl() {
        initLogger();
        this.ui = new Ui();
        this.storage = new Storage(ui);
        this.pantry = this.storage.loadPantryStock();
        this.menu = this.storage.loadMenu();
        this.sales = this.storage.loadOrderList(menu);
        this.currentDate = new CurrentDate(sales);

        logger.info( "CafeCtrl initialised successfully");
        assert sales.getOrderLists().size() == currentDate.getCurrentDay() + 1;
    }
    
    /**
     * The main loop of the CafeCtrl application.
     *
     * <p> This method consistently receives user input, parses commands, and executes the respective command
     * until the user enters a "bye" command, terminating the application.</p>
     */
    private void run() {
        ui.showWelcome();
        ui.printLine();

        do {
            try {
                String fullUserInput = ui.receiveUserInput();
                ParserUtil parserUtil = new Parser();
                command = parserUtil.parseCommand(menu, fullUserInput, ui, pantry, sales, currentDate);
                command.execute();
                logger.info(command.getClass().getName() + " executed.");
            } catch (Exception e) {
                logger.log(Level.WARNING, "Error executing command: " + e.getMessage(), e);
                ui.showToUser(e.getMessage());
            } finally {
                ui.printLine();
            }
        } while (!command.isExit());

        storage.saveAll(menu, sales, pantry);
        logger.info("CafeCtrl terminated.");
    }

    private void initLogger() {
        logger.setUseParentHandlers(false);
        try {
            FileHandler fileHandler = new FileHandler("cafeCtrl.log");
            SimpleFormatter formatter = new SimpleFormatter();

            logger.addHandler(fileHandler);
            fileHandler.setFormatter(formatter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CafeCtrl cafeCtrl = new CafeCtrl();
        cafeCtrl.run();
    }
}
