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
        recipes.addRecipe("Recipe1", recipe1Steps);
        recipes.addRecipe("Recipe2", recipe2Steps);

        ArrayList<Recipe> recipeList = recipes.getRecipes();

        ArrayList<String> recipeSteps;
        assertEquals("Recipe1", recipeList.get(0).getTitle());

        recipeSteps = recipeList.get(0).getRecipeSteps();
        assertEquals("step1", recipeSteps.get(0));
        assertEquals("step2", recipeSteps.get(1));
        assertEquals("step3", recipeSteps.get(2));

        recipeSteps = recipeList.get(1).getRecipeSteps();
        assertEquals("step1", recipeSteps.get(0));
        assertEquals("step2", recipeSteps.get(1));
    }
}
