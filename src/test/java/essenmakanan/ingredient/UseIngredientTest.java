package essenmakanan.ingredient;

import essenmakanan.command.UseIngredientCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UseIngredientTest {
    private IngredientList ingredients;
    private UseIngredientCommand useIngredientCommand;

    @BeforeEach
    public void setUp() {
        ingredients = new IngredientList();
        Ingredient tomato = new Ingredient("tomato", 5.0, IngredientUnit.PIECE);
        ingredients.addIngredient(tomato);
    }

    @Test
    public void useIngredient_enoughIngredient_quantityDecrease(){

        String userInput = "i/tomato,2,pc";
        useIngredientCommand = new UseIngredientCommand(ingredients,userInput);
        useIngredientCommand.executeCommand();

        Ingredient ingredient = ingredients.getIngredient(0);
        assertEquals("tomato", ingredient.getName());
        assertEquals(3.0, ingredient.getQuantity());
        assertEquals(IngredientUnit.PIECE, ingredient.getUnit());
    }

    @Test
    public void useIngredient_notEnoughIngredient_quantityDecrease(){

        String userInput = "i/tomato,6,pc";
        useIngredientCommand = new UseIngredientCommand(ingredients,userInput);
        useIngredientCommand.executeCommand();

        // nothing should happen
        Ingredient ingredient = ingredients.getIngredient(0);
        assertEquals("tomato", ingredient.getName());
        assertEquals(5.0, ingredient.getQuantity());
        assertEquals(IngredientUnit.PIECE, ingredient.getUnit());
    }
}
