package essenmakanan.ingredient;
import essenmakanan.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientListTest {

    private IngredientList ingredients;
    @BeforeEach
    public void setUp() {
        ingredients = new IngredientList();
    }

    @Test
    public void addIngredient_validIngredient_addsNormally() {

        Ingredient tomato = new Ingredient("tomato", 1.0, IngredientUnit.PIECE);
        Ingredient cheese = new Ingredient("cheese", 20.0, IngredientUnit.GRAM);

        ingredients.addIngredient(tomato);
        ingredients.addIngredient(cheese);

        Ui.printAllIngredients(ingredients);

        Ingredient ingredient;
        ingredient = ingredients.getIngredient(0);
        assertEquals("tomato", ingredient.getName());
        assertEquals(1.0, ingredient.getQuantity());
        assertEquals(IngredientUnit.PIECE, ingredient.getUnit());

        ingredient = ingredients.getIngredient(1);
        assertEquals("cheese", ingredient.getName());
        assertEquals(20.0, ingredient.getQuantity());
        assertEquals(IngredientUnit.GRAM, ingredient.getUnit());
    }
}
