package essenmakanan.recipe;

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

    public void listRecipes() {
        int count = 1;

        System.out.println("Here's a list of your recipes!");
        for (Recipe recipe : recipes) {
            assert getRecipe(count - 1).getTitle().equals(recipe.getTitle())
                    : "Title is not matching with the current index";

            System.out.println(count + ". " + recipe);
            count++;
        }
    }

    public Recipe getRecipe(int index) {
        return recipes.get(index);
    }

}
