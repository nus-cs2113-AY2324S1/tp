package seedu.cafectrl.command;

import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.Messages;
import seedu.cafectrl.ui.Ui;

import java.util.ArrayList;

public class ViewTotalStockCommand extends Command {

    public static final String COMMAND_WORD = "view_stock";
    private ArrayList<Ingredient> pantryStock;
    private Pantry pantry;

    public ViewTotalStockCommand() {
        this.pantry = new Pantry();
    }

    @Override
    public void execute(Menu menu, Ui ui) {

        ui.showToUser(Messages.VIEW_STOCK);
        pantryStock = pantry.getPantryStock();

        for (Ingredient ingredient : pantryStock) {
            ui.showToUser(ingredient.getName() + " " + ingredient.getQuantity());
        }
    }
}

