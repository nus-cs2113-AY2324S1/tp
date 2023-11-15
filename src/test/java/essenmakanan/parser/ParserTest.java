package essenmakanan.parser;

import essenmakanan.exception.EssenCommandException;
import essenmakanan.exception.EssenFormatException;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.recipe.RecipeList;
import essenmakanan.shortcut.ShortcutList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    private IngredientList ingredients;
    private RecipeList recipes;
    private Ingredient ingredient1;
    private Parser parser;
    private ShortcutList shortcuts;

    @BeforeEach
    public void setUp() {
        ingredients = new IngredientList();
        recipes = new RecipeList();
        shortcuts = new ShortcutList();
        parser = new Parser();
    }

    @Test
    public void parseCommand_invalidCommand_essenCommandException() {
        String input1 = "invalid";
        assertThrows(EssenCommandException.class, () -> {
            parser.parseCommand(input1, recipes, ingredients, shortcuts);
        });

        String input2 = "";
        assertThrows(EssenCommandException.class, () -> {
            parser.parseCommand(input2, recipes, ingredients, shortcuts);
        });

        String input3 = "     ";
        assertThrows(EssenCommandException.class, () -> {
            parser.parseCommand(input3, recipes, ingredients, shortcuts);
        });

        String input4 = "1";
        assertThrows(EssenCommandException.class, () -> {
            parser.parseCommand(input4, recipes, ingredients, shortcuts);
        });
    }

    @Test
    public void parseFilterCommand_invalidCommand_essenFormatException() {
        String input1 = "filter";
        assertThrows(EssenFormatException.class, () -> {
            parser.parseCommand(input1, recipes, ingredients, shortcuts);
        });

        String input2 = "filter i/1";
        assertThrows(EssenFormatException.class, () -> {
            parser.parseCommand(input2, recipes, ingredients, shortcuts);
        });

        String input3 = "filter recipe";
        assertThrows(EssenFormatException.class, () -> {
            parser.parseCommand(input3, recipes, ingredients, shortcuts);
        });

        String input4 = "filter recipe i";
        assertThrows(EssenFormatException.class, () -> {
            parser.parseCommand(input4, recipes, ingredients, shortcuts);
        });
    }

    @Test
    public void parseViewCommand_invalidCommand_essenFormatException() {
        String input1 = "view";
        assertThrows(EssenFormatException.class, () -> {
            parser.parseCommand(input1, recipes, ingredients, shortcuts);
        });

        String input2 = "view m";
        assertThrows(EssenFormatException.class, () -> {
            parser.parseCommand(input2, recipes, ingredients, shortcuts);
        });

        String input3 = "view    ";
        assertThrows(EssenFormatException.class, () -> {
            parser.parseCommand(input3, recipes, ingredients, shortcuts);
        });
    }

}
