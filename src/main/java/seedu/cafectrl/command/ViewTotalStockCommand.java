package seedu.cafectrl.command;

import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.Ui;

import java.util.ArrayList;

public class ViewTotalStockCommand extends Command {

    public static final String COMMAND_WORD = "view_stock";
    public static final String MESSAGE_USAGE = "To view pantry stock:\n" + COMMAND_WORD;
    protected Ui ui;
    protected Pantry pantry;
    private ArrayList<Ingredient> pantryStock;

    public ViewTotalStockCommand(Pantry pantry, Ui ui) {
        this.pantry = pantry;
        this.ui = ui;
    }

    @Override
    public void execute() {
        ui.showIngredientTop();
        pantryStock = pantry.getPantryStock();

        for (Ingredient ingredient : pantryStock) {
            ui.showIngredientStock(ingredient.getName(), ingredient.getQty(), ingredient.getUnit());
        }
        ui.showMenuBottom();
    }
}
