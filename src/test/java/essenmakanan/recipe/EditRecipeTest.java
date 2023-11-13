package essenmakanan.recipe;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.ingredient.IngredientUnit;
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
                new String[]{"i/Flour,200,g", "i/Egg,2,pc"});
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
        assertEquals(Step.convertToStepIdTemplate("Prepare the dough",1), newStep);
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
        assertEquals(Step.convertToStepIdTemplate("Prepare the dough",1), newStep);
        assertEquals("Breads", recipeToEdit.getTitle());
    }

    @Test
    public void editRecipe_invalidInput_exceptionThrown() {
        String[] editDetails = {"/nbreads"};

        assertThrows(EssenFormatException.class, () -> {
            recipes.editRecipe(recipeToEdit, editDetails);
        });
    }


    @Test
    public void editRecipeNameAndSteps_validInput_editSuccess() {
        // recipe title with multiple word
        String[] editDetails = {"n/white bread", "s/1,Prepare the dough"};
        try {
            recipes.editRecipe(recipeToEdit, editDetails);
        } catch (EssenFormatException e) {
            e.handleException();
        }

        String newStep = recipeToEdit.getRecipeSteps().getStepByIndex(0).getDescription();
        assertEquals(Step.convertToStepIdTemplate("Prepare the dough",1), newStep);
        assertEquals("white bread", recipeToEdit.getTitle());
    }

    @Test
    public void editRecipeIngredient_nonIntegerIndex_editSuccess() {
        String[] editDetails = {"i/f,n-newName"};

        assertThrows(EssenFormatException.class, () -> {
            recipes.editRecipe(recipeToEdit, editDetails);
        });
    }

    @Test
    public void editRecipeIngredient_indexOutOfRange_editSuccess() {
        String[] editDetails = {"i/10,n-newName"};

        assertThrows(EssenFormatException.class, () -> {
            recipes.editRecipe(recipeToEdit, editDetails);
        });
    }

    @Test
    public void editRecipeIngredient_validName_editSuccess() {
        // changing Flour to egg
        String[] editDetails = {"i/1,n-egg"};

        try {
            recipes.editRecipe(recipeToEdit, editDetails);
        } catch (EssenFormatException e) {
            e.handleException();
        }

        String newIngredientName = recipeToEdit.getRecipeIngredients().getIngredientByIndex(0).getName();
        assertEquals("egg", newIngredientName);
    }
    // edit r/bread i/1,n-Yeast1,q-1.0 i/2,n-Yeast2,q-2.0

    // edit r/recipeName i/1,q-newqty
    @Test
    public void editRecipeIngredient_validQuantity_editSuccess() {
        // changing Flour quantity from 200 to 400
        String[] editDetails = {"i/1,q-400"};

        try {
            recipes.editRecipe(recipeToEdit, editDetails);
        } catch (EssenFormatException e) {
            e.handleException();
        }

        double newIngredientQuantity = recipeToEdit.getRecipeIngredients().getIngredientByIndex(0).getQuantity();
        assertEquals(400.0, newIngredientQuantity);
    }

    @Test
    public void editRecipeIngredient_validUnit_editSuccess() {
        // changing Flour quantity from 200 to 400
        String[] editDetails = {"i/1,u-kg"};

        try {
            recipes.editRecipe(recipeToEdit, editDetails);
        } catch (EssenFormatException e) {
            e.handleException();
        }

        IngredientUnit newIngredientUnit = recipeToEdit.getRecipeIngredients().getIngredientByIndex(0).getUnit();
        assertEquals(IngredientUnit.KILOGRAM, newIngredientUnit);
    }

}
