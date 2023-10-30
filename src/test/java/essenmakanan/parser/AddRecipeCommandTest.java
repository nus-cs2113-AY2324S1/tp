package essenmakanan.parser;

import essenmakanan.command.AddRecipeCommand;
import essenmakanan.exception.EssenFormatException;
import essenmakanan.recipe.RecipeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddRecipeCommandTest {

    private AddRecipeCommand addRecipeCommand;

    private RecipeList recipeList;

    @BeforeEach
    public void setUp() {
        recipeList = new RecipeList();
    }

    @Test
    public void addRecipeCommand_validCommand_recipeCreated() {
        String userInput = "r/bread s/step 1 instructions s/step 2 instructions ";
        addRecipeCommand = new AddRecipeCommand(userInput, recipeList);
        addRecipeCommand.executeCommand();
        assertEquals("bread", recipeList.getRecipeByIndex(0).getTitle());
        String step1 = recipeList.getRecipeByIndex(0).getRecipeSteps().getStepByIndex(0).getDescription();
        String step2 = recipeList.getRecipeByIndex(0).getRecipeSteps().getStepByIndex(1).getDescription();
        assertEquals("step 1 instructions", step1);
        assertEquals("step 2 instructions", step2);
    }

    @Test
    public void addRecipeWithInvalidInput_invalidIngredient_errorThrown() {
        String userInput = "r/bread s/step1 i/invalidIngredient";
        addRecipeCommand = new AddRecipeCommand(userInput, recipeList);

        assertThrows(EssenFormatException.class, () -> {
            addRecipeCommand.addWithTitleAndStepsAndIngredients();
        });
    }

    @Test
    public void addRecipeWithInvalidInput_missingTitle_errorThrown() {
        String userInput = "r/ s/step1 i/invalidIngredient";
        addRecipeCommand = new AddRecipeCommand(userInput, recipeList);

        assertThrows(EssenFormatException.class, () -> {
            addRecipeCommand.addWithTitleAndStepsAndIngredients();
        });
    }

}
