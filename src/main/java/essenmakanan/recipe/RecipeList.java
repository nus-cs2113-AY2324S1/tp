package essenmakanan.recipe;

import java.util.ArrayList;

public class RecipeList {
    private ArrayList<Recipe> recipes;

    public RecipeList() {
        recipes = new ArrayList<>();
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public void addRecipe(String title, String[] steps) {
        recipes.add(new Recipe(title, steps));
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void deleteRecipe(Recipe recipe) {
        recipes.remove(recipe);
    }

    public void viewAllRecipes() {
        int count = 1;
        for (Recipe recipe : recipes) {
            System.out.println(count + ". " + recipe);
            count ++;
        }
    }

    public Recipe getRecipeByIndex(int index) {
        return recipes.get(index);
    }

}
