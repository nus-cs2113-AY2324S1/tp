package essenmakanan.parser;

import essenmakanan.exception.EssenMakananException;
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
    public void parseIngredientInput_invalidInput_throwsEssenMakananException() {
        String invalidDetails = "plus tomato";
        assertThrows(EssenMakananException.class, () -> {
            parser.parseIngredient(ingredients, invalidDetails);
        });
    }
}
