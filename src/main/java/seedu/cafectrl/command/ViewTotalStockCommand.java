package seedu.cafectrl.command;

import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.Ui;

import java.util.ArrayList;

public class ViewTotalStockCommand extends Command {

    private ArrayList<Ingredient> pantryStock;
    public static final String COMMAND_WORD = "view_stock";

    private Pantry pantry;

    @Override
    public void execute(Menu menu, Ui ui) {
        pantryStock = pantry.getPantryStock();

        for (Ingredient ingredient : pantryStock) {
            ui.showToUser(ingredient.getName() + " " + ingredient.getQuantity());
        }
    }
}

