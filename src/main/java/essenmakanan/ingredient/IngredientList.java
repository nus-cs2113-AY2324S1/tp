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

    public Ingredient getIngredient(int index) {
        return ingredients.get(index);
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
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
