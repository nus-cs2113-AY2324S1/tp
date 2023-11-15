package essenmakanan.command;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.ingredient.IngredientUnit;
import essenmakanan.recipe.RecipeIngredientList;
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
    public void addRecipeCommand_stepAndIngredient_recipeCreated() {
        String userInput = "r/bread s/step 1 instructions i/eggs,2,pc";
        addRecipeCommand = new AddRecipeCommand(userInput, recipeList);
        addRecipeCommand.executeCommand();
        assertEquals("bread", recipeList.getRecipe(0).getTitle());
        String step1 = recipeList.getRecipe(0).getRecipeSteps().getStepByIndex(0).getDescription();
        assertEquals(Step.convertToStepIdTemplate("step 1 instructions",1), step1);

        // check ingredients
        RecipeIngredientList recipeIngredients = recipeList.getRecipe(0).getRecipeIngredients();
        assertEquals("eggs", recipeIngredients.getIngredients().get(0).getName());
        assertEquals(2.0, recipeIngredients.getIngredients().get(0).getQuantity());
        assertEquals(IngredientUnit.PIECE, recipeIngredients.getIngredients().get(0).getUnit());
    }
    @Test
    public void addWithTitleStepsTags_stepIngredientAndTag_validInput() {
        String userInput = "r/bread t/1 s/buy ingredients i/vegetable oil,100,ml";

        addRecipeCommand = new AddRecipeCommand(userInput, recipeList);
        addRecipeCommand.executeCommand();
        recipeStepList = recipeList.getRecipes().get(0).getRecipeSteps();
        Step step1 = recipeStepList.getStepByIndex(0);

        // check step is correct

        assertEquals(Step.convertToStepIdTemplate("buy ingredients",1), step1.getDescription());

        // check tag is correct
        assertEquals(Tag.NIGHT_BEFORE, step1.getTag());

        // check ingredient is correct
        RecipeIngredientList recipeIngredients = recipeList.getRecipe(0).getRecipeIngredients();
        assertEquals("vegetable oil", recipeIngredients.getIngredients().get(0).getName());
        assertEquals(100,0, recipeIngredients.getIngredients().get(0).getQuantity());
        assertEquals(IngredientUnit.MILLILITER, recipeIngredients.getIngredients().get(0).getUnit());

    }
    @Test
    public void addRecipeCommand_multipleStepsIngredientsAndTags_validInput() {
        String userInput = "r/bread t/1 s/buy ingredients s/store ingredients " +
            "t/2 s/wash the ingredients s/cut the ingredients t/3 s/marinade t/4 s/cook " +
                "i/vegetable oil,1,l i/eggs,2,pc";

        addRecipeCommand = new AddRecipeCommand(userInput, recipeList);
        addRecipeCommand.executeCommand();

        recipeStepList = recipeList.getRecipes().get(0).getRecipeSteps();
        Step step1 = recipeStepList.getStepByIndex(0);
        Step step2 = recipeStepList.getStepByIndex(1);
        Step step3 = recipeStepList.getStepByIndex(2);
        Step step4 = recipeStepList.getStepByIndex(3);
        Step step5 = recipeStepList.getStepByIndex(4);
        Step step6 = recipeStepList.getStepByIndex(5);

        assertEquals(Step.convertToStepIdTemplate("buy ingredients",1), step1.getDescription());
        assertEquals(Step.convertToStepIdTemplate("store ingredients",2), step2.getDescription());
        assertEquals(Step.convertToStepIdTemplate("wash the ingredients",3), step3.getDescription());
        assertEquals(Step.convertToStepIdTemplate("cut the ingredients",4), step4.getDescription());
        assertEquals(Step.convertToStepIdTemplate("marinade",5), step5.getDescription());
        assertEquals(Step.convertToStepIdTemplate("cook",6), step6.getDescription());

        assertEquals(Tag.NIGHT_BEFORE, step1.getTag());
        assertEquals(Tag.NIGHT_BEFORE, step2.getTag());
        assertEquals(Tag.MORNING_OF_COOKING, step3.getTag());
        assertEquals(Tag.MORNING_OF_COOKING, step4.getTag());
        assertEquals(Tag.MORE_THAN_ONE_DAY, step5.getTag());
        assertEquals(Tag.ACTUAL_COOKING, step6.getTag());

        // check ingredients are correct
        RecipeIngredientList recipeIngredients = recipeList.getRecipe(0).getRecipeIngredients();
        assertEquals("vegetable oil", recipeIngredients.getIngredients().get(0).getName());
        assertEquals(1,0, recipeIngredients.getIngredients().get(0).getQuantity());
        assertEquals(IngredientUnit.LITER, recipeIngredients.getIngredients().get(0).getUnit());

        assertEquals("eggs", recipeIngredients.getIngredients().get(1).getName());
        assertEquals(2.0, recipeIngredients.getIngredients().get(1).getQuantity());
        assertEquals(IngredientUnit.PIECE, recipeIngredients.getIngredients().get(1).getUnit());
    }


    @Test
    public void addWithTitleStepsTags_validInput_duration() {
        String userInput = "r/bread t/1 s/buy ingredients d/30mins " +
            "t/2 s/wash the ingredients d/20mins t/4 s/cook d/1.6h i/egg,2,pc";
        addRecipeCommand = new AddRecipeCommand(userInput, recipeList);
        addRecipeCommand.executeCommand();

        recipeStepList = recipeList.getRecipes().get(0).getRecipeSteps();
        Step step1 = recipeStepList.getStepByIndex(0);
        Step step2 = recipeStepList.getStepByIndex(1);
        Step step3 = recipeStepList.getStepByIndex(2);

        assertEquals(Step.convertToStepIdTemplate("buy ingredients",1), step1.getDescription());
        assertEquals(Step.convertToStepIdTemplate("wash the ingredients",2), step2.getDescription());
        assertEquals(Step.convertToStepIdTemplate("cook",3), step3.getDescription());

        assertEquals(30, step1.getEstimatedDuration());
        assertEquals(20, step2.getEstimatedDuration());
        assertEquals(96, step3.getEstimatedDuration());
    }

    @Test
    public void addRecipeWithInvalidInput_invalidIngredient_errorThrown() {
        String userInput = "r/bread s/step 1 i/invalidIngredient";
        addRecipeCommand = new AddRecipeCommand(userInput, recipeList);

        assertThrows(EssenFormatException.class, () -> {
            addRecipeCommand.addValidRecipe();
        });
    }

    @Test
    public void addRecipeWithInvalidInput_missingTitle_errorThrown() {
        String userInput = "r/ s/step1 i/egg,2,pc";
        addRecipeCommand = new AddRecipeCommand(userInput, recipeList);

        assertThrows(EssenFormatException.class, () -> {
            addRecipeCommand.addValidRecipe();
        });
    }


    @Test
    public void addRecipe_emptySteps_exceptionThrown() {
        String userInput = "r/bread s/ i/egg,2,pc";
        addRecipeCommand = new AddRecipeCommand(userInput, recipeList);

        assertThrows(EssenFormatException.class, () -> {
            addRecipeCommand.addValidRecipe();
        });
    }

    @Test
    public void addValidCommand_stepAndIngredientNotInOrder_recipeCreated() {
        String userInput = "r/bread i/eggs,2,pc s/step 1 instructions s/step 2 instructions";
        addRecipeCommand = new AddRecipeCommand(userInput, recipeList);
        addRecipeCommand.executeCommand();
        assertEquals("bread", recipeList.getRecipe(0).getTitle());
        String step1 = recipeList.getRecipe(0).getRecipeSteps().getStepByIndex(0).getDescription();
        String step2 = recipeList.getRecipe(0).getRecipeSteps().getStepByIndex(1).getDescription();

        assertEquals(Step.convertToStepIdTemplate("step 1 instructions",1), step1);
        assertEquals(Step.convertToStepIdTemplate("step 2 instructions",2), step2);

        // check ingredients
        RecipeIngredientList recipeIngredients = recipeList.getRecipe(0).getRecipeIngredients();
        assertEquals("eggs", recipeIngredients.getIngredients().get(0).getName());
        assertEquals(2.0, recipeIngredients.getIngredients().get(0).getQuantity());
        assertEquals(IngredientUnit.PIECE, recipeIngredients.getIngredients().get(0).getUnit());
    }

    @Test
    public void addRecipeCommand_multipleTitle_formatException() {
        String userInput = "r/bread r/toast s/step 1 i/egg,2,pc";
        addRecipeCommand = new AddRecipeCommand(userInput, recipeList);

        assertThrows(EssenFormatException.class, () -> {
            addRecipeCommand.addValidRecipe();
        });
    }

    @Test
    public void addRecipe_stepsWithMultipleDuration_exceptionThrown() {
        String userInput = "r/bread s/wash eggs d/1min d/1min i/egg,2,pc";
        addRecipeCommand = new AddRecipeCommand(userInput, recipeList);

        assertThrows(EssenFormatException.class, () -> {
            addRecipeCommand.addValidRecipe();
        });
    }

    @Test
    public void addRecipe_flagsTooClose_exceptionThrown() {
        String userInput = "r/t/1 s/STEP1 s/STEP2 d/30h t/2 s/STEP3 i/bread,2,kg";
        addRecipeCommand = new AddRecipeCommand(userInput, recipeList);

        assertThrows(EssenFormatException.class, () -> {
            addRecipeCommand.addValidRecipe();
        });
    }

    @Test
    public void addRecipe_titleBlankSpaces_exceptionThrown() {
        String userInput = "r/     t/1 s/STEP1 s/STEP2 d/30h t/2 s/STEP3 i/bread,2,kg";
        addRecipeCommand = new AddRecipeCommand(userInput, recipeList);

        assertThrows(EssenFormatException.class, () -> {
            addRecipeCommand.addValidRecipe();
        });
    }


    @Test
    public void addRecipe_stepBlankSpaces_exceptionThrown() {
        String userInput = "r/toast s/   i/bread,2,kg";
        addRecipeCommand = new AddRecipeCommand(userInput, recipeList);

        assertThrows(EssenFormatException.class, () -> {
            addRecipeCommand.addValidRecipe();
        });
    }

    @Test
    public void addRecipe_ingredientBlankSpaces_exceptionThrown() {
        String userInput = "r/toast s/step1   i/   ";
        addRecipeCommand = new AddRecipeCommand(userInput, recipeList);

        assertThrows(EssenFormatException.class, () -> {
            addRecipeCommand.addValidRecipe();
        });
    }
}
