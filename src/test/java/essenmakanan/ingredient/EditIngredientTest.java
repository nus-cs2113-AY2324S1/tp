package essenmakanan.ingredient;

import essenmakanan.exception.EssenFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EditIngredientTest {

    private IngredientList ingredients;
    private Ingredient ingredientToEdit;
    @BeforeEach
    public void setUp() {
        ingredients = new IngredientList();
        ingredientToEdit = new Ingredient("bread",2.0,IngredientUnit.PIECE);
    }
    @Test
    public void editIngredientName_validInput_editSuccess() {

        ingredients.addIngredient(ingredientToEdit);
        String[] editDetails = {"n/breads"};

        try {
            ingredients.editIngredient(ingredientToEdit, editDetails);
        } catch (EssenFormatException e) {
            e.handleException();
        }

        assertEquals("breads", ingredientToEdit.getName());
    }

    @Test
    public void editIngredientQuantity_validInput_editSuccess() {

        ingredients.addIngredient(ingredientToEdit);
        String[] editDetails = {"q/3"};

        try {
            ingredients.editIngredient(ingredientToEdit, editDetails);
        } catch (EssenFormatException e) {
            e.handleException();
        }

        assertEquals(3.0, ingredientToEdit.getQuantity());
    }

    @Test
    public void editIngredientUnit_validInput_editSuccess() {

        ingredients.addIngredient(ingredientToEdit);
        String[] editDetails = {"u/g"};

        try {
            ingredients.editIngredient(ingredientToEdit, editDetails);
        } catch (EssenFormatException e) {
            e.handleException();
        }

        assertEquals(IngredientUnit.GRAM, ingredientToEdit.getUnit());
    }

    @Test
    public void editIngredientAll_validInput_editSuccess() {

        ingredients.addIngredient(ingredientToEdit);
        String[] editDetails = {"n/breads", "q/3", "u/g"};

        try {
            ingredients.editIngredient(ingredientToEdit, editDetails);
        } catch (EssenFormatException e) {
            e.handleException();
        }

        assertEquals("breads", ingredientToEdit.getName());
        assertEquals(3.0, ingredientToEdit.getQuantity());
        assertEquals(IngredientUnit.GRAM, ingredientToEdit.getUnit());
    }

    @Test
    public void editIngredientName_invalidInput_editError() {

        ingredients.addIngredient(ingredientToEdit);
        String[] editDetails = {"/nbreads"};

        assertThrows(EssenFormatException.class, () -> {
            ingredients.editIngredient(ingredientToEdit, editDetails);
        });
    }
}
