package essenmakanan.ingredient;
import java.util.ArrayList;

public class IngredientList {
    private ArrayList<Ingredient> ingredients;

    public IngredientList() {
        ingredients = new ArrayList<>();
    }

    public void addIngredient(String ingredientName) {
        ingredients.add(new Ingredient(ingredientName));
    }

    public void deleteIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
    }

    public void listIngredients() {
        int ingredientCount = 0;

        for (Ingredient ingredient : ingredients) {
            ingredientCount++;
            System.out.println(ingredientCount + ". " + ingredient.getName());
        }
    }
}
