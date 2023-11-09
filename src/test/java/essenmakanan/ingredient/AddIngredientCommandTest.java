package essenmakanan.ingredient;

import essenmakanan.command.AddIngredientCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddIngredientCommandTest {

    private IngredientList ingredients;
    private AddIngredientCommand addIngredientCommand;

    @BeforeEach
    public void setUp() {
        ingredients = new IngredientList();
        Ingredient tomato = new Ingredient("tomato", 1.0, IngredientUnit.PIECE);
        ingredients.addIngredient(tomato);
    }

    @Test
    public void addExistingIngredient_increaseQuantity_quantityIncreased(){

        String userInput = "i/tomato,2,pc";
        addIngredientCommand = new AddIngredientCommand(userInput, ingredients);
        addIngredientCommand.executeCommand();

        Ingredient ingredient = ingredients.getIngredient(0);
        assertEquals("tomato", ingredient.getName());
        assertEquals(3.0, ingredient.getQuantity());
        assertEquals(IngredientUnit.PIECE, ingredient.getUnit());
    }

    /*@Test
    public void addExistingIngredient_multipleIncreaseQuantity_quantityIncreased(){

        String userInput = "i/tomato,2,pc, i/tomato,3,pc";
        addIngredientCommand = new AddIngredientCommand(userInput, ingredients);
        addIngredientCommand.executeCommand();

        Ingredient ingredient = ingredients.getIngredient(0);
        assertEquals("tomato", ingredient.getName());
        assertEquals(6.0, ingredient.getQuantity());
        assertEquals(IngredientUnit.PIECE, ingredient.getUnit());
    }*/
}
