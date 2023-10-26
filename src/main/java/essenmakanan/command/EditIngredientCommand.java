package essenmakanan.command;

import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;

public class EditIngredientCommand extends Command {
    private String editDetails;
    private IngredientList ingredients;

    public EditIngredientCommand(String input, IngredientList ingredients)  {
        super();
        this.editDetails = input;
        this.ingredients = ingredients;
    }

    @Override
    public void executeCommand() {
        Ingredient existingIngredient;

        this.editDetails = this.editDetails.replace("i/", "");
        // INGREDIENT_NAME [n/NEW_NAME]
        String[] splitDetails = this.editDetails.split(" ");
        String ingredientName = splitDetails[0];

        existingIngredient = ingredients.getIngredientByName(ingredientName);

        if (existingIngredient == null) {
            System.out.println("Ingredient not found!");
        } else {
            ingredients.editIngredient(existingIngredient, splitDetails);
        }

    }
}
