package essenmakanan.parser;

import essenmakanan.exception.EssenMakananFormatException;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.ingredient.IngredientUnit;

public class IngredientParser {
    public static int getIngredientId(String input) {
        if (input.matches("\\d+")) { //if input only contains numbers
            return Integer.parseInt(input);
        } else {

        }
    }
    public static Ingredient parseIngredient(IngredientList ingredients, String inputDetail)
            throws EssenMakananFormatException {

        IngredientUnit ingredientUnit;

        String[] ingredientDetails = inputDetail.split(",");

        if (ingredientDetails.length != 3) {
            throw new EssenMakananFormatException();
        }

        String ingredientName = ingredientDetails[0].strip();

        String ingredientQuantity = ingredientDetails[1].strip();

        String ingredientUnitString = ingredientDetails[2].strip().toLowerCase();
        ingredientUnit = mapIngredientUnit(ingredientUnitString);

        Ingredient newIngredient = new Ingredient(ingredientName, ingredientQuantity, ingredientUnit);

        return newIngredient;
    }

    public static IngredientUnit mapIngredientUnit(String ingredientUnitString) throws EssenMakananFormatException {
        IngredientUnit ingredientUnit;
        // return("Valid ingredient units are: g, kg, ml, l, tsp, tbsp, cup, pcs");
        switch(ingredientUnitString) {
        case "g":
            ingredientUnit = IngredientUnit.GRAM;
            break;
        case "kg":
            ingredientUnit = IngredientUnit.KILOGRAM;
            break;
        case "ml":
            ingredientUnit = IngredientUnit.MILLILITER;
            break;
        case "l":
            ingredientUnit = IngredientUnit.LITER;
            break;
        case "tsp":
            ingredientUnit = IngredientUnit.TEASPOON;
            break;
        case "tbsp":
            ingredientUnit = IngredientUnit.TABLESPOON;
            break;
        case "cup":
            ingredientUnit = IngredientUnit.CUP;
            break;
        case "pc":
            ingredientUnit = IngredientUnit.PIECE;
            break;
        default:
            throw new EssenMakananFormatException();
        }

        return ingredientUnit;
    }
}
