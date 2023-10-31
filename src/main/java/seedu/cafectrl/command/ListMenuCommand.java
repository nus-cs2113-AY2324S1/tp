package seedu.cafectrl.command;

import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.ui.Ui;

import java.text.DecimalFormat;

/**
 * Lists all dishes in the menu to the user.
 */
public class ListMenuCommand extends Command {
    public static final String COMMAND_WORD = "list_menu";
    public static final String MESSAGE_USAGE = "Command Format:\n"
            + COMMAND_WORD;
    private static final DecimalFormat dollarValue = new DecimalFormat("0.00");

    protected Menu menu;
    protected Ui ui;

    /**
     * Constructor for the ListMenuCommand
     *
     * @param menu The menu object of the current session
     * @param ui The ui object created that handles I/O with the user
     */
    public ListMenuCommand(Menu menu, Ui ui) {
        this.menu = menu;
        this.ui = ui;
    }

    /**
     * Iterates through the menu arraylist, outputting the dish name and dish price.
     * Calls printEmptyMenu() when (menu.getSize() == 0), printFullMenu() otherwise.
     */
    @Override
    public void execute() {
        if (menu.getSize() == 0) {
            printEmptyMenu(ui);
        } else {
            printFullMenu(menu, ui);
        }
    };

    /**
     * Shows empty menu message to user
     * Called only when the menu is empty
     *
     * @param ui The ui object created that handles I/O with the user
     */
    public void printEmptyMenu(Ui ui) {
        ui.showEmptyMenu();
    }

    /**
     * Prints the dishes in the menu
     * Called only when the menu is not empty
     *
     * @param menu The menu object of the current session
     * @param ui The ui object created that handles I/O with the user
     */
    public void printFullMenu(Menu menu, Ui ui) {
        ui.showMenuTop();
        for (int i = 0; i < menu.getSize(); i++) {
            String indexNum = String.valueOf(i + 1);
            Dish selectedDish = menu.getDishFromId(i);
            String dishName = selectedDish.getName();
            String dishPrice = dollarValue.format(selectedDish.getPrice());
            ui.showMenuDish(indexNum, dishName, dishPrice);
        }
        ui.showMenuBottom();
    }
}
