package essenmakanan.recipe;

import java.util.ArrayList;

public class Recipe {
    private String title;
    private RecipeStepList recipeSteps;

    public Recipe(String title) {
        this.title = title;
        recipeSteps = new RecipeStepList();
    }

    public Recipe(String title, String[] steps) {
        this.title = title;
        recipeSteps = new RecipeStepList(steps);
    }

    public ArrayList<String> getRecipeSteps() {
        return recipeSteps.getSteps();
    }

    public String getTitle() {
        return title;
    }
}
