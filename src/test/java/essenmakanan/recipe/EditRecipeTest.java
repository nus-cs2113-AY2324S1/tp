package essenmakanan.recipe;

import essenmakanan.exception.EssenFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EditRecipeTest {

    private RecipeList recipes;
    private Recipe recipeToEdit;

    @BeforeEach
    public void setUp() {
        recipes = new RecipeList();
        recipeToEdit = new Recipe("Bread", new String[]{"Prepare", "Bake"},
                new String[]{"i,Flour,200,g", "i,Egg,2,pc"});
        recipes.addRecipe(recipeToEdit);
    }

    @Test
    public void editRecipeName_validInput_editSuccess() {
        String[] editDetails = {"n/Breads"};
        try {
            recipes.editRecipe(recipeToEdit, editDetails);
        } catch (EssenFormatException e) {
            e.handleException();
        }

        assertEquals("Breads", recipeToEdit.getTitle());
    }

    @Test
    public void editRecipeStep_validInput_editSuccess() {
        String[] editDetails = {"s/1,Prepare the dough"};
        try {
            recipes.editRecipe(recipeToEdit, editDetails);
        } catch (EssenFormatException e) {
            e.handleException();
        }

        String newStep = recipeToEdit.getRecipeSteps().getStepByIndex(0).getDescription();
        assertEquals("Prepare the dough", newStep);
    }

    @Test
    public void editRecipeNameAndStep_validInput_editSuccess() {
        // recipe title with one word
        String[] editDetails = {"n/Breads", "s/1,Prepare the dough"};
        try {
            recipes.editRecipe(recipeToEdit, editDetails);
        } catch (EssenFormatException e) {
            e.handleException();
        }

        String newStep = recipeToEdit.getRecipeSteps().getStepByIndex(0).getDescription();
        assertEquals("Prepare the dough", newStep);
        assertEquals("Breads", recipeToEdit.getTitle());
    }

    @Test
    public void editRecipe_invalidInput_exceptionThrown() {
        String[] editDetails = {"edit", "/nbreads"};

        assertThrows(EssenFormatException.class, () -> {
            recipes.editRecipe(recipeToEdit, editDetails);
        });
    }


    /*@Test
    public void editRecipeNameAndSteps_validInput_editSuccess() {
        // recipe title with multiple word
        String[] editDetails = {"edit", "n/white bread", "s/1,Prepare the dough"};
        try {
            recipes.editRecipe(recipeToEdit, editDetails);
        } catch (EssenFormatException e) {
            e.handleException();
        }

        String newStep = recipeToEdit.getRecipeSteps().getStepByIndex(0).getDescription();
        assertEquals("Prepare the dough", newStep);
        assertEquals("Breads", recipeToEdit.getTitle());
    }*/


}
