package essenmakanan.recipe;

import java.util.ArrayList;

public class RecipeList extends ArrayList<Recipe> {
    public void addRecipe(Recipe recipe) {
        add(recipe);
    }

    public void deleteRecipe(Recipe recipe) {
        remove(recipe);
    }


}
