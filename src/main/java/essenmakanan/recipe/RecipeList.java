package essenmakanan.recipe;

import essenmakanan.ui.Ui;

import java.util.ArrayList;

public class RecipeList {
    private ArrayList<Recipe> recipes;

    public RecipeList() {
        recipes = new ArrayList<>();
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
        assert getRecipeByIndex(recipes.size() - 1).getTitle().equals(recipe.getTitle())
                : "Recipe is not successfully added into the list.";
    }

    public void addRecipe(String title, String[] steps) {
        recipes.add(new Recipe(title, steps));
    }

    public void deleteRecipe(int id) {
        int pos = id - 1;
        Ui.printDeleteRecipeSuccess(recipes.get(pos).getTitle());
        recipes.remove(pos);
    }

    public Recipe getRecipeByIndex(int index) {
        assert index >= 0 && index < recipes.size() : "Index is out of bounds";
        return recipes.get(index);
    }

    public Recipe getRecipeByName(String name) {
        for (Recipe recipe : recipes) {
            if (recipe.getTitle().equals(name)) {
                return recipe;
            }
        }
        return null;
    }

    public boolean recipeIdInList(int id) {
        if (id > 0 && id <= recipes.size()) {
            return true;
        }
        return false;
    }

    public int idOfRecipeByName(String recipeTitle) {
        int i = 0;
        for (Recipe recipe : recipes) {
            i++;
            if (recipe.getTitle().equals(recipeTitle)) {
                return i;
            }
        }
        return -1;
    }

    public void listRecipeTitles() {
        Ui.drawDivider();
        System.out.println("Here's a list of your recipes!");
        int count = 1;

        for (Recipe recipe : recipes) {
            assert recipes.get(count - 1).getTitle().equals(recipe.getTitle())
                    : "Title is not matching with the current index";

            System.out.println(count + ". " + recipe);
            count++;
        }
    }
}
