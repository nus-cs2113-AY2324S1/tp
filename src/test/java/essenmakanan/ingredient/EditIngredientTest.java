package essenmakanan.ingredient;

import essenmakanan.exception.EssenFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EditIngredientTest {

    IngredientList ingredients;
    Ingredient ingredientToEdit;
    @BeforeEach
    public void setUp() {
        ingredients = new IngredientList();
        ingredientToEdit = new Ingredient("bread","2",IngredientUnit.PIECE);
    }
    @Test
    public void editIngredientName_validInput_editSuccess() {

        ingredients.addIngredient(ingredientToEdit);
        String[] editDetails = {"edit", "n/breads"};

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
        String[] editDetails = {"edit", "q/3"};

        try {
            ingredients.editIngredient(ingredientToEdit, editDetails);
        } catch (EssenFormatException e) {
            e.handleException();
        }

        assertEquals("3", ingredientToEdit.getQuantity());
    }

    @Test
    public void editIngredientUnit_validInput_editSuccess() {

        ingredients.addIngredient(ingredientToEdit);
        String[] editDetails = {"edit", "u/g"};

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
        String[] editDetails = {"edit", "n/breads", "q/3", "u/g"};

        try {
            ingredients.editIngredient(ingredientToEdit, editDetails);
        } catch (EssenFormatException e) {
            e.handleException();
        }

        assertEquals("breads", ingredientToEdit.getName());
        assertEquals("3", ingredientToEdit.getQuantity());
        assertEquals(IngredientUnit.GRAM, ingredientToEdit.getUnit());
    }

    @Test
    public void editIngredientName_invalidInput_editError() {

        ingredients.addIngredient(ingredientToEdit);
        String[] editDetails = {"edit", "/nbreads"};

        assertThrows(EssenFormatException.class, () -> {
            ingredients.editIngredient(ingredientToEdit, editDetails);
        });
    }
}
