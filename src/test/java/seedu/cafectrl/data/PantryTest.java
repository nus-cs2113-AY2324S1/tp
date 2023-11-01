package seedu.cafectrl.data;

import org.junit.jupiter.api.Test;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.ErrorMessages;
import seedu.cafectrl.ui.Ui;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PantryTest {
    @Test
    void addIngredientToStock_differentUnitForBuyIngredient_returnErrorMessage() {
        ArrayList<Ingredient> ingredientsList = new ArrayList<>();
        ingredientsList.add(new Ingredient("chicken", 500, "g"));

        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui, ingredientsList);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pantry.addIngredientToStock("chicken", 500, "ml");
        });

        String expectedErrorMessage = ErrorMessages.UNIT_NOT_MATCHING + "\nUnit used previously: g";
        assertEquals(expectedErrorMessage, exception.getMessage());
    }
}
