package essenmakanan.parser;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.exception.EssenOutOfRangeException;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.ingredient.IngredientUnit;

public class IngredientParser {
    public static int getIngredientIndex(IngredientList ingredients, String input)
            throws EssenOutOfRangeException {
        int index;
        input = input.replace("i/", "");

        if (input.matches("\\d+")) { //if input only contains numbers
            index = Integer.parseInt(input) - 1;
        } else {
            index = ingredients.getIndexByName(input);
        }

        if (!ingredients.ingredientExist(index)) {
            throw new EssenOutOfRangeException();
        }

        return index;
    }

    public static Ingredient parseIngredient(String inputDetail)
            throws EssenFormatException {

        IngredientUnit ingredientUnit;

        if (!isValidIngredient(inputDetail)) {
            throw new EssenFormatException();
        }

        inputDetail = inputDetail.replace("i/", "");

        String[] ingredientDetails = inputDetail.split(",");

        assert (ingredientDetails.length == 3) : "Ingredient details should have 3 parts";

        String ingredientName = ingredientDetails[0].strip();

        String ingredientQuantity = ingredientDetails[1].strip();

        String ingredientUnitString = ingredientDetails[2].strip().toLowerCase();
        ingredientUnit = mapIngredientUnit(ingredientUnitString);

        Ingredient newIngredient = new Ingredient(ingredientName, ingredientQuantity, ingredientUnit);

        return newIngredient;
    }

    public static boolean isValidIngredient(String inputDetail) {
        inputDetail = inputDetail.replace("i/", "");

        String[] ingredientDetails = inputDetail.split(",");

        if (ingredientDetails.length != 3) {
            return false;
        }

        String ingredientUnitString = ingredientDetails[2].strip().toLowerCase();
        try {
            mapIngredientUnit(ingredientUnitString);
        } catch (EssenFormatException e) {
            return false;
        }

        return true;
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

    public static String convertToString(Ingredient ingredient) {
        return ingredient.getName() + " | " + ingredient.getQuantity() + " | " + ingredient.getUnit();
    }
}
