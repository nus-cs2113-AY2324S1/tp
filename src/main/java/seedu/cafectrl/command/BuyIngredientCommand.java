package seedu.cafectrl.command;

import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.Ui;

import java.util.ArrayList;

//@@author ShaniceTang
public class BuyIngredientCommand extends Command {
    public static final String COMMAND_WORD = "buy_ingredient";
    public static final String MESSAGE_USAGE = "Command Format:\n"
            + COMMAND_WORD + " ingredient/INGREDIENT1_NAME qty/INGREDIENT1_QTY"
            + "[, ingredient/INGREDIENT2_NAME, qty/INGREDIENT2_QTY...]\n"
            + "(Items in square brackets [] are optional)\n"
            + "Example:\n"
            + COMMAND_WORD + " ingredient/rice qty/200g, ingredient/chicken qty/100g";

    protected Ui ui;
    protected Pantry pantry;
    private ArrayList<Ingredient> ingredients;
    private String ingredientString = "";

    public BuyIngredientCommand(ArrayList<Ingredient> ingredients, Ui ui, Pantry pantry) {
        this.ingredients = ingredients;
        this.ui = ui;
        this.pantry = pantry;
    }

    @Override
    public void execute() {
        try {
            addIngredient();
            ui.showBuyIngredientHeader();
            ui.showToUser(ingredientString);
        } catch (RuntimeException e) {
            ui.showToUser(e.getMessage());
        }
    }

    private void addIngredient() {
        for(int i = 0; i < ingredients.size(); i++) {
            Ingredient ingredient = ingredients.get(i);
            ingredient = pantry.addIngredientToStock(ingredient.getName(),
                    ingredient.getQty(),
                    ingredient.getUnit());
            buildBuyIngredientMessage(ingredient, i);
        }
    }

    private void buildBuyIngredientMessage(Ingredient ingredient, int index) {
        ingredientString += "Ingredient: " + ingredient.getName()
                + "\t\tQty: " + ingredient.getQty()
                + ingredient.getUnit();

        //append new line if current ingredient is not last
        if(index < ingredients.size() - 1) {
            ingredientString += "\n";
        }
    }
}
//@@author ShaniceTang
