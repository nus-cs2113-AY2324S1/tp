package essenmakanan.command;

import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.ingredient.IngredientUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeleteIngredientCommandTest {
    private IngredientList ingredients;
    private Ingredient ingredient0;
    private Ingredient ingredient1;

    @BeforeEach
    public void setup() {
        ingredients = new IngredientList();

        ingredient0 = new Ingredient("banana", "1", IngredientUnit.PIECE);
        ingredient1 = new Ingredient("apple", "2", IngredientUnit.PIECE);

        ingredients.addIngredient(ingredient0);
        ingredients.addIngredient(ingredient1);
    }

    @Test
    public void deleteIngredient_validIngredientId_deleteCorrectly() {
        Command deleteCommand = new DeleteIngredientCommand(ingredients, "2");
        deleteCommand.executeCommand();
        assert ingredients.getIngredient(0) == ingredient0 : "Wrong ingredient was removed";
        assert !ingredients.ingredientExist(1) : "Ingredient was not removed";
    }

    @Test
    public void deleteIngredient_validIngredientName_deleteCorrectly() {
        Command deleteCommand = new DeleteIngredientCommand(ingredients, "apple");
        deleteCommand.executeCommand();
        assert ingredients.getIngredient(0) == ingredient0 : "Wrong ingredient was removed";
        assert !ingredients.ingredientExist(1) : "Ingredient was not removed";
    }

    @Test
    public void deleteIngredient_extraIngredient_noDeletion() {
        Command deleteCommand = new DeleteIngredientCommand(ingredients, "1 3");
        deleteCommand.executeCommand();
        assert ingredients.getIngredient(0) == ingredient0 : "Ingredient was not supposed to be removed";
        assert ingredients.getIngredient(1) == ingredient1 : "Ingredient was not supposed to be removed";
    }

    @Test
    public void deleteIngredient_invalidIngredientName_noDeletion() {
        Command deleteCommand = new DeleteIngredientCommand(ingredients, "strawberry");
        deleteCommand.executeCommand();
        assert ingredients.getIngredient(0) == ingredient0 : "Ingredient was not supposed to be removed";
        assert ingredients.getIngredient(1) == ingredient1 : "Ingredient was not supposed to be removed";
    }

    @Test
    public void deleteIngredient_ingredientIdIsZero_noDeletion() {
        Command deleteCommand = new DeleteIngredientCommand(ingredients, "0");
        deleteCommand.executeCommand();
        assert ingredients.getIngredient(0) == ingredient0 : "Ingredient was not supposed to be removed";
        assert ingredients.getIngredient(1) == ingredient1 : "Ingredient was not supposed to be removed";
    }

    @Test
    public void deleteIngredient_invalidIngredientId_noDeletion() {
        Command deleteCommand = new DeleteIngredientCommand(ingredients, "4");
        deleteCommand.executeCommand();
        assert ingredients.getIngredient(0) == ingredient0 : "Ingredient was not supposed to be removed";
        assert ingredients.getIngredient(1) == ingredient1 : "Ingredient was not supposed to be removed";
    }
}
