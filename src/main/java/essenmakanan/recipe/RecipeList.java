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

    public void addRecipe(String title, String[] steps) {
        recipes.add(new Recipe(title, steps));
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void deleteRecipe(Recipe recipe) {
        remove(recipe);
    }

    public void listRecipes() {
        for (int i = 0; i < recipes.size(); i++) {
            System.out.println((i + 1) + ": " + recipes.get(i).getTitle());
        }
    }

    public Recipe getRecipeByIndex(int index) {
        return recipes.get(index);
    }

}
