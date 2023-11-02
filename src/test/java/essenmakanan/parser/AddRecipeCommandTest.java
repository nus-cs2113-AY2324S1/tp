package essenmakanan.parser;

import essenmakanan.command.AddRecipeCommand;
import essenmakanan.exception.EssenFormatException;
import essenmakanan.recipe.RecipeList;
import essenmakanan.recipe.RecipeStepList;
import essenmakanan.recipe.Step;
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

        recipeStepList = recipeList.getRecipes().get(0).getRecipeSteps();
        Step step1 = recipeStepList.getStepByIndex(0);
        Step step2 = recipeStepList.getStepByIndex(1);
        Step step3 = recipeStepList.getStepByIndex(2);
        Step step4 = recipeStepList.getStepByIndex(3);
        Step step5 = recipeStepList.getStepByIndex(4);
        assertEquals("buy ingredients", step1.getDescription());
        assertEquals("store ingredients", step2.getDescription());
        assertEquals("wash the ingredients", step3.getDescription());
        assertEquals("cut the ingredients", step4.getDescription());
        assertEquals("cook", step5.getDescription());

        assertEquals(Tag.NIGHT_BEFORE, step1.getTag());
        assertEquals(Tag.NIGHT_BEFORE, step2.getTag());
        assertEquals(Tag.MORNING_OF_COOKING, step3.getTag());
        assertEquals(Tag.MORNING_OF_COOKING, step4.getTag());
        assertEquals(Tag.ACTUAL_COOKING, step5.getTag());
    }

    @Test
    public void addWithTitleStepsTags_validInput_duration() {
        String userInput = "r/bread t/1 s/buy ingredients d/30minutes s/store ingredients " +
            "t/2 s/wash the ingredients s/cut the ingredients d/20mins t/4 s/cook d/1.6h ";
        addRecipeCommand = new AddRecipeCommand(userInput, recipeList);
        addRecipeCommand.executeCommand();

        recipeStepList = recipeList.getRecipes().get(0).getRecipeSteps();
        Step step1 = recipeStepList.getStepByIndex(0);
        Step step2 = recipeStepList.getStepByIndex(1);
        Step step3 = recipeStepList.getStepByIndex(2);
        Step step4 = recipeStepList.getStepByIndex(3);
        Step step5 = recipeStepList.getStepByIndex(4);

        assertEquals("buy ingredients", step1.getDescription());
        assertEquals("store ingredients", step2.getDescription());
        assertEquals("wash the ingredients", step3.getDescription());
        assertEquals("cut the ingredients", step4.getDescription());
        assertEquals("cook", step5.getDescription());

        assertEquals(30, step1.getEstimatedDuration());
        assertEquals(5, step2.getEstimatedDuration());
        assertEquals(5, step3.getEstimatedDuration());
        assertEquals(20, step4.getEstimatedDuration());
        assertEquals(96, step5.getEstimatedDuration());

    }

    @Test
    public void addRecipeCommand_validCommand_recipeCreated() {
        String userInput = "r/bread s/step 1 instructions s/step 2 instructions ";
        addRecipeCommand = new AddRecipeCommand(userInput, recipeList);
        addRecipeCommand.executeCommand();
        assertEquals("bread", recipeList.getRecipe(0).getTitle());
        String step1 = recipeList.getRecipe(0).getRecipeSteps().getStepByIndex(0).getDescription();
        String step2 = recipeList.getRecipe(0).getRecipeSteps().getStepByIndex(1).getDescription();
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
