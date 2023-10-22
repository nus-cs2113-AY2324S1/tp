package seedu.cafectrl.command;

import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.Ui;

public class BuyIngredientCommand extends Command {

    //command format: buy_ingredient ingredient/[INGREDIENT_NAME] qty/[QUANTITY]
    public static final String COMMAND_WORD = "buy_ingredient";

    private String ingredientName;
    private String ingredientQty;
    private Pantry pantry;


    public BuyIngredientCommand(String name, String qty) {
        this.ingredientName = name;
        this.ingredientQty = qty;
        this.pantry = new Pantry();
    }

    @Override
    public void execute(Menu menu, Ui ui) {
        Ingredient ingredient = pantry.addIngredientToStock(ingredientName, ingredientQty);
        ui.showBuyIngredientMessage(ingredient);
    }
}
