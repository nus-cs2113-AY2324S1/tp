package essenmakanan.parser;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.ingredient.IngredientList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class IngredientParserTest {
    private IngredientParser parser;
    private IngredientList ingredients;

    @BeforeEach
    public void setUp() {
        parser = new IngredientParser();
        ingredients = new IngredientList();
    }

    @Test
    public void parseIngredientInput_invalidInput_throwsEssenMakananFormatException() {
        String invalidDetails = "plus tomato";
        assertThrows(EssenFormatException.class, () -> {
            parser.parseIngredient(ingredients, invalidDetails);
        });
    }
    
}
