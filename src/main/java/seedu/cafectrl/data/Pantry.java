package seedu.cafectrl.data;

import seedu.cafectrl.data.dish.Ingredient;

import java.util.ArrayList;

/**
 * Represents a pantry that stores ingredients and manages their quantities.
 */
public class Pantry {

    private ArrayList<Ingredient> pantryStock;

    /**
     * Retrieves the current pantry stock from storage, which may include reading from a file (pantry.txt).
     * @return An ArrayList of Ingredient objects representing the current pantry stock.
     */
    public ArrayList<Ingredient> getPantryStock() {
        pantryStock.addAll(retrieveStockFromStorage());
        return pantryStock;
    }

    /**
     * Retrieves the pantry stock from storage, e.g., by reading from a file (pantry.txt).
     * @return An ArrayList of Ingredient objects representing the pantry stock.
     */
    public ArrayList<Ingredient> retrieveStockFromStorage () {
        ArrayList<Ingredient> pantryStock = new ArrayList<>();
        //TODO: Add file reader to read from pantry.txt
        return pantryStock;
    }

    /**
     * Extracts the quantity (numeric part) from a given string containing both quantity and unit.
     * @param qty A string containing both quantity and unit (e.g., "100g").
     * @return An integer representing the extracted quantity.
     */
    public int extractQty(String qty) {
        return Integer.parseInt(qty.replaceAll("[^0-9]", ""));
    }

    /**
     * Extracts the unit (non-numeric part) from a given string containing both quantity and unit.
     * @param qty A string containing both quantity and unit (e.g., "100g").
     * @return A string representing the extracted unit.
     */
    public String extractUnit(String qty) {
        return qty.replaceAll("[0-9]", "");
    }

    /**
     * Adds or updates an ingredient in the pantry stock based on its name and quantity.
     * @param name The name of the ingredient to add or update.
     * @param qty The quantity of the ingredient (e.g., "100g").
     * @return The Ingredient object that was added or updated in the pantry stock.
     */
    public Ingredient addIngredientToStock (String name, String qty) {
        pantryStock = getPantryStock(); //get latest pantry stock
        int ingredientIndex = -1;

        //checks if ingredient exists in the pantry
        ingredientIndex = getIndexOfIngredient(name);

        //TODO: Add file writer to write update pantry.txt
        return updateIngredient(name, qty, ingredientIndex);
    }

    /**
     * Updates an ingredient's quantity in the pantry stock or adds a new ingredient if it doesn't exist.
     * @param name The name of the ingredient.
     * @param qty The quantity of the ingredient (e.g., "100g").
     * @param ingredientIndex The index of the ingredient in the pantry stock (-1 if not found).
     * @return The Ingredient object that was added or updated in the pantry stock.
     */
    private Ingredient updateIngredient(String name, String qty, int ingredientIndex) {
        Ingredient ingredient;
        if (ingredientIndex != -1) { //ingredient is in pantry
            ingredient = pantryStock.get(ingredientIndex);
            qty = updateQty(ingredientIndex, qty);
            ingredient.setQuantity(qty);
        } else {
            ingredient = new Ingredient(name, qty);
            pantryStock.add(ingredient);
        }
        return ingredient;
    }

    /**
     * Updates the quantity of an ingredient in the pantry stock.
     * @param ingredientIndex The index of the ingredient in the pantry stock.
     * @param qtyToAdd The quantity to add to the existing quantity (e.g., "100g").
     * @return The updated quantity string (e.g., "200g").
     */
    private String updateQty(int ingredientIndex, String qtyToAdd) {
        String unit = extractUnit(qtyToAdd); //remove numbers

        String qtyToUpdate = pantryStock.get(ingredientIndex).getQuantity();

        int intQtyToAdd = extractQty(qtyToAdd);
        int intQtyToUpdate = extractQty(qtyToUpdate);

        intQtyToUpdate += intQtyToAdd;
        return intQtyToUpdate + unit;
    }

    /**
     * Gets the index of an ingredient in the pantry stock based on its name (case-insensitive comparison).
     * @param name The name of the ingredient to search for.
     * @return The index of the ingredient in the pantry stock or -1 if not found.
     */
    private int getIndexOfIngredient(String name) {
        for (int i = 0; i < pantryStock.size(); i++) {
            if (name.equalsIgnoreCase(pantryStock.get(i).getName().trim())) {
                return i;
            }
        }
        return -1;
    }
}
