package seedu.cafectrl.command;

import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.Ui;

import java.util.ArrayList;

public class BuyIngredientCommand extends Command {

    public static final String COMMAND_WORD = "buy_ingredient";
    protected Ui ui;
    protected Pantry pantry;
    private ArrayList<Ingredient> ingredients;


    public BuyIngredientCommand(ArrayList<Ingredient> ingredients, Ui ui, Pantry pantry) {
        this.ingredients = ingredients;
        this.ui = ui;
        this.pantry = pantry;
    }

    @Override
    public void execute() {
        ui.showBuyIngredientHeader();
        for (Ingredient ingredient : ingredients) {
            ingredient = pantry.addIngredientToStock(ingredient.getName(),
                                                        ingredient.getQty(),
                                                        ingredient.getUnit());
            ui.showBuyIngredientMessage(ingredient);
        }
    }
}
