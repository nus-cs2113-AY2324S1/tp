package essenmakanan;

import static org.junit.jupiter.api.Assertions.assertEquals;

import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeList;
import essenmakanan.recipe.RecipeStepList;
import org.junit.jupiter.api.Test;

class AddRecipeTest {

    @Test
    public void addRecipes_validData_fullyConstructedRecipes() {
        RecipeList recipes = new RecipeList();
        String[] recipe1Steps = {"step1", "step2", "step3"};
        String[] recipe2Steps = {"step1", "step2"};
        recipes.addRecipe(new Recipe("Recipe1", recipe1Steps));
        recipes.addRecipe(new Recipe("Recipe2", recipe2Steps));

        Recipe recipe = recipes.getRecipeByIndex(0);
        assertEquals("Recipe1", recipe.getTitle());

        RecipeStepList steps = recipe.getRecipeSteps();
        assertEquals("step1", steps.getStepByIndex(0).getDescription());
        assertEquals("step2", steps.getStepByIndex(1).getDescription());
        assertEquals("step3", steps.getStepByIndex(2).getDescription());

        recipe = recipes.getRecipeByIndex(1);
        steps = recipe.getRecipeSteps();
        assertEquals("step1", steps.getStepByIndex(0).getDescription());
        assertEquals("step2", steps.getStepByIndex(1).getDescription());
    }
}
