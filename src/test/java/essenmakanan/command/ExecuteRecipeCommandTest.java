package essenmakanan.command;

import essenmakanan.exception.EssenNullInputException;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.ingredient.IngredientUnit;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExecuteRecipeCommandTest {
    private IngredientList ingredients;
    private RecipeList recipes;
    private ExecuteRecipeCommand executeRecipeCommand;

    @BeforeEach
    public void setUp() {
        // setting up ingredient inventory
        ingredients = new IngredientList();
        Ingredient egg = new Ingredient("egg", 4.0, IngredientUnit.PIECE);
        ingredients.addIngredient(egg);

        // setting up recipe
        recipes = new RecipeList();
        String recipeTitle = "bread";
        String[] recipeSteps = {"step1", "step2"};
        String[] recipeIngredients = {"i/egg,2,pc"};
        Recipe newRecipe = new Recipe(recipeTitle, recipeSteps, recipeIngredients);
        recipes.addRecipe(newRecipe);
    }

    @Test
    public void executeRecipeCommand_oneIngredient_recipeExecuted() {

        String userInput = "bread";
        executeRecipeCommand = new ExecuteRecipeCommand(ingredients, recipes, userInput);
        executeRecipeCommand.executeCommand();

        // check if ingredient decreased
        assertEquals(2.0, ingredients.getIngredient(0).getQuantity());
    }


    @Test
    public void executeRecipeCommand_noTitle_errorThrown() {
        String userInput = "";
        executeRecipeCommand = new ExecuteRecipeCommand(ingredients, recipes, userInput);
        assertThrows(EssenNullInputException.class, () -> {
            executeRecipeCommand.getRecipe();
        });
    }

    @Test
    public void executeRecipeCommand_multipleIngredient_recipeExecuted() {
        // setting up recipe
        recipes = new RecipeList();
        String recipeTitle = "toast";
        String[] recipeSteps = {"butter the bread", "bake"};
        String[] recipeIngredients = {"i/egg,2,pc", "i/cheese,0.2,kg", "i/milk,400,ml" ,"i/sugar,100,g"};
        Recipe newRecipe = new Recipe(recipeTitle, recipeSteps, recipeIngredients);
        recipes.addRecipe(newRecipe);

        // set up ingredient inventory
        Ingredient cheese = new Ingredient("cheese", 2.0, IngredientUnit.KILOGRAM);
        ingredients.addIngredient(cheese);
        Ingredient milk = new Ingredient("milk", 400.0, IngredientUnit.MILLILITER);
        ingredients.addIngredient(milk);
        Ingredient sugar = new Ingredient("sugar", 150.0, IngredientUnit.GRAM);
        ingredients.addIngredient(sugar);

        String userInput = "toast";
        executeRecipeCommand = new ExecuteRecipeCommand(ingredients, recipes, userInput);
        executeRecipeCommand.executeCommand();

        // check if ingredient decreased
        assertEquals(2.0, ingredients.getIngredient(0).getQuantity());
        assertEquals(1.8, ingredients.getIngredient(1).getQuantity());
        assertEquals(0.0, ingredients.getIngredient(2).getQuantity());
        assertEquals(50.0, ingredients.getIngredient(3).getQuantity());
    }

    @Test
    public void executeRecipeCommand_insufficientIngredient_recipeExecuted() {
        // setting up recipe
        recipes = new RecipeList();
        String recipeTitle = "bread";
        String[] recipeSteps = {"step1", "step2"};
        String[] recipeIngredients = {"i/egg,5,pc"};
        Recipe newRecipe = new Recipe(recipeTitle, recipeSteps, recipeIngredients);
        recipes.addRecipe(newRecipe);

        String userInput = "bread";
        executeRecipeCommand = new ExecuteRecipeCommand(ingredients, recipes, userInput);
        executeRecipeCommand.executeCommand();

        // ingredient qunatity should stay the same because insufficient
        assertEquals(4.0, ingredients.getIngredient(0).getQuantity());
    }
}
