package essenmakanan.parser;

import essenmakanan.command.ViewSpecificRecipeCommand;
import essenmakanan.exception.EssenException;
import essenmakanan.exception.EssenOutOfRangeException;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RecipeParserTest {

    RecipeParser recipeParser;
    RecipeList recipes;

    @BeforeEach
    public void setUp() {

        recipeParser = new RecipeParser();
        recipes = new RecipeList();

        String[] recipeSteps = {"step1", "step2"};
        String[] recipeIngredients = {"i/flour,200,g", "i/egg,2,pc"};
        Recipe banana = new Recipe("banana", recipeSteps, recipeIngredients);

        recipes.addRecipe(banana);
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        String invalidCommand = "see";
        String invalidDetails = "recipes in my house";
        assertThrows(EssenException.class, () -> {
            recipeParser.parseRecipeCommand(recipes, invalidCommand, invalidDetails);
        });
    }

    @Test
    public void viewRecipe_invalidRecipe_errorThrown() {
        String input = "r/apple";
        assertThrows(EssenOutOfRangeException.class, () -> {
            RecipeParser.getRecipeIndex(recipes, input);
        });
    }

}
