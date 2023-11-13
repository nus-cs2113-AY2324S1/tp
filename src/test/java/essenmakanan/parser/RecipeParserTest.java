package essenmakanan.parser;

import essenmakanan.exception.EssenException;
import essenmakanan.exception.EssenFormatException;
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
    public void parsePlanCommandInput_invalidInput_essenFormatException() {
        String userInput1 = "1 r/bread";
        assertThrows(EssenFormatException.class, () -> {
            RecipeParser.parsePlanCommandInput(userInput1);
        });

        String userInput2 = "2 r/1 r/2 r/3";
        assertThrows(EssenFormatException.class, () -> {
            RecipeParser.parsePlanCommandInput(userInput2);
        });

        String userInput3 = "two r/1 r/2";
        assertThrows(EssenFormatException.class, () -> {
            RecipeParser.parsePlanCommandInput(userInput3);
        });

        String userInput4 = "r/1 r/2 r/3";
        assertThrows(EssenFormatException.class, () -> {
            RecipeParser.parsePlanCommandInput(userInput4);
        });

        String userInput5 = "2";
        assertThrows(EssenFormatException.class, () -> {
            RecipeParser.parsePlanCommandInput(userInput5);
        });

        String userInput6 = "two";
        assertThrows(EssenFormatException.class, () -> {
            RecipeParser.parsePlanCommandInput(userInput6);
        });
    }

    @Test
    public void getRecipeIndex_stringAsIndex_essenOutOfRangeException() {
        // Add recipes to myRecipeList
        String[] inputIngredients1 = {"flour,200,g", "egg,2,pc"};
        String[] inputSteps1 = {"beat the egg", "add flour into bowl of egg", "mix them together"};
        Recipe myRecipe1 = new Recipe("bread", inputSteps1, inputIngredients1);

        String[] inputIngredients2 = {"noodles,100,g", "egg,1,pc", "vegetable,4,pc"};
        String[] inputSteps2 = {"beat the egg", "add flour into bowl of egg", "mix them together"};
        Recipe myRecipe2 = new Recipe("noodles", inputSteps2, inputIngredients2);

        RecipeList myRecipes = new RecipeList();
        myRecipes.addRecipe(myRecipe1);
        myRecipes.addRecipe(myRecipe2);

        String userInput1 = "r/6";
        assertThrows(EssenOutOfRangeException.class, () -> {
            RecipeParser.getRecipeIndex(myRecipes, userInput1);
        });

        String userInput2 = "r/0";
        assertThrows(EssenOutOfRangeException.class, () -> {
            RecipeParser.getRecipeIndex(myRecipes, userInput2);
        });

        String userInput3 = "r/no bread";
        assertThrows(EssenOutOfRangeException.class, () -> {
            RecipeParser.getRecipeIndex(myRecipes, userInput3);
        });

        String userInput4 = "no bread";
        assertThrows(EssenOutOfRangeException.class, () -> {
            RecipeParser.getRecipeIndex(myRecipes, userInput4);
        });
    }

    @Test
    public void viewRecipe_invalidRecipe_errorThrown() {
        String input = "r/apple";
        assertThrows(EssenOutOfRangeException.class, () -> {
            RecipeParser.getRecipeIndex(recipes, input);
        });
    }

    @Test
    public void executeRecipeCommand_titleDoNotExist_errorThrown() {
        String userInput = "recipeThatDoesNotExist";
        assertThrows(EssenOutOfRangeException.class, () -> {
            RecipeParser.getRecipeIndex(recipes, userInput);
        });
    }

}
