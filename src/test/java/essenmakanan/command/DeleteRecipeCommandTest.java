package essenmakanan.command;

import essenmakanan.exception.EssenMakananFormatException;
import essenmakanan.exception.EssenMakananOutOfRangeException;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeList;
import essenmakanan.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteRecipeCommandTest {
    private RecipeList recipes;
    private Recipe recipe1;
    private Recipe recipe2;

    @BeforeEach
    public void setup() {
        recipes = new RecipeList();

        String[] steps = {"step1"};

        recipe1 = new Recipe("Bake a cake", steps);
        recipe2 = new Recipe("Fry an egg", steps);

        recipes.addRecipe(recipe1);
        recipes.addRecipe(recipe2);
    }

    @Test
    public void execute_validRecipeId_deleteCorrectly() {
        Command deleteCommand = new DeleteRecipeCommand(recipes, "1");
        deleteCommand.executeCommand();
        Ui.printAllRecipes(recipes);
    }

    @Test
    public void execute_validRecipeTitle_deleteCorrectly() {
        Command deleteCommand = new DeleteRecipeCommand(recipes, "Fry an egg");
        deleteCommand.executeCommand();
        Ui.printAllRecipes(recipes);
    }


    @Test
    public void execute_extraRecipe_throwsEssenMakananFormatException() {
        Command deleteCommand = new DeleteRecipeCommand(recipes, "1 2");
        deleteCommand.executeCommand();
    }

    @Test
    public void execute_invalidRecipeName_EssenMakananOutOfRangeException() {
        Command deleteCommand = new DeleteRecipeCommand(recipes, "Make strawberry fondue");
        deleteCommand.executeCommand();
    }

    @Test
    public void execute_recipeIdIsZero_EssenMakananOutOfRangeException() {
        Command deleteCommand = new DeleteRecipeCommand(recipes, "0");
        deleteCommand.executeCommand();
    }

    @Test
    public void execute_invalidRecipeId_EssenMakananOutOfRangeException() {
        Command deleteCommand = new DeleteRecipeCommand(recipes, "4");
        assertThrows(EssenMakananOutOfRangeException.class, () -> {
            deleteCommand.executeCommand();;
        });
    }
}
