package essenmakanan.ingredient;

import essenmakanan.ui.Ui;

import java.util.ArrayList;

public class IngredientList {
    private ArrayList<Ingredient> ingredients;

    public IngredientList() {
        ingredients = new ArrayList<>();
    }

    public Ingredient getIngredient(int index) {
        return ingredients.get(index);
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void deleteIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
    }

    public void listIngredients(Ui ui) {
        int ingredientCount = 1;

        System.out.println("Here's a list of your ingredients!");
        for (Ingredient ingredient : ingredients) {
            System.out.println(ingredientCount + ". " + ingredient.getName());
            ingredientCount++;
        }
    }
}
