package essenmakanan.recipe;

public class Recipe {
    private String title;
    private RecipeStepList recipeSteps;

    public Recipe(String title) {
        this.title = title;
        recipeSteps = new RecipeStepList();
    }
}
