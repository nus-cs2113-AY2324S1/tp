package essenmakanan.recipe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortStepsTest {

    private Recipe recipe;


    @BeforeEach
    public void setUp() {
        recipe = new Recipe("Hamburger", new String[]{});
    }

    @Test
    public void sortTheListWithTwoSteps() {
        Step step1 = new Step("Cook", "10:00");
        Step step2 = new Step("Buy Ingredients", "8:00");

        RecipeStepList recipeStepList = recipe.getRecipeSteps();

        recipeStepList.addStep(step1);
        recipeStepList.addStep(step2);

        recipeStepList.sortByTime();

        assertEquals("Buy Ingredients", recipeStepList.getSteps().get(0).getDescription());
        assertEquals("Cook", recipeStepList.getSteps().get(1).getDescription());
    }

    @Test
    public void sortTheListWithThreeSteps() {
        Step step1 = new Step("Cook", "10:00");
        Step step2 = new Step("Buy Ingredients", "8:00");
        Step step3 = new Step("Eat", "12:00");


        RecipeStepList recipeStepList = recipe.getRecipeSteps();

        recipeStepList.addStep(step1);
        recipeStepList.addStep(step2);
        recipeStepList.addStep(step3);

        recipeStepList.sortByTime();

        assertEquals("Buy Ingredients", recipeStepList.getSteps().get(0).getDescription());
        assertEquals("Cook", recipeStepList.getSteps().get(1).getDescription());
        assertEquals("Eat", recipeStepList.getSteps().get(2).getDescription());

    }

}
