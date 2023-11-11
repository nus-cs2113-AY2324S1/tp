package seedu.cafectrl.command;

import seedu.cafectrl.CafeCtrl;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.Ui;
import seedu.cafectrl.ui.Messages;

import java.util.ArrayList;
import java.util.logging.Logger;

//@@author ShaniceTang
public class ViewTotalStockCommand extends Command {
    public static final String COMMAND_WORD = "view_stock";
    public static final String MESSAGE_USAGE = "To view pantry stock:\n" + COMMAND_WORD;

    private static Logger logger = Logger.getLogger(CafeCtrl.class.getName());
    protected Ui ui;
    protected Pantry pantry;
    private ArrayList<Ingredient> pantryStock;

    public ViewTotalStockCommand(Pantry pantry, Ui ui) {
        this.pantry = pantry;
        this.ui = ui;
    }

    @Override
    public void execute() {
        logger.info("Executing ViewTotalStockCommand...");
        pantryStock = pantry.getPantryStock();

        if (pantryStock.isEmpty()) {
            ui.showToUser(Messages.EMPTY_STOCK);
            return;
        }

        ui.showIngredientTop();
        for (Ingredient ingredient : pantryStock) {
            ui.showIngredientStock(ingredient.getName(), ingredient.getQty(), ingredient.getUnit());
        }
        ui.showMenuBottom();
    }
}
