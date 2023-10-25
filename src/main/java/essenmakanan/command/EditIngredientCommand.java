package essenmakanan.command;

import essenmakanan.exception.EssenMakananFormatException;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.parser.IngredientParser;

public class EditIngredientCommand extends Command {
    private String ingredientDetails;
    private IngredientList ingredients;

    public EditIngredientCommand(String input, IngredientList ingredients)  {
        super();
        this.ingredientDetails = input;
        this.ingredients = ingredients;
    }

    @Override
    public void executeCommand() {
        Ingredient ingredientToEdit;
        Ingredient existingIngredient;

        try{
            ingredientToEdit = IngredientParser.parseIngredient(ingredients, this.ingredientDetails);
            existingIngredient = ingredients.getIngredientByName(ingredientToEdit.getName());

            if (existingIngredient == null) {
                System.out.println("Ingredient not found! Adding it to list");
                ingredients.addIngredient(ingredientToEdit);
            } else {
                ingredients.editIngredient(existingIngredient, ingredientToEdit);
            }
        } catch (EssenMakananFormatException e) {
            e.handleException();
        }
    }
}
