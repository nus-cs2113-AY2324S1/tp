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
        assert getRecipe(recipes.size() - 1).getTitle().equals(recipe.getTitle())
                : "Recipe is not successfully added into the list.";
    }

    public void addRecipe(String title, String[] steps) {
        recipes.add(new Recipe(title, steps));
    }

    public void deleteRecipe(Recipe recipe) {
        recipes.remove(recipe);
    }

    public Recipe getRecipe(int index) {
        assert index >= 0 && index < recipes.size() : "Index is out of bounds";
        return recipes.get(index);
    }

    public void viewSpecificRecipe(int index) {
        Ui.drawDivider();
        if (index < 0 | index >= recipes.size()) {
            System.out.println("We have " + recipes.size() + "recipes right now and the given input is invalid.");
            return;
        }
        Recipe recipe = recipes.get(index-1);
        listAllSteps(recipe);
    }

    public void viewSpecificRecipe(String title) {
        Ui.drawDivider();
        Recipe recipe = recipes.stream().filter(recipe1 -> recipe1.getTitle().equals(title)).findFirst().orElse(null);
        if (recipe == null) {
            System.out.println("You haven't added this recipe with given title");
        }
        listAllSteps(recipe);
    }

    private static void listAllSteps(Recipe recipe) {
        List<String> steps = recipe.getRecipeSteps();
        int count = 1;
        for (String s : steps) {
            assert steps.get(count - 1).equals(s)
                : "Step is not matching with the current index";

            System.out.println("Step " + count + ": " + s);
            count++;
        }
    }




}
