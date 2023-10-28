package essenmakanan.recipe;

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

    public RecipeStepList getRecipeSteps() {
        return recipeSteps;
    }

    public Step getRecipeStepByIndex(int index) {
        return recipeSteps.getStepByIndex(index);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
