package essenmakanan.command;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;

import java.util.Arrays;

public class EditIngredientCommand extends Command {
    private String editDetails;
    private IngredientList ingredients;

    public EditIngredientCommand(String input, IngredientList ingredients)  {
        super();
        this.editDetails = input;
        this.ingredients = ingredients;
    }

    /**
     * Check if command and its contents are valid. Parse a valid Ingredient and edit the ingredient in the list
     */
    @Override
    public void executeCommand() {
        Ingredient existingIngredient;

        this.editDetails = this.editDetails.replace("i/", "");

        String[] splitDetails = this.editDetails.split(" ");
        String ingredientName = splitDetails[0];
        String[] ingredientEditDetails = Arrays.copyOfRange(splitDetails, 1, splitDetails.length);

        existingIngredient = ingredients.getIngredient(ingredientName);

        assert existingIngredient.getName().equals(ingredientName)
                : "Selected ingredient does not have matching name.";

        if (existingIngredient == null) {
            System.out.println("Ingredient not found!");
        } else {
            try {
                ingredients.editIngredient(existingIngredient, ingredientEditDetails);
            } catch (EssenFormatException e) {
                e.handleException();
            }
        }

    }
}
