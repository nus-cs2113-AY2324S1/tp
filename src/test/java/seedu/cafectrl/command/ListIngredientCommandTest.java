package seedu.cafectrl.command;

import org.junit.jupiter.api.Test;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ListIngredientCommandTest {
    @Test
    public void execute_validIndex_printsIngredients() {
        ArrayList<Dish> menuItems = new ArrayList<>();
        menuItems.add(new Dish("Chicken Rice",
                new ArrayList<>(Arrays.asList(new Ingredient("Rice", 1, "cup"),
                        new Ingredient("Chicken", 100, "g"))), 8.0F));
        menuItems.add(new Dish("Chicken Sandwich",
                new ArrayList<>(Arrays.asList(new Ingredient("Lettuce", 100, "g"),
                        new Ingredient("Chicken", 50, "g"))), 5.0F));
        Menu menu = new Menu(menuItems);

        ArrayList<String> actualOutput = new ArrayList<>();
        Ui ui = new Ui() {
            @Override
            public void showToUser(String... message) {
                actualOutput.addAll(Arrays.asList(message));
            }
        };

        int indexToSelect = 1;
        ListIngredientCommand listIngredientCommand = new ListIngredientCommand(indexToSelect, menu, ui);
        listIngredientCommand.execute();

        String expectedOutput = "Chicken Rice Ingredients: \n"
                + "Rice - 1cup\n"
                + "Chicken - 100g\n";

        assertEquals(expectedOutput.trim().replaceAll("\\s+", " "),
                actualOutput.get(0).trim().replaceAll("\\s+", " "));
    }

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
