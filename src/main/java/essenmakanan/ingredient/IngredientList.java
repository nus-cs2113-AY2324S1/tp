package essenmakanan.ingredient;

import java.util.ArrayList;
import essenmakanan.ui.Ui;

public class IngredientList {
    private ArrayList<Ingredient> ingredients;

    public IngredientList() {
        ingredients = new ArrayList<>();
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public Ingredient getIngredientByIndex(int index) {
        return ingredients.get(index);
    }

    public Ingredient getIngredientByName(String name) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equals(name)) {
                return ingredient;
            }
        }
        return null;
    }

    public int indexOf(Ingredient ingredient) {
        return ingredients.indexOf(ingredient);
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void editIngredient(Ingredient existingIngredient, Ingredient ingredientToEdit) {
        String oldName = existingIngredient.getName();
        String newName = ingredientToEdit.getName();

        String oldQuantity = existingIngredient.getQuantity();
        String newQuantity = ingredientToEdit.getQuantity();

        IngredientUnit oldUnit = existingIngredient.getUnit();
        IngredientUnit newUnit = ingredientToEdit.getUnit();

        if (!oldName.equals(newName)) {
            existingIngredient.setName(newName);
            Ui.printEditIngredientNameSuccess(oldName, newName);
        }

        if (!oldQuantity.equals(newQuantity)) {
            existingIngredient.setQuantity(newQuantity);
            Ui.printEditIngredientQuantitySuccess(oldQuantity, newQuantity);
        }

        if (!oldUnit.equals(newUnit)) {
            existingIngredient.setUnit(newUnit);
            Ui.printEditIngredientUnitSuccess(oldUnit, newUnit);
        }
    }

    public void deleteIngredient(Ingredient ingredient) {
        Ui.printDeleteIngredientsSuccess(ingredient.getName());
        ingredients.remove(ingredient);
    }

    public void listIngredients() {
        Ui.drawDivider();
        System.out.println("Here's a list of your ingredients!");
        int count = 1;

        for (Ingredient ingredient : ingredients) {
            assert ingredients.get(count - 1).getName().equals(ingredient.getName())
                    : "Title is not matching with the current index";

            System.out.println(count + ". " + ingredient);
            count++;
        }
    }
}
