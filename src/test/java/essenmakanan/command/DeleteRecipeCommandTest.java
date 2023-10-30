package essenmakanan.command;

import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class DeleteRecipeCommandTest {
    private RecipeList recipes;
    private Recipe recipe0;
    private Recipe recipe1;

    @BeforeEach
    public void setup() {
        recipes = new RecipeList();

        String[] steps = {"step1", "step2"};

        recipe0 = new Recipe("Bake a cake", steps);
        recipe1 = new Recipe("Fry an egg", steps);

        recipes.addRecipe(recipe0);
        recipes.addRecipe(recipe1);
    }

    @Test
    public void deleteRecipe_validRecipeId_deleteCorrectly() {
        Command deleteCommand = new DeleteRecipeCommand(recipes, "r/2");
        deleteCommand.executeCommand();
        assert recipes.getRecipe(0) == recipe0 : "Wrong recipe was removed";
        assert !recipes.recipeExist(1) : "Recipe was not removed";
    }

    @Test
    public void deleteRecipe_validRecipeTitle_deleteCorrectly() {
        Command deleteCommand = new DeleteRecipeCommand(recipes, "r/Fry an egg");
        deleteCommand.executeCommand();
        assert recipes.getRecipe(0) == recipe0 : "Wrong recipe was removed";
        assert !recipes.recipeExist(1) : "Recipe was not removed";
    }


    @Test
    public void deleteRecipe_extraRecipe_noDeletion() {
        Command deleteCommand = new DeleteRecipeCommand(recipes, "r/1 2");
        deleteCommand.executeCommand();
        assert recipes.getRecipe(0) == recipe0 : "Recipe was not supposed to be removed";
        assert recipes.getRecipe(1) == recipe1 : "Recipe was not supposed to be removed";
    }

    @Test
    public void deleteRecipe_invalidRecipeName_noDeletion() {
        Command deleteCommand = new DeleteRecipeCommand(recipes, "r/Make strawberry fondue");
        deleteCommand.executeCommand();
        assert recipes.getRecipe(0) == recipe0 : "Recipe was not supposed to be removed";
        assert recipes.getRecipe(1) == recipe1 : "Recipe was not supposed to be removed";
    }

    @Test
    public void deleteRecipe_recipeIdIsZero_noDeletion() {
        Command deleteCommand = new DeleteRecipeCommand(recipes, "r/0");
        deleteCommand.executeCommand();
        assert recipes.getRecipe(0) == recipe0 : "Recipe was not supposed to be removed";
        assert recipes.getRecipe(1) == recipe1 : "Recipe was not supposed to be removed";
    }

    @Test
    public void deleteRecipe_invalidRecipeId_noDeletion() {
        Command deleteCommand = new DeleteRecipeCommand(recipes, "r/4");
        deleteCommand.executeCommand();
        assert recipes.getRecipe(0) == recipe0 : "Recipe was not supposed to be removed";
        assert recipes.getRecipe(1) == recipe1 : "Recipe was not supposed to be removed";
    }
}
