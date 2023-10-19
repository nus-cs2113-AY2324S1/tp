package essenmakanan;

import essenmakanan.exception.EssenMakananException;
import essenmakanan.parser.RecipeParser;
import essenmakanan.recipe.RecipeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        try{
            recipeParser.parseRecipeCommand(recipes, "invalid command", "invalid details");
        } catch (EssenMakananException e) {
            assertEquals(e.getMessage(), "EssenMakanan Exception!");
        }
    }

}
