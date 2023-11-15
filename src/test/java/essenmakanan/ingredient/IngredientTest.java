package essenmakanan.ingredient;

import essenmakanan.command.AddIngredientCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {

    private IngredientList ingredients;

    @BeforeEach
    public void setUp() {
        ingredients = new IngredientList();
    }

    @Test
    public void createIngredient_validUnit_addSuccess() {
        Ingredient ingredient = new Ingredient("bread", 2.0, IngredientUnit.PIECE);
        assert ingredient.getUnit() == IngredientUnit.PIECE;
        assertEquals(2.0, ingredient.getQuantity());
        assertEquals(IngredientUnit.PIECE, ingredient.getUnit());
    }

    @Test void addMultipleIngredients_inOneLine_addSuccess() {
        AddIngredientCommand addIngredientCommand = new AddIngredientCommand(
                "i/bread,2,pc i/apple,3,kg i/milk,1,l",
                ingredients
        );

        addIngredientCommand.executeCommand();

        assertEquals(ingredients.getIngredient(0).getName(), "bread");
        assertEquals(ingredients.getIngredient(0).getQuantity(), 2.0);
        assertEquals(ingredients.getIngredient(0).getUnit(), IngredientUnit.PIECE);

        assertEquals(ingredients.getIngredient(1).getName(), "apple");
        assertEquals(ingredients.getIngredient(1).getQuantity(), 3.0);
        assertEquals(ingredients.getIngredient(1).getUnit(), IngredientUnit.KILOGRAM);

        assertEquals(ingredients.getIngredient(2).getName(), "milk");
        assertEquals(ingredients.getIngredient(2).getQuantity(), 1.0);
        assertEquals(ingredients.getIngredient(2).getUnit(), IngredientUnit.LITER);
    }

}
