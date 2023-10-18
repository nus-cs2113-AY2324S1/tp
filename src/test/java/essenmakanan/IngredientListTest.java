package essenmakanan;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientListTest {

    @Test
    public void addIngredient_validIngredient_addsNormally() {
        IngredientList ingredients = new IngredientList();

        Ingredient tomato = new Ingredient("tomato");
        Ingredient cheese = new Ingredient("cheese");
        Ingredient bread = new Ingredient("bread");

        ingredients.addIngredient(tomato);
        ingredients.addIngredient(cheese);
        ingredients.addIngredient(bread);
        ingredients.listIngredients();

        Ingredient ingredient;
        ingredient = ingredients.getIngredient(0);
        assertEquals("tomato", ingredient.getName());

        ingredient = ingredients.getIngredient(1);
        assertEquals("cheese", ingredient.getName());

        ingredient = ingredients.getIngredient(2);
        assertEquals("bread", ingredient.getName());
    }
}
