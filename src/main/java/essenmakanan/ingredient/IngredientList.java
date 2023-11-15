package essenmakanan.ingredient;

import java.util.ArrayList;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.parser.IngredientParser;
import essenmakanan.ui.Ui;

public class IngredientList {
    private ArrayList<Ingredient> ingredients;

    public IngredientList() {
        ingredients = new ArrayList<>();
    }

    public IngredientList(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public IngredientList(String[] inputIngredients) {
        ingredients = new ArrayList<>();
        for (String ingredientString : inputIngredients) {
            assert !ingredientString.isEmpty() : "Ingredient must be valid and present";
            try {
                Ingredient ingredient = IngredientParser.parseIngredient(ingredientString);
                ingredients.add(ingredient);
            } catch (EssenFormatException e) {
                e.handleException();
            }
        }
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public Ingredient getIngredient(int index) {
        return ingredients.get(index);
    }

    /**
     * Get the ingredient object from the ingredient name
     *
     * @param name of the ingredient
     * @return the ingredient object
     */
    public Ingredient getIngredient(String name) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equals(name)) {
                return ingredient;
            }
        }
        return null;
    }

    public int getSize() {
        return this.ingredients.size();
    }

    public boolean isEmpty() {
        return ingredients.isEmpty();
    }

    /**
     * To check if an ingredient name exists in the ingredient list
     * @param ingredientName is a string
     * @return a boolean of whether the ingredient name is valid
     */
    public boolean exist(String ingredientName) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equals(ingredientName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * To check if an id is valid
     * @param id of the ingredient
     * @return a boolean of whether the id exists
     */
    public boolean exist(int id) {
        if (id >= 0 && id < ingredients.size()) {
            return true;
        }
        return false;
    }

    public int getIndex(Ingredient ingredient) {
        return ingredients.indexOf(ingredient);
    }

    /**
     * Get the index of the ingredient from the ingredient name
     *
     * @param ingredientName is a string
     * @return the index of the ingredient
     */
    public int getIndex(String ingredientName) {
        int i = 0;
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equals(ingredientName)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     * To add an ingredient object into the ingredient list if the ingredient does not already exist
     *
     * @param ingredient to be added
     */
    public void addIngredient(Ingredient ingredient) {
        assert ingredient.getName() != null : "Ingredient name should not be null";

        ingredients.add(ingredient);
    }

    /**
     * To update the quantity of an ingredient in the ingredient list. If ingredient quantity is negative,
     * subtract from existing quantity, else, add to the existing quantity
     *
     * @param ingredientToUpdate is an ingredient object
     * @throws EssenFormatException if the unit of the ingredient to update does not match the existing ingredient
     */
    public void updateIngredient(Ingredient ingredientToUpdate) throws EssenFormatException {
        Ingredient existingIngredient = this.getIngredient(ingredientToUpdate.getName());
        // check if unit matches
        if (!existingIngredient.getUnit().equals(ingredientToUpdate.getUnit())) {
            System.out.println("Existing ingredient unit is " + existingIngredient.getUnit().getValue()
                    + " but new ingredient unit is " + ingredientToUpdate.getUnit().getValue());
            throw new EssenFormatException();
        }

        double oldQuantity = existingIngredient.getQuantity();
        double deltaQuantity = ingredientToUpdate.getQuantity();
        double newQuantity = oldQuantity + deltaQuantity;

        if (newQuantity < 0) {
            // if new quantity is negative, throw exception
            System.out.println("You do not have enough ingredients to use.");
            return;
        }

        existingIngredient.setQuantity(newQuantity);
        Ui.printUpdateIngredientsSuccess(existingIngredient.getName(),
                oldQuantity,
                newQuantity);

    }

    /**
     * To edit the ingredient in the ingredient list. Details of what to edit can be found in editDetails
     *
     * @param existingIngredient is an ingredient object
     * @param editDetails is a string array of the details to edit
     * @throws EssenFormatException if the edit details are not in the correct format
     */
    public static void editIngredient(Ingredient existingIngredient, String[] editDetails) throws EssenFormatException {
        for (int i = 0; i < editDetails.length; i++) {
            if (editDetails[i].isEmpty()) {
                continue;
            }

            // get flag of input to know which field to edit
            String flag = editDetails[i].substring(0, 2);
            String content = editDetails[i].substring(2);

            switch (flag) {
            case "n/":
                Ui.printEditIngredientNameSuccess(existingIngredient.getName(), content);
                existingIngredient.setName(content);
                break;
            case "q/":
                Double newQuantity = null;
                try {
                    newQuantity = Double.parseDouble(content);
                } catch (NumberFormatException e) {
                    System.out.println("Quantity must be a double!");
                    throw new EssenFormatException();
                }
                Ui.printEditIngredientQuantitySuccess(existingIngredient.getQuantity(), newQuantity);
                existingIngredient.setQuantity(newQuantity);
                break;
            case "u/":
                try {
                    IngredientUnit newUnit = IngredientParser.mapIngredientUnit(content);
                    Ui.printEditIngredientUnitSuccess(existingIngredient.getUnit(), newUnit);
                    existingIngredient.setUnit(newUnit);
                } catch (EssenFormatException e) {
                    e.handleException();
                }
                break;
            default:
                System.out.println("See user guide for correct edit format and example: " +
                        "edit i/INGREDIENT_NAME [n/NEW_NAME] [q/NEW_QUANTITY] [u/NEW_UNIT]");
                throw new EssenFormatException();
            }
        }

    }

    /**
     * Delete ingredient by index
     *
     * @param index
     */
    public void deleteIngredient(int index) {
        Ui.printDeleteIngredientsSuccess(ingredients.get(index).getName());
        ingredients.remove(index);
    }

    /**
     * Print all ingredients in the ingredient list
     */
    public void listIngredients() {
        Ui.drawDivider();
        int count = 1;

        for (Ingredient ingredient : ingredients) {
            assert ingredients.get(count - 1).getName().equals(ingredient.getName())
                    : "Name is not matching with the current index";

            System.out.println(count + ". " + ingredient);
            count++;
        }
    }

    /**
     * To check if ingredient lists are the same
     *
     * @param list is an IngredientList ingredients
     * @return boolean value of whether the ingredient lists are the same
     */
    public boolean equals(IngredientList list) {
        if (this.getSize() != list.getSize()) {
            return false;
        }

        for (int i=0; i<this.getSize(); i++) {
            if ( !this.getIngredient(i).equals(list.getIngredient(i)) ) {
                return false;
            }
        }

        return true;
    }
}
