package essenmakanan.parser;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.exception.EssenOutOfRangeException;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.ingredient.IngredientUnit;
import essenmakanan.ui.Ui;

public class IngredientParser {

    /**
     * Checks if the quantity is valid.
     *
     * @param quantity The given quantity.
     * @return Confirmation if the quantity is valid.
     */
    public static boolean checkForValidQuantity(double quantity) {
        return ((int) quantity != 0 && Math.ceil(quantity) > 0) || quantity > 0;
    }

    /**
     * Validates the input of ingredient ID or ingredient name.
     *
     * @param ingredients is the ingredient inventory
     * @param input is the input of ingredient ID or ingredient name
     * @return the index of the ingredient
     * @throws EssenOutOfRangeException when the ingredient name or ingredient ID does not exist
     */
    public static int getIngredientIndex(IngredientList ingredients, String input)
            throws EssenOutOfRangeException {
        int index;
        input = input.replace("i/", "");

        if (input.matches("\\d+")) { //if input only contains numbers
            index = Integer.parseInt(input) - 1;
        } else {
            index = ingredients.getIndex(input);
        }

        if (!ingredients.exist(index)) {
            System.out.println("Your ingredient name or id does not exist or it is invalid.");
            throw new EssenOutOfRangeException();
        }

        return index;
    }

    public static Double getInsufficientQuantity(Ingredient ingredientNeeded, Ingredient ingredientAvailable) {
        final Double zeroQuantity = 0.0;
        
        Double quantityNeeded = ingredientNeeded.getQuantity();
        Double quantityAvailable = ingredientAvailable.getQuantity();

        if (quantityNeeded > quantityAvailable) {
            return (quantityNeeded - quantityAvailable);
        }

        return zeroQuantity;
    }

    public static Ingredient parseIngredient(String inputDetail)
            throws EssenFormatException {

        IngredientUnit ingredientUnit;

        if (!isValidIngredient(inputDetail)) {
            Ui.printValidIngredientExample();
            throw new EssenFormatException();
        }

        inputDetail = inputDetail.replace("i/", "");

        String[] ingredientDetails = inputDetail.split(",");

        assert (ingredientDetails.length == 3) : "Ingredient details should have 3 parts";

        String ingredientName = ingredientDetails[0].trim();
        if (ingredientName.isEmpty()) {
            System.out.println("Ingredient name should not be empty!");
            throw new EssenFormatException();
        }

        if (ingredientDetails[1].isBlank()) {
            // check if quantity is a null
            System.out.println("Ingredient quantity should not be empty!");
            throw new EssenFormatException();
        }

        Double ingredientQuantity = Double.parseDouble(ingredientDetails[1].trim());

        String ingredientUnitString = ingredientDetails[2].trim().toLowerCase();
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

        String ingredientUnitString = ingredientDetails[2].trim().toLowerCase();
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
            System.out.println(Ui.validIngredientUnits());
            throw new EssenFormatException();
        }

        return ingredientUnit;
    }

    /**
     * Converts an ingredient into string form.
     *
     * @param ingredient The ingredient.
     * @return An ingredient that has been converted into string.
     */
    public static String convertToString(Ingredient ingredient) {
        return ingredient.getName() + " | " + ingredient.getQuantity() + " | " + ingredient.getUnit();
    }
}
