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
        recipeSteps = new RecipeStepList(steps);

    }

    public ArrayList<String> getRecipeSteps() {
        return recipeSteps.getSteps();
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return getTitle();
    }

    public String getTotalDuration() {
        int totalDuration = 0;
        for (Step step : recipeSteps) {
            totalDuration += step.getEstimatedTimeInMinutes();
        }
        int hour = totalDuration / 60;
        int minutes = totalDuration - hour * 60;
        return "This Recipe will take you " + hour + " hours and " + minutes + " minutes.";
    }

    public void sort() {
        recipeSteps.sort((step1, step2) -> step1.getStartTime().compareTo(step2.getStartTime()));
    }
    public void viewTimeLine() {

    }

    public static void main(String[] args) {
    }


}
