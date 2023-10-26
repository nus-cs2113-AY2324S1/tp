package essenmakanan.ingredient;

import essenmakanan.exception.EssenMakananFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EditIngredientTest {
    @Test
    public void editIngredientName_validInput_editSuccess() {
        IngredientList ingredients = new IngredientList();
        Ingredient ingredientToEdit = new Ingredient("bread","2",IngredientUnit.PIECE);

        ingredients.addIngredient(ingredientToEdit);
        String[] editDetails = {"edit", "n/breads"};

        try {
            ingredients.editIngredient(ingredientToEdit, editDetails);
        } catch (EssenMakananFormatException e) {
            e.handleException();
        }

        assertEquals("breads", ingredientToEdit.getName());
    }

    @Test
    public void editIngredientQuantity_validInput_editSuccess() {
        IngredientList ingredients = new IngredientList();
        Ingredient ingredientToEdit = new Ingredient("bread","2",IngredientUnit.PIECE);

        ingredients.addIngredient(ingredientToEdit);
        String[] editDetails = {"edit", "q/3"};

        try {
            ingredients.editIngredient(ingredientToEdit, editDetails);
        } catch (EssenMakananFormatException e) {
            e.handleException();
        }

        assertEquals("3", ingredientToEdit.getQuantity());
    }

    @Test
    public void editIngredientUnit_validInput_editSuccess() {
        IngredientList ingredients = new IngredientList();
        Ingredient ingredientToEdit = new Ingredient("bread","2",IngredientUnit.PIECE);

        ingredients.addIngredient(ingredientToEdit);
        String[] editDetails = {"edit", "u/g"};

        try {
            ingredients.editIngredient(ingredientToEdit, editDetails);
        } catch (EssenMakananFormatException e) {
            e.handleException();
        }

        assertEquals(IngredientUnit.GRAM, ingredientToEdit.getUnit());
    }

    @Test
    public void editIngredientName_invalidInput_editSuccess() {
        IngredientList ingredients = new IngredientList();
        Ingredient ingredientToEdit = new Ingredient("bread","2",IngredientUnit.PIECE);

        ingredients.addIngredient(ingredientToEdit);
        String[] editDetails = {"edit", "/nbreads"};

        assertThrows(EssenMakananFormatException.class, () -> {
            ingredients.editIngredient(ingredientToEdit, editDetails);
        });
    }
}
