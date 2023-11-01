package seedu.cafectrl.parser;

import org.junit.jupiter.api.Test;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.parser.exception.ParserException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ParserUtilTest {
    @Test
    void parsePriceToFloat_validPriceString_exactFloatPrice() throws ParserException {
        String inputPriceString = "3.14";

        assertEquals((float) 3.14, ParserUtil.parsePriceToFloat(inputPriceString));
    }

    @Test
    void parsePriceToFloat_largePriceString_arithmeticExceptionThrown() throws ParserException {
        String inputPriceString = "99999999999.99";

        assertThrows(ParserException.class, () -> ParserUtil.parsePriceToFloat(inputPriceString));
    }

    @Test
    void isRepeatedDishName_existingDishName_true() {
        Menu menu = new Menu();
        Dish dish = new Dish("Chicken Rice", 2.50F);
        menu.addDish(dish);

        String inputDishName = "chicken rice";

        assertTrue(ParserUtil.isRepeatedDishName(inputDishName, menu));
    }

    @Test
    void isRepeatedDishName_nonExistingDishName_false() {
        Menu menu = new Menu();
        Dish dish = new Dish("Chicken Rice", 2.50F);
        menu.addDish(dish);

        String inputDishName = "chicken chop";

        assertFalse(ParserUtil.isRepeatedDishName(inputDishName, menu));
    }

    @Test
    void isRepeatedDishName_nullString_nullPointerExceptionThrown() throws NullPointerException {
        Menu menu = new Menu();
        Dish dish = new Dish("Chicken Rice", 2.50F);
        menu.addDish(dish);

        assertThrows(NullPointerException.class, () -> ParserUtil.isRepeatedDishName(null, menu));
    }

    @Test
    void isRepeatedDishName_emptyDishName_false() {
        Menu menu = new Menu();
        Dish dish = new Dish("Chicken Rice", 2.50F);
        menu.addDish(dish);

        String inputDishName = "";

        assertFalse(ParserUtil.isRepeatedDishName(inputDishName, menu));
    }

    @Test
    void isNameLengthInvalid_moreThanMaxLengthString_true() {
        assertTrue(ParserUtil.isNameLengthInvalid("this string is more than 35 characters"));
    }

    @Test
    void isNameLengthInvalid_lessThanMaxLengthString_false() {
        assertFalse(ParserUtil.isNameLengthInvalid("this str is less than 35 chars"));
    }

    @Test
    void isNameLengthInvalid_nullString_nullPointerExceptionThrown() throws NullPointerException {
        assertThrows(NullPointerException.class, () ->ParserUtil.isNameLengthInvalid(null));
    }
}
