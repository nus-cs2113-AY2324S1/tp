package seedu.cafectrl.command;

import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.Ui;

public class BuyIngredientCommand extends Command {

    public static final String COMMAND_WORD = "buy_ingredient";
    protected Ui ui;
    protected Pantry pantry;

    private String name;
    private int qty;
    private String unit;


    public BuyIngredientCommand(String name, int qty, String unit, Ui ui, Pantry pantry) {
        this.name = name;
        this.qty = qty;
        this.unit = unit;
        this.ui = ui;
        this.pantry = pantry;
    }

    @Override
    public void execute() {
        Ingredient ingredient = pantry.addIngredientToStock(name, qty, unit);
        ui.showBuyIngredientMessage(ingredient);
    }
}
