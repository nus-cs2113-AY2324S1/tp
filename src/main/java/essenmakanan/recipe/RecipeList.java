package essenmakanan.recipe;

import java.util.ArrayList;

public class RecipeList extends ArrayList<Recipe> {
    private ArrayList<Recipe> recipes;

    public RecipeList() {
        recipes = new ArrayList<>();
    }

    public void addRecipe(String input) {
        String recipeTitle = input.replace("r/", "");
        recipes.add(new Recipe(recipeTitle));
    }

    public void deleteRecipe(Recipe recipe) {
        remove(recipe);
    }


}
