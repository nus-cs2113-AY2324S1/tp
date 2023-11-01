package seedu.cafectrl.command;

import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.Ui;

import java.util.ArrayList;

//@@author ShaniceTang
/**
 * The BuyIngredientCommand class represents a command to buy ingredients and add them to the pantry.
 * It executes the command, adds the ingredients, and displays the results to the user.
 */
public class BuyIngredientCommand extends Command {
    public static final String COMMAND_WORD = "buy_ingredient";
    public static final String MESSAGE_USAGE = "To buy ingredient:\n"
            + COMMAND_WORD + " ingredient/INGREDIENT1_NAME qty/INGREDIENT1_QTY"
            + "[, ingredient/INGREDIENT2_NAME, qty/INGREDIENT2_QTY...]\n"
            + "Example:\n"
            + COMMAND_WORD + " ingredient/rice qty/200g, ingredient/chicken qty/100g";

    protected Ui ui;
    protected Pantry pantry;
    private ArrayList<Ingredient> ingredients;
    private String ingredientString = ""; // Used to store the message about the bought ingredients

    /**
     * Constructs a BuyIngredientCommand with the specified ingredients, user interface, and pantry.
     *
     * @param ingredients The list of ingredients to be bought and added to the pantry.
     * @param ui The user interface to interact with the user.
     * @param pantry The pantry to which the ingredients will be added.
     */
    public BuyIngredientCommand(ArrayList<Ingredient> ingredients, Ui ui, Pantry pantry) {
        this.ingredients = ingredients;
        this.ui = ui;
        this.pantry = pantry;
    }

    /**
     * Executes the command to buy ingredients, adds them to the pantry, and displays the results to the user.
     */
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

    /**
     * Adds the specified ingredients to the pantry.
     * This method is called during command execution.
     */
    private void addIngredient() {
        for(int i = 0; i < ingredients.size(); i++) {
            Ingredient ingredient = ingredients.get(i);
            ingredient = pantry.addIngredientToStock(ingredient.getName(),
                    ingredient.getQty(),
                    ingredient.getUnit());
            buildBuyIngredientMessage(ingredient, i);
        }
    }

    /**
     * Builds a message about the bought ingredient and appends it to the result message.
     *
     * @param ingredient The Ingredient object to build the message for.
     * @param index The index of the ingredient in the list.
     */
    private void buildBuyIngredientMessage(Ingredient ingredient, int index) {
        ingredientString += "Ingredient: " + ingredient.getName()
                + "\t\tQty: " + ingredient.getQty()
                + ingredient.getUnit();

        //append new line if current ingredient is not last
        if(index < ingredients.size() - ui.OFFSET_LIST_INDEX) {
            ingredientString += "\n";
        }
    }
}

