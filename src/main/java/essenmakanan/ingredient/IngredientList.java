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
        System.out.println("You have deleted the following ingredient: " + ingredient.getName());
        ingredients.remove(ingredient);
    }

    public void listIngredients() {
        System.out.println("Here's a list of your ingredients!");

        int count = 1;

        for (Ingredient ingredient : ingredients) {
            assert ingredients.get(count - 1).getName().equals(ingredient.getName())
                    : "Title is not matching with the current index";

            System.out.println(count + ". " + ingredient.getName());
            count++;
        }
    }
}
