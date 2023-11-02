package essenmakanan.parser;

import essenmakanan.exception.EssenException;
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
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        String invalidCommand = "see";
        String invalidDetails = "recipes in my house";
        assertThrows(EssenException.class, () -> {
            recipeParser.parseRecipeCommand(recipes, invalidCommand, invalidDetails);
        });
    }

}
