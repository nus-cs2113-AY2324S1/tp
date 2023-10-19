package essenmakanan.recipe;

import essenmakanan.ui.Ui;

import java.util.ArrayList;

public class RecipeList {
    private ArrayList<Recipe> recipes;

    public RecipeList() {
        recipes = new ArrayList<>();
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
        assert getRecipe(recipes.size() - 1).getTitle().equals(recipe.getTitle())
                : "Recipe is not successfully added into the list.";
    }

    public void addRecipe(String title, String[] steps) {
        recipes.add(new Recipe(title, steps));
    }

    public void deleteRecipe(Recipe recipe) {
        recipes.remove(recipe);
    }

    public void listRecipes(Ui ui) {
        ui.printAllRecipes(recipes);
    }

    public void viewSpecificRecipeByIndex(Ui ui, int index) {
        ui.printSpecificRecipes(index, recipes);
    }

    public void viewSpecificRecipeByTitle(Ui ui, String title) {
        ui.printSpecificRecipes(title, recipes);
    }

    public Recipe getRecipe(int index) {
        assert index >= 0 && index < recipes.size(): "Index Out of Bound";
        return recipes.get(index);
    }

}
