package essenmakanan.parser;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.exception.EssenOutOfRangeException;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.ingredient.IngredientUnit;

public class IngredientParser {
    public static int getIngredientIndex(IngredientList ingredients, String input)
            throws EssenOutOfRangeException, EssenFormatException {
        int index;
        input = input.replace("i/", "");

        if (input.matches("\\d+")) { //if input only contains numbers
            if (input.length() != 1) {
                throw new EssenFormatException();
            }
            index = Integer.parseInt(input) - 1;
        } else {
            index = ingredients.getIndexByName(input);
        }

        if (!ingredients.ingredientExist(index)) {
            throw new EssenOutOfRangeException();
        }

        return index;
    }

    public static Ingredient parseIngredient(IngredientList ingredients, String inputDetail)
            throws EssenFormatException {

        IngredientUnit ingredientUnit;

        inputDetail = inputDetail.replace("i/", "");

        String[] ingredientDetails = inputDetail.split(",");

        if (ingredientDetails.length != 3) {
            throw new EssenFormatException();
        }

        String ingredientName = ingredientDetails[0].strip();

        String ingredientQuantity = ingredientDetails[1].strip();

        String ingredientUnitString = ingredientDetails[2].strip().toLowerCase();
        ingredientUnit = mapIngredientUnit(ingredientUnitString);

        Ingredient newIngredient = new Ingredient(ingredientName, ingredientQuantity, ingredientUnit);

        return newIngredient;
    }

    public static IngredientUnit mapIngredientUnit(String ingredientUnitString) throws EssenFormatException {
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
            throw new EssenFormatException();
        }

        return ingredientUnit;
    }
}
