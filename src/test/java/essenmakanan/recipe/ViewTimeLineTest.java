package essenmakanan.recipe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ViewTimeLineTest {

    private Recipe recipe = new Recipe("Bread", new String[]{});

    @BeforeEach
    public void setUp() {
        Step step1A = new Step("Buy Flavors", "20:00", Tag.NIGHT_BEFORE);
        Step step1B = new Step("Buy Eggs", "18:00", Tag.NIGHT_BEFORE);

        Step step2A = new Step("Wash it", "10:00", Tag.MORNING_OF_COOKING);
        Step step2B = new Step("Cut it", "10:00", Tag.MORNING_OF_COOKING);

        Step step3 = new Step("Cook", "12:00", Tag.ACTUAL_COOKING);
        Step step4 = new Step("Eat", "13:00");
        step4.setTag(Tag.MORE_THAN_ONE_DAY);

        RecipeStepList recipeSteps = recipe.getRecipeSteps();
        recipeSteps.addStep(step1A);
        recipeSteps.addStep(step1B);
        recipeSteps.addStep(step2A);
        recipeSteps.addStep(step2B);
        recipeSteps.addStep(step3);
        recipeSteps.addStep(step4);
    }

    @Test
    public void test() {
        recipe.viewTimeLine();
    }
}
