package essenmakanan.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.ingredient.IngredientUnit;
import essenmakanan.parser.IngredientParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddRecipeTest {
    private RecipeList recipes;
    @BeforeEach
    public void setUp() {
        recipes = new RecipeList();
    }

    @Test
    public void addRecipes_validRecipeWithNameAndStep_fullyConstructedRecipes() {
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

    @Test
    public void addRecipe_titleAndStepsAndIngredients_recipeCreated() {
        String recipeTitle = "bread";
        String[] recipeSteps = {"step1", "step2"};
        String[] recipeIngredients = {"i/flour,200,g", "i/egg,2,pc"};

        Recipe newRecipe = new Recipe(recipeTitle, recipeSteps, recipeIngredients);
        recipes.addRecipe(newRecipe);

        Recipe addedRecipe = recipes.getRecipeByIndex(0);

        // check title is added correctly
        assertEquals("bread", addedRecipe.getTitle());

        // check steps are added correctly
        RecipeStepList steps = addedRecipe.getRecipeSteps();
        assertEquals("step1", steps.getStepByIndex(0).getDescription());
        assertEquals("step2", steps.getStepByIndex(1).getDescription());

        // check ingredients are added correctly
        RecipeIngredientList ingredients = addedRecipe.getRecipeIngredients();
        assertEquals("flour", ingredients.getIngredientByIndex(0).getName());
        assertEquals("200", ingredients.getIngredientByIndex(0).getQuantity());
        assertEquals(IngredientUnit.GRAM, ingredients.getIngredientByIndex(0).getUnit());

        assertEquals("egg", ingredients.getIngredientByIndex(1).getName());
        assertEquals("2", ingredients.getIngredientByIndex(1).getQuantity());
        assertEquals(IngredientUnit.PIECE, ingredients.getIngredientByIndex(1).getUnit());
    }


}
