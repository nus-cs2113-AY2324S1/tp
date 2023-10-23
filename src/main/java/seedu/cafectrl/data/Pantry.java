package seedu.cafectrl.data;

import seedu.cafectrl.data.dish.Ingredient;

import java.util.ArrayList;

/**
 * Represents a pantry that stores ingredients and manages their quantities.
 */
public class Pantry {

    private ArrayList<Ingredient> pantryStock;

    /**
     * Initializes a new instance of the Pantry class, loading the pantry stock from storage.
     */
    public Pantry() {
        pantryStock = retrieveStockFromStorage();
    }

    /**
     * Retrieves the current pantry stock from storage, which may include reading from a file (pantry.txt).
     *
     * @return An ArrayList of Ingredient objects representing the current pantry stock.
     */
    public ArrayList<Ingredient> getPantryStock() {
        return pantryStock;
    }

    /**
     * Retrieves the pantry stock from storage, e.g., by reading from a file (pantry.txt).
     *
     * @return An ArrayList of Ingredient objects representing the pantry stock.
     */
    public ArrayList<Ingredient> retrieveStockFromStorage () {
        ArrayList<Ingredient> pantryStock = new ArrayList<>();
        //TODO: Add file reader to read from pantry.txt
        return pantryStock;
    }


    /**
     * Adds or updates an ingredient in the pantry stock based on its name and quantity.
     *
     * @param name The name of the ingredient to add or update.
     * @param qty The quantity of the ingredient (e.g., "100g").
     * @param unit The unit of measurement for the quantity.
     * @return The Ingredient object that was added or updated in the pantry stock.
     */
    public Ingredient addIngredientToStock (String name, int qty, String unit) {
        pantryStock = getPantryStock(); //get latest pantry stock from pantry.txt
        int ingredientIndex = getIndexOfIngredient(name);

        //if ingredient exists in pantry, add quantity of that ingredient
        if (ingredientIndex != -1) {
            return addQty(qty, ingredientIndex);
        }

        //else, add new ingredient to pantry
        Ingredient ingredient = new Ingredient(name, qty, unit);
        pantryStock.add(ingredient);
        //TODO: Add file writer to write update pantry.txt
        return ingredient;

    }

    /**
     * Updates an ingredient's quantity in the pantry stock or adds a new ingredient if it doesn't exist.
     *
     * @param qty            The quantity of the ingredient (e.g., "100g").
     * @param ingredientIndex The index of the ingredient in the pantry stock (-1 if not found).
     * @return The Ingredient object that was added or updated in the pantry stock.
     */
    private Ingredient addQty(int qty, int ingredientIndex) {
        Ingredient ingredient = pantryStock.get(ingredientIndex);
        qty += ingredient.getQty(); //adds new qty to current qty
        ingredient.setQty(qty);
        return ingredient;
    }

    /**
     * Gets the index of an ingredient in the pantry stock based on its name (case-insensitive comparison).
     *
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
