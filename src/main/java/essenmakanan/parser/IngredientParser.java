package essenmakanan.parser;

import essenmakanan.exception.EssenMakananException;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.ui.Ui;

public class IngredientParser {
    public void parseIngredientCommand(IngredientList ingredients, String command, String inputDetail)
            throws EssenMakananException {
        switch(command) {
        case "add":
            String[] allIngredients = inputDetail.split("/i");

            System.out.println("Ingredient(s) added to your inventory: ");
            for (String ingredient : allIngredients) {
                ingredient = ingredient.strip();
                Ingredient newIngredient = new Ingredient(ingredient);
                ingredients.addIngredient(newIngredient);
            }
            break;
        case "view":
            Ui.printAllIngredients(ingredients);
            break;
        default:
            throw new EssenMakananException("Invalid command! Valid commands are: 'add', 'view'");
        }
    }
}
