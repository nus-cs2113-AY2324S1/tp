package essenmakanan.recipe;

import essenmakanan.ui.Ui;

import java.util.ArrayList;
import java.util.List;

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

    public void deleteRecipe(int index) {
        Ui.printDeleteRecipeSuccess(recipes.get(index).getTitle());
        recipes.remove(index);
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

    public int getIndexOfRecipeByName(String recipeTitle) {
        int i = 0;
        for (Recipe recipe : recipes) {
            if (recipe.getTitle().equals(recipeTitle)) {
                return i;
            }
            i++;
        }
        return -1;
    }
    public boolean recipeExist(int index) {
        if (index >= 0 && index < recipes.size()) {
            return true;
        }
        return false;
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

    private static void listRecipeSteps(Recipe recipe) {
        List<String> steps = recipe.getRecipeSteps();
        int count = 1;
        for (String s : steps) {
            assert steps.get(count - 1).equals(s)
                    : "Step is not matching with the current index";

            System.out.println("Step " + count + ": " + s);
            count++;
        }
    }

    public void viewRecipeByIndex(int index) {
        Ui.drawDivider();
        if (index < 0 || index >= recipes.size()) {
            System.out.println("We have " + recipes.size() + "recipes right now and the given input is invalid.");
            return;
        }
        Recipe recipe = recipes.get(index-1);
        listRecipeSteps(recipe);
    }

    public void viewRecipeByTitle(String title) {
        Ui.drawDivider();
        Recipe recipe = recipes.stream()
            .filter(recipe1 -> recipe1.getTitle().equals(title))
            .findFirst()
            .orElse(null);
        if (recipe == null) {
            System.out.println("You haven't added this recipe with given title");
        }
        listRecipeSteps(recipe);
    }
}
