package seedu.cafectrl.command;

import org.junit.jupiter.api.Test;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.Messages;
import seedu.cafectrl.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ListIngredientCommandTest {
    @Test
    public void execute_validIndex_printsIngredients() {
        ArrayList<Dish> menuItems = new ArrayList<>();
        menuItems.add(new Dish("Chicken Rice",
                new ArrayList<>(Arrays.asList(new Ingredient("Rice", 100, "g"),
                        new Ingredient("Chicken", 200, "g"))), 8.0F));
        menuItems.add(new Dish("Chicken Sandwich",
                new ArrayList<>(Arrays.asList(new Ingredient("Lettuce", 100, "g"),
                        new Ingredient("Chicken", 50, "g"))), 5.0F));
        Menu menu = new Menu(menuItems);

        Ui ui = new Ui();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream consoleStream = new PrintStream(baos);

        PrintStream originalOut = System.out;
        System.setOut(consoleStream);

        int indexToSelect = 1;
        ListIngredientCommand listIngredientCommand = new ListIngredientCommand(indexToSelect, menu, ui);
        listIngredientCommand.execute();

        String actualOutput = baos.toString().trim();
        System.setOut(originalOut);

        String expectedOutput = Messages.INGREDIENTS_END_CAP
                + "| Dish: chicken rice                                    |"
                + Messages.INGREDIENTS_CORNER
                + Messages.INGREDIENTS_TITLE
                + Messages.INGREDIENTS_CORNER
                + "| rice                                   | 100g         |"
                + "| chicken                                | 200g         |"
                + Messages.INGREDIENTS_END_CAP;

        String normalizedExpected = expectedOutput.toLowerCase().replaceAll("\\s+", "").trim();
        String normalizedActual = actualOutput.toLowerCase().replaceAll("\\s+", "").trim();

        assertEquals(normalizedExpected, normalizedActual);

    }

    @Test
    public void execute_invalidIndex_returnsErrorMessage() {
        ArrayList<Dish> menuItems = new ArrayList<>();
        menuItems.add(new Dish("Chicken Rice",
                new ArrayList<>(Arrays.asList(new Ingredient("Rice", 1, "cup"),
                        new Ingredient("Chicken", 100, "g"))), 8.0F));
        menuItems.add(new Dish("Chicken Sandwich",
                new ArrayList<>(Arrays.asList(new Ingredient("Lettuce", 100, "g"),
                        new Ingredient("Chicken", 50, "g"))), 5.0F));
        Menu menu = new Menu(menuItems);
        Ui ui = new Ui();
        int invalidIndex = 3;

        assertThrows(IllegalArgumentException.class, () -> {
            ListIngredientCommand listIngredientCommand = new ListIngredientCommand(invalidIndex, menu, ui);
            listIngredientCommand.execute();
        });
    }

}
