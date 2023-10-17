package seedu.cafectrl.command;

import seedu.cafectrl.data.Menu;
import seedu.cafectrl.ui.Ui;

/**
 * Edit the price of a dish of a certain index
 */
public class EditPriceCommand extends Command {
    public static final String COMMAND_WORD = "edit price";
    private final int menuID;
    private final float newPrice;

    public EditPriceCommand(int menuID, float newPrice) {
        this.menuID = menuID;
        this.newPrice = newPrice;
    }

    public void execute(Menu menu, Ui ui) {
        menu.getDish(this.menuID).setPrice(this.newPrice);
    }
}
