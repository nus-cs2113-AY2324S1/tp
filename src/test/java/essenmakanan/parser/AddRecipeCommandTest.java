package essenmakanan.parser;

import essenmakanan.command.AddRecipeCommand;
import essenmakanan.recipe.RecipeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddRecipeCommandTest {

    private AddRecipeCommand addRecipeCommand;

    private RecipeList recipeList;

    private final String userInput = "r/bread s/step 1 instructions s/step 2 instructions ";




    @BeforeEach
    public void setUp() {
        recipeList = new RecipeList();
        addRecipeCommand = new AddRecipeCommand(userInput, recipeList);
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        addRecipeCommand.executeCommand();
        assertEquals("bread", recipeList.getRecipeByIndex(0).getTitle());
        String step1 = recipeList.getRecipeByIndex(0).getRecipeSteps().getStepByIndex(0).getDescription();
        String step2 = recipeList.getRecipeByIndex(0).getRecipeSteps().getStepByIndex(1).getDescription();
        assertEquals("step 1 instructions", step1);
        assertEquals("step 2 instructions", step2);
    }

}
