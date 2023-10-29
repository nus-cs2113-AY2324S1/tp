package essenmakanan.recipe;

public class Recipe {
    private String title;
    private RecipeStepList recipeSteps;
    private RecipeIngredientList recipeIngredients;

    public Recipe(String title) {
        this.title = title;
        recipeSteps = new RecipeStepList();
        recipeIngredients = new RecipeIngredientList();
    }

    public Recipe(String title, String[] steps) {
        this.title = title;
        recipeSteps = new RecipeStepList(steps);
        // recipeIngredients = new RecipeIngredientList();
    }

    public Recipe(String title, String[] steps, String[] ingredients) {
        this.title = title;
        recipeSteps = new RecipeStepList(steps);
        recipeIngredients = new RecipeIngredientList(ingredients);
    }

    public RecipeStepList getRecipeSteps() {
        return recipeSteps;
    }

    public RecipeIngredientList getRecipeIngredients() {
        return recipeIngredients;
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
