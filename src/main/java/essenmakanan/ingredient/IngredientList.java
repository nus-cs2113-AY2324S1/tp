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
        String ingredientName;

        for (int i = 0; i < ingredients.size(); i++){
            ingredientCount++;
            ingredientName = ingredients.get(i).getName();
            System.out.println(ingredientCount + ". " + ingredientName);
        }
    }
}
