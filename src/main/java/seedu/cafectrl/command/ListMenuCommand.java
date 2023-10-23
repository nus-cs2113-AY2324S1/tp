package seedu.cafectrl.command;

import seedu.cafectrl.data.Menu;
import seedu.cafectrl.ui.Messages;
import seedu.cafectrl.ui.Ui;

import java.text.DecimalFormat;

/**
 * Lists all dishes in the menu to the user.
 */
public class ListMenuCommand extends Command {
    public static final String COMMAND_WORD = "list_menu";
    private static final DecimalFormat dollarValue = new DecimalFormat("0.00");

    protected Menu menu;
    protected Ui ui;

    public ListMenuCommand(Menu menu, Ui ui) {
        this.menu = menu;
        this.ui = ui;
    }

    /**
     * Iterates through the menu arraylist, outputting the dish name and dish price.
     */
    @Override
    public void execute() {
        if (menu.getSize() == 0) {
            printEmptyMenu(ui);
            return;
        }
        printFullMenu(menu, ui);
    };

    public void printEmptyMenu(Ui ui) {
        ui.showToUser(Messages.MENU_EMPTY_MESSAGE);
    }

    public void printFullMenu(Menu menu, Ui ui) {
        ui.showToUser(Messages.MENU_TOP, Messages.LIST_MENU_MESSAGE,
                Messages.MENU_CORNER, Messages.MENU_TITLE, Messages.MENU_CORNER);
        for (int i = 0; i < menu.getSize(); i++) {
            String indexNum = String.valueOf(i + 1);
            String dishName = menu.getDish(i).getName();
            String dishPrice = dollarValue.format(menu.getDish(i).getPrice());
            ui.formatListMenu(indexNum + ". " + dishName," $" + dishPrice);
        }
        ui.showToUser(Messages.MENU_TOP);
    }
}
