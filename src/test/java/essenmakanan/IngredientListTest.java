package essenmakanan;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
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

        Ingredient tomato = new Ingredient("tomato");
        Ingredient cheese = new Ingredient("cheese");

        ingredients.addIngredient(tomato);
        ingredients.addIngredient(cheese);

        Ui.printAllIngredients(ingredients);

        Ingredient ingredient;
        ingredient = ingredients.getIngredientByIndex(0);
        assertEquals("tomato", ingredient.getName());

        ingredient = ingredients.getIngredientByIndex(1);
        assertEquals("cheese", ingredient.getName());
    }
}
