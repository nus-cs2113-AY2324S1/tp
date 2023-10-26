package essenmakanan.parser;

import essenmakanan.command.AddRecipeCommand;
import essenmakanan.recipe.RecipeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddRecipeCommandTest {

    private AddRecipeCommand addRecipeCommand;

    private RecipeList recipeList;

    private final String INPUT = "r/bread s/step 1 instructions s/step 2 instructions ";




    @BeforeEach
    public void setUp() {
        recipeList = new RecipeList();
        addRecipeCommand = new AddRecipeCommand(INPUT, recipeList);
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        addRecipeCommand.executeCommand();
        assertEquals("bread", recipeList.getRecipe(0).getTitle());
        assertEquals("step 1 instructions", recipeList.getRecipe(0).getRecipeSteps().get(0));
        assertEquals("step 2 instructions", recipeList.getRecipe(0).getRecipeSteps().get(1));
    }

}
