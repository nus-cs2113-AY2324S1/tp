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

public class StartRecipeCommandTest {
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

        String ingredientString1 = "flour, 200, g";
        String ingredientString2 = "egg, 2, pc";
        String[] ingredientList1 = {ingredientString1, ingredientString2};
        RecipeIngredientList recipeIngredientList1 = new RecipeIngredientList(ingredientList1);
        recipe0 = new Recipe("Fluffy Bread", recipeStepList, recipeIngredientList1);

        String ingredientString3 = "noodles, 100, g";
        String ingredientString4 = "egg, 1, pc";
        String ingredientString5 = "vegetable, 4, pc";
        String[] ingredientList2 = {ingredientString3, ingredientString4, ingredientString5};
        RecipeIngredientList recipeIngredientList2 = new RecipeIngredientList(ingredientList2);
        recipe1 = new Recipe("Meatball Noodles", recipeStepList, recipeIngredientList2);

        recipes.addRecipe(recipe0);
        recipes.addRecipe(recipe1);

        // For ingredients in our inventory
        ingredients = new IngredientList();
        Ingredient ingredient1 = new Ingredient("flour", "100", IngredientUnit.GRAM);
        Ingredient ingredient2 = new Ingredient("egg", "1", IngredientUnit.PIECE);
        ingredients.addIngredient(ingredient1);
        ingredients.addIngredient(ingredient2);
    }

    @Test
    public void startRecipe_validRecipeTitle_deleteCorrectly() {
        StartRecipeCommand command = new StartRecipeCommand("Fluffy Bread", recipes, ingredients);
        command.executeCommand();
        IngredientList insufficientIngredients = new IngredientList();
        double ingredientQty1 = 100;
        double ingredientQty2 = 1;
        insufficientIngredients.addIngredient(new Ingredient("flour", Double.toString(ingredientQty1), IngredientUnit.GRAM));
        insufficientIngredients.addIngredient(new Ingredient("egg", Double.toString(ingredientQty2), IngredientUnit.PIECE));
        // insufficientIngredients.addIngredient(new Ingredient("flour","100", IngredientUnit.GRAM));
        // insufficientIngredients.addIngredient(new Ingredient("egg", "1", IngredientUnit.PIECE));
        Ingredient checkIng1 = command.getInsufficientIngredients().getIngredient(0);
        Ingredient checkIng2 = insufficientIngredients.getIngredient(0);
        String name1 = checkIng1.getName();
        String name2 = checkIng2.getName();

        assert command.getInsufficientIngredients().equals(insufficientIngredients) : "The insufficient quantity was not detected";
    }
/*
    @Test
    public void startRecipe_validRecipeId_deleteCorrectly() {
        Command deleteCommand = new DeleteRecipeCommand(recipes, "r/Fry an egg");
        deleteCommand.executeCommand();
        assert recipes.getRecipe(0) == recipe0 : "Wrong recipe was removed";
        assert !recipes.recipeExist(1) : "Recipe was not removed";
    }*/
}
