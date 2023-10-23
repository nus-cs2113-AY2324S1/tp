package seedu.cafectrl.command;

import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.Ui;

public class BuyIngredientCommand extends Command {

    public static final String COMMAND_WORD = "buy_ingredient";

    private String name;
    private int qty;
    private String unit;
    private Pantry pantry;


    public BuyIngredientCommand(String name, int qty, String unit) {
        this.name = name;
        this.qty = qty;
        this.unit = unit;
        pantry = new Pantry();
    }

    @Override
    public void execute(Menu menu, Ui ui) {
        Ingredient ingredient = pantry.addIngredientToStock(name, qty, unit);
        ui.showBuyIngredientMessage(ingredient);
    }
}
