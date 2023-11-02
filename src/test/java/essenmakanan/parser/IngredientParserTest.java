package essenmakanan.parser;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.exception.EssenOutOfRangeException;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.ingredient.IngredientUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class IngredientParserTest {
    //private IngredientParser parser;
    private IngredientList ingredients;
    private Ingredient ingredient0;
    private Ingredient ingredient1;

    @BeforeEach
    public void setUp() {
        ingredients = new IngredientList();

        ingredient0 = new Ingredient("banana", "1", IngredientUnit.PIECE);
        ingredient1 = new Ingredient("apple", "2", IngredientUnit.PIECE);

        ingredients.addIngredient(ingredient0);
        ingredients.addIngredient(ingredient1);
    }

    @Test
    public void parseIngredient_invalidInput_throwsEssenMakananFormatException() {
        String invalidDetails = "plus tomato";
        assertThrows(EssenFormatException.class, () -> {
            IngredientParser.parseIngredient(invalidDetails);
        });
    }

    @Test
    public void getIngredientIndex_extraIndexInInput_throwsEssenOutOfRangeException() {
        String input = "0 1";
        assertThrows(EssenOutOfRangeException.class, () -> {
            IngredientParser.getIngredientIndex(ingredients, input);
        });
    }

    @Test void getIngredientIndex_invalidName_throwsEssenOutOfRangeException() {
        String input = "strawberry";
        assertThrows(EssenOutOfRangeException.class, () -> {
            IngredientParser.getIngredientIndex(ingredients, input);
        });
    }

    @Test void getIngredientIndex_invalidIndex_throwsEssenOutOfRangeException() {
        String input = "3";
        assertThrows(EssenOutOfRangeException.class, () -> {
            IngredientParser.getIngredientIndex(ingredients, input);
        });
    }
}
