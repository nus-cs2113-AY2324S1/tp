package essenmakanan.command;

import essenmakanan.exception.EssenInvalidEditException;
import essenmakanan.exception.EssenNullInputException;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeList;
import essenmakanan.recipe.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EditRecipeCommandTest {
    private EditRecipeCommand editRecipeCommand;
    private RecipeList recipes;
    private Recipe recipeToEdit;

    @BeforeEach
    public void setUp() {
        this.recipes = new RecipeList();

        // create recipeStub s/{"step1", "step2"} i/{"i/flour,200,g", "i/egg,2,pc"};
        recipeToEdit = Recipe.createRecipeStub("white bread"); // flour: 200g egg: 2pc
    }

    @Test
    public void editRecipe_validNameEdit_namedEdited() {

        recipes.addRecipe(recipeToEdit);

        String userInput = "r/white bread n/bread";
        editRecipeCommand = new EditRecipeCommand(userInput, recipes);
        editRecipeCommand.executeCommand();

        assertEquals("bread", recipes.getRecipe(0).getTitle());
    }

    @Test
    public void editRecipe_validStepEdit_namedEdited() {
        recipes.addRecipe(recipeToEdit);

        String userInput = "r/white bread s/1,new step 1";
        editRecipeCommand = new EditRecipeCommand(userInput, recipes);
        editRecipeCommand.executeCommand();

        assertEquals(Step.convertToStepIdTemplate("new step 1",1),
                recipes.getRecipe(0).getRecipeStepByIndex(0).getDescription());
    }

    @Test
    public void editRecipe_validNameStepEdit_namedEdited() {
        recipes.addRecipe(recipeToEdit);

        String userInput = "r/white bread n/bread s/1,new step 1 s/2,new step 2";
        editRecipeCommand = new EditRecipeCommand(userInput, recipes);
        editRecipeCommand.executeCommand();

        assertEquals("bread", recipes.getRecipe(0).getTitle());
        assertEquals(Step.convertToStepIdTemplate("new step 1",1),
                recipes.getRecipe(0).getRecipeStepByIndex(0).getDescription());
        assertEquals(Step.convertToStepIdTemplate("new step 2",2),
                recipes.getRecipe(0).getRecipeStepByIndex(1).getDescription());
    }

    @Test
    public void editRecipe_jumbledNameStepEdit_namedEdited() {
        recipes.addRecipe(recipeToEdit);

        String userInput = "r/white bread s/1,new step 1 n/bread s/2,new step 2";
        editRecipeCommand = new EditRecipeCommand(userInput, recipes);
        editRecipeCommand.executeCommand();

        assertEquals("bread", recipes.getRecipe(0).getTitle());
        assertEquals(Step.convertToStepIdTemplate("new step 1",1),
                recipes.getRecipe(0).getRecipeStepByIndex(0).getDescription());
        assertEquals(Step.convertToStepIdTemplate("new step 2",2),
                recipes.getRecipe(0).getRecipeStepByIndex(1).getDescription());
    }

    @Test
    public void editRecipe_missingTitle_exceptionThrown() {
        Recipe recipeToEdit = Recipe.createRecipeStub("white bread"); // flour: 200g egg: 2pc
        recipes.addRecipe(recipeToEdit);

        String userInput = "r/ n/newName";
        editRecipeCommand = new EditRecipeCommand(userInput, recipes);
        assertThrows(EssenNullInputException.class, () -> {
            editRecipeCommand.getRecipeTitle();
        });

        // case 2 of missing title
        userInput = "r/n/newName";
        editRecipeCommand = new EditRecipeCommand(userInput, recipes);
        assertThrows(EssenNullInputException.class, () -> {
            editRecipeCommand.getRecipeTitle();
        });
    }

    @Test
    public void editRecipe_missingEditDetails_exceptionThrown() {
        Recipe recipeToEdit = Recipe.createRecipeStub("white bread"); // flour: 200g egg: 2pc
        recipes.addRecipe(recipeToEdit);
        String userInput = "r/white bread n/";
        editRecipeCommand = new EditRecipeCommand(userInput, recipes);

        assertThrows(EssenInvalidEditException.class, () -> {
            editRecipeCommand.getAttributesToEdit();
        });
    }

    @Test
    public void editRecipe_invalidFlag_exceptionThrown() {
        Recipe recipeToEdit = Recipe.createRecipeStub("white bread"); // flour: 200g egg: 2pc
        recipes.addRecipe(recipeToEdit);
        String userInput = "r/white bread j/new name";
        editRecipeCommand = new EditRecipeCommand(userInput, recipes);

        assertThrows(EssenInvalidEditException.class, () -> {
            editRecipeCommand.getAttributesToEdit();
        });
    }



}
