package essenmakanan;

import static org.junit.jupiter.api.Assertions.assertEquals;

import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class AddRecipeTest {

    @Test
    public void addRecipes_validData_fullyConstructedRecipe() {
        RecipeList recipes = new RecipeList();
        String[] recipe1Steps = {"step1", "step2", "step3"};
        String[] recipe2Steps = {"step1", "step2"};
        recipes.addRecipe(new Recipe("Recipe1", recipe1Steps));
        recipes.addRecipe(new Recipe("Recipe2", recipe2Steps));

        Recipe recipe = recipes.getRecipe(0);
        assertEquals("Recipe1", recipe.getTitle());

        ArrayList<String> steps = recipe.getRecipeSteps();
        assertEquals("step1", steps.get(0));
        assertEquals("step2", steps.get(1));
        assertEquals("step3", steps.get(2));

        recipe = recipes.getRecipe(1);
        steps = recipe.getRecipeSteps();
        assertEquals("step1", steps.get(0));
        assertEquals("step2", steps.get(1));
    }
}
