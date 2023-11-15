package essenmakanan.recipe;

import essenmakanan.ingredient.Ingredient;
import essenmakanan.ui.Ui;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Recipe {
    private String title;
    private RecipeStepList recipeSteps;
    private RecipeIngredientList recipeIngredients;


    public Recipe(String title, RecipeStepList recipeSteps, RecipeIngredientList recipeIngredients) {
        this.title = title;
        this.recipeSteps = recipeSteps;
        this.recipeIngredients = recipeIngredients;
    }

    public Recipe(String title, String[] steps, String[] ingredients) {
        this.title = title;
        this.recipeSteps = new RecipeStepList(steps);
        this.recipeIngredients = new RecipeIngredientList(ingredients);
    }

    public Recipe(String title, RecipeStepList recipeSteps) {
        this.title = title;
        this.recipeSteps = recipeSteps;
        this.recipeIngredients = new RecipeIngredientList(new String[]{});
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

    public Ingredient getRecipeIngredientByIndex(int index) {
        return recipeIngredients.getIngredientByIndex(index);
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

    public void addStep(Step step) {
        recipeSteps.addStep(step);
    }

    public String getTotalDuration() {
        int totalDuration = 0;
        for (Step step : recipeSteps.getSteps()) {
            totalDuration += step.getEstimatedDuration();
        }
        int hour = totalDuration / 60;
        int minutes = totalDuration - hour * 60;
        return "This Recipe will take you " + hour + " hours and " + minutes + " minutes.";
    }


    public void viewTimeLine() {

        Map<Tag, List<Step>> categorizedSteps = recipeSteps.getSteps()
            .stream()
            .sorted((s1,s2) -> s1.getTag().hasHigherPriorityThan(s2.getTag()))
            .collect(Collectors.groupingBy(Step::getTag));

        for (Tag tag : Tag.values()) {
            if (categorizedSteps.get(tag) != null && categorizedSteps.get(tag).size() > 0) {
                System.out.println("Steps that you have to do: " + tag);
                categorizedSteps.get(tag).forEach(step -> System.out.println("    " + step));
                Ui.drawDivider();
            }
        }

    }

    /**
     * Recipe stub for testing purposes
     *
     * @param title name of the stub
     * @return a Recipe stub object for testing
     */
    public static Recipe createRecipeStub(String title) {
        String[] recipeSteps = {"step1", "step2"};
        String[] recipeIngredients = {"i/flour,200,g", "i/egg,2,pc"};
        return new Recipe(title, recipeSteps, recipeIngredients);
    }

}
