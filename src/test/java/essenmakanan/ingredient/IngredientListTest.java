package essenmakanan.ingredient;
import essenmakanan.exception.EssenFormatException;
import essenmakanan.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void updateExistingIngredient_increaseQuantity_quantityIncreased(){
        Ingredient tomato = new Ingredient("tomato", 1.0, IngredientUnit.PIECE);
        Ingredient tomato2 = new Ingredient("tomato", 2.0, IngredientUnit.PIECE);

        ingredients.addIngredient(tomato);

        try {
            ingredients.updateIngredient(tomato2);
        } catch (EssenFormatException e) {
            e.handleException();
        }

        Ingredient ingredient = ingredients.getIngredient(0);
        assertEquals("tomato", ingredient.getName());
        assertEquals(3.0, ingredient.getQuantity());
        assertEquals(IngredientUnit.PIECE, ingredient.getUnit());
    }

    @Test
    public void updateIngredient_decreaseQuantity_quantityDecreased() {
        Ingredient tomato = new Ingredient("tomato", 2.0, IngredientUnit.PIECE);
        Ingredient tomato2 = new Ingredient("tomato", -1.0, IngredientUnit.PIECE);

        ingredients.addIngredient(tomato);

        try {
            ingredients.updateIngredient(tomato2);
        } catch (EssenFormatException e) {
            e.handleException();
        }

        Ingredient ingredient = ingredients.getIngredient(0);
        assertEquals("tomato", ingredient.getName());
        assertEquals(1.0, ingredient.getQuantity());
        assertEquals(IngredientUnit.PIECE, ingredient.getUnit());
    }

    @Test
    public void updateIngredient_decreaseMoreThanExisting_quantitySame() {
        Ingredient tomato = new Ingredient("tomato", 10.0, IngredientUnit.PIECE);
        Ingredient tomato2 = new Ingredient("tomato", -11.0, IngredientUnit.PIECE);

        ingredients.addIngredient(tomato);

        try {
            ingredients.updateIngredient(tomato2);
        } catch (EssenFormatException e) {
            e.handleException();
        }

        Ingredient ingredient = ingredients.getIngredient(0);
        assertEquals("tomato", ingredient.getName());
        assertEquals(10.0, ingredient.getQuantity());
        assertEquals(IngredientUnit.PIECE, ingredient.getUnit());
    }

    @Test
    public void editIngredient_quantityNotDouble_errorThrown() {
        Ingredient tomato = new Ingredient("tomato", 10.0, IngredientUnit.PIECE);

        ingredients.addIngredient(tomato);
        assertThrows(EssenFormatException.class, () -> {
            ingredients.editIngredient(tomato, new String[]{"i/tomato", "q/ten", "u/pc"});
        });
    }
}
