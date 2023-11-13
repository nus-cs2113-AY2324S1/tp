package seedu.cafectrl.command;

import seedu.cafectrl.CafeCtrl;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.Ui;
import seedu.cafectrl.parser.Parser;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.cafectrl.ui.Ui.OFFSET_LIST_INDEX;

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
            + "Example:"
            + COMMAND_WORD + " ingredient/milk qty/200ml, ingredient/chicken qty/100g";
    private static Logger logger = Logger.getLogger(CafeCtrl.class.getName());

    protected Ui ui;
    protected Pantry pantry;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Ingredient> ingredientsToBePrinted = new ArrayList<>();
    private String ingredientString = ""; // Used to store the message about the bought ingredients
    private int finalIndex = 0;

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
        logger.info("Executing BuyIngredientCommand...");
        try {
            addIngredient();
            ui.printBuyIngredientHeader();
            ui.showToUser(ingredientString.strip());
        } catch (Exception e) {
            logger.log(Level.WARNING, "BuyIngredientCommand unsuccessful: " + e.getMessage(), e);
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
            ingredients.set(i, ingredient);
        }

        for (int i = ingredients.size() - OFFSET_LIST_INDEX; i >= finalIndex; i--) {
            Ingredient ingredient = ingredients.get(i);
            buildBuyIngredientMessage(ingredient);
        }
    }

    /**
     * Builds a message about the bought ingredient and appends it to the result message.
     *
     * @param ingredient The Ingredient object to build the message for.
     */
    private void buildBuyIngredientMessage(Ingredient ingredient) {
        if (Parser.isRepeatedIngredientName(ingredient.getName(), ingredientsToBePrinted)) {
            return;
        }
        ingredientsToBePrinted.add(ingredient);
        ingredientString += "Ingredient: " + ingredient.getName()
                + "\nQty: " + ingredient.getQty()
                + ingredient.getUnit() + "\n\n";
    }
}

