package essenmakanan.parser;

import essenmakanan.command.AddRecipeCommand;
import essenmakanan.exception.EssenFormatException;
import essenmakanan.recipe.RecipeList;
import essenmakanan.recipe.RecipeStepList;
import essenmakanan.recipe.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddRecipeCommandTest {

    private AddRecipeCommand addRecipeCommand;

    private RecipeList recipeList;

    private RecipeStepList recipeStepList;
    @BeforeEach
    public void setUp() {
        recipeList = new RecipeList();
    }

    @Test
    public void addWithTitleStepsTags_validInput() {
        String userInput = "r/bread t/1 s/buy ingredients s/store ingredients " +
            "t/2 s/wash the ingredients s/cut the ingredients t/4 s/cook ";
        addRecipeCommand = new AddRecipeCommand(userInput, recipeList);
        addRecipeCommand.executeCommand();
        recipeStepList = recipeList.getRecipeByIndex(0).getRecipeSteps();
        String step1 = recipeStepList.getStepByIndex(0).getDescription();
        String step2 = recipeStepList.getStepByIndex(1).getDescription();
        String step3 = recipeStepList.getStepByIndex(2).getDescription();
        String step4 = recipeStepList.getStepByIndex(3).getDescription();
        String step5 = recipeStepList.getStepByIndex(4).getDescription();
        assertEquals("buy ingredients", step1);
        assertEquals("store ingredients", step2);
        assertEquals("wash the ingredients", step3);
        assertEquals("cut the ingredients", step4);
        assertEquals("cook", step5);

        Tag tag1 = recipeStepList.getStepByIndex(0).getTag();
        Tag tag2 = recipeStepList.getStepByIndex(1).getTag();
        Tag tag3 = recipeStepList.getStepByIndex(2).getTag();
        Tag tag4 = recipeStepList.getStepByIndex(3).getTag();
        Tag tag5 = recipeStepList.getStepByIndex(4).getTag();

        assertEquals(Tag.NIGHT_BEFORE, tag1);
        assertEquals(Tag.NIGHT_BEFORE, tag2);
        assertEquals(Tag.MORNING_OF_COOKING, tag3);
        assertEquals(Tag.MORNING_OF_COOKING, tag4);
        assertEquals(Tag.ACTUAL_COOKING, tag5);
        recipeList.getRecipeByIndex(0).viewTimeLine();

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
