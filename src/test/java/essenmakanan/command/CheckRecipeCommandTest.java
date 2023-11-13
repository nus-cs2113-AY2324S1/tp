package essenmakanan.command;

import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.ingredient.IngredientUnit;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeIngredientList;
import essenmakanan.recipe.RecipeList;
import essenmakanan.recipe.RecipeStepList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckRecipeCommandTest {
    private RecipeList recipes;
    private Recipe recipe0;
    private Recipe recipe1;
    private IngredientList ingredients;

    @BeforeEach
    public void setup() {
        // Recipe creation
        String[] steps = {"step1", "step2"};
        RecipeStepList recipeStepList = new RecipeStepList(steps);

        recipes = new RecipeList();

        // Recipe for Fluffy Bread
        String ingredientString1 = "flour, 200, g";
        String ingredientString2 = "egg, 2, pc";
        String ingredientString3 = "yeast, 50, g";
        String[] ingredientList1 = {ingredientString1, ingredientString2, ingredientString3};
        RecipeIngredientList recipeIngredientList1 = new RecipeIngredientList(ingredientList1);
        recipe0 = new Recipe("Fluffy Bread", recipeStepList, recipeIngredientList1);

        // Recipe for Meatball Noodles
        String ingredientString4 = "noodles, 100, g";
        String ingredientString5 = "egg, 1, pc";
        String ingredientString6 = "vegetable, 4, pc";
        String[] ingredientList2 = {ingredientString4, ingredientString5, ingredientString6};
        RecipeIngredientList recipeIngredientList2 = new RecipeIngredientList(ingredientList2);
        recipe1 = new Recipe("Meatball Noodles", recipeStepList, recipeIngredientList2);

        // Add both recipes to recipes
        recipes.addRecipe(recipe0);
        recipes.addRecipe(recipe1);

        // For ingredients in our inventory
        ingredients = new IngredientList();
        Ingredient ingredient1 = new Ingredient("flour", 100.0, IngredientUnit.GRAM);
        Ingredient ingredient2 = new Ingredient("egg", 1.0, IngredientUnit.PIECE);
        ingredients.addIngredient(ingredient1);
        ingredients.addIngredient(ingredient2);
    }

    @Test
    public void startRecipe_validIngredients_comparesCorrectly() {
        CheckRecipeCommand command = new CheckRecipeCommand("Fluffy Bread", recipes, ingredients);
        command.executeCommand();

        // Check if insufficientIngredients list is correct
        IngredientList insufficientIngredients = new IngredientList();
        double ingredientQty1 = 100;
        double ingredientQty2 = 1;
        insufficientIngredients.addIngredient(new Ingredient("flour",
                ingredientQty1, IngredientUnit.GRAM));
        insufficientIngredients.addIngredient(new Ingredient("egg",
                ingredientQty2, IngredientUnit.PIECE));
        assert command.getInsufficientIngredients().equals(insufficientIngredients)
                : "The insufficient quantity was not detected";

        // Check if missingIngredient list is correct
        IngredientList missingIngredients = new IngredientList();
        missingIngredients.addIngredient(new Ingredient("yeast", 50.0, IngredientUnit.GRAM));
        assert command.getMissingIngredients().equals(missingIngredients) : "The missing quantity was not detected";
    }
}
