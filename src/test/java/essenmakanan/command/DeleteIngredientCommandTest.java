package essenmakanan.command;

import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.ingredient.IngredientUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeleteIngredientCommandTest {
    private IngredientList ingredients;
    private Ingredient ingredient1;
    private Ingredient ingredient2;

    @BeforeEach
    public void setup() {
        ingredients = new IngredientList();

        ingredient1 = new Ingredient("banana", "1", IngredientUnit.PIECE);
        ingredient2 = new Ingredient("apple", "2", IngredientUnit.PIECE);

        ingredients.addIngredient(ingredient1);
        ingredients.addIngredient(ingredient2);
    }

    @Test
    public void deleteIngredient_validIngredientId_deleteCorrectly() {
        Command deleteCommand = new DeleteIngredientCommand(ingredients, "2");
        deleteCommand.executeCommand();
        ingredients.listIngredients();
    }

    @Test
    public void deleteIngredient_validIngredientName_deleteCorrectly() {
        Command deleteCommand = new DeleteIngredientCommand(ingredients, "apple");
        deleteCommand.executeCommand();
        ingredients.listIngredients();
    }

    @Test
    public void execute_extraIngredient_throwsEssenMakananFormatException() {
        Command deleteCommand = new DeleteIngredientCommand(ingredients, "1 3");
        deleteCommand.executeCommand();
    }

    @Test
    public void execute_invalidIngredientName_throwsEssenMakananOutOfRangeCommand() {
        Command deleteCommand = new DeleteIngredientCommand(ingredients, "strawberry");
        deleteCommand.executeCommand();
    }

    @Test
    public void execute_ingredientIdIsZero_throwsEssenMakananOutOfRangeCommand() {
        Command deleteCommand = new DeleteIngredientCommand(ingredients, "0");
        deleteCommand.executeCommand();
    }

    @Test
    public void execute_invalidIngredientId_throwsEssenMakananOutOfRangeCommand() {
        Command deleteCommand = new DeleteIngredientCommand(ingredients, "4");
        deleteCommand.executeCommand();
    }
}