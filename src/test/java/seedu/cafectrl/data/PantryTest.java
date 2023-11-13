package seedu.cafectrl.data;

import org.junit.jupiter.api.Test;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.ErrorMessages;
import seedu.cafectrl.ui.Messages;
import seedu.cafectrl.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PantryTest {
    @Test
    void addIngredientToStock_differentUnitForBuyIngredient_returnErrorMessage() {
        ArrayList<Ingredient> ingredientsList = new ArrayList<>();
        ingredientsList.add(new Ingredient("chicken", 500, "g"));

        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui, ingredientsList);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pantry.addIngredientToStock("chicken", 500, "ml");
        });

        String expectedErrorMessage = "chicken" + ErrorMessages.UNIT_NOT_MATCHING
                + "g" + ErrorMessages.IGNORE_REMAINING_INGREDIENTS;
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void isDishCooked_dishCooked_returnsTrue() {
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);

        //the following models an order of two chicken rice
        //each chicken rice needs 100g of chicken and 50g of rice
        //dish ingredients contains the total quantity of ingredients needed for the order
        ArrayList<Ingredient> dishIngredients = new ArrayList<>();
        dishIngredients.add(new Ingredient("Chicken", 200, "g"));
        dishIngredients.add(new Ingredient("Rice", 100, "g"));

        //pantryStock is the quantity of ingredients in the pantry
        pantry.addIngredientToStock("Chicken", 300, "g");
        pantry.addIngredientToStock("Rice", 200, "g");

        boolean expectedIsDishCooked = true;
        boolean actualIsDishCooked = pantry.isDishCooked(dishIngredients);

        assert pantry.getPantryStock().get(0).getQty() == 100;
        assert pantry.getPantryStock().get(1).getQty() == 100;
        assertEquals(expectedIsDishCooked, actualIsDishCooked);
    }

    @Test
    void isDishCooked_dishNotCooked_returnsFalse() {
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);

        //the following models an order of two chicken rice
        //each chicken rice needs 100g of chicken and 50g of rice
        //dish ingredients contains the total quantity of ingredients needed for the order
        ArrayList<Ingredient> dishIngredients = new ArrayList<>();
        dishIngredients.add(new Ingredient("Chicken", 200, "g"));
        dishIngredients.add(new Ingredient("Rice", 100, "g"));

        //pantryStock is the quantity of ingredients in the pantry
        pantry.addIngredientToStock("Chicken", 100, "g");
        pantry.addIngredientToStock("Rice", 200, "g");

        boolean expectedIsDishCooked = false;
        boolean actualIsDishCooked = pantry.isDishCooked(dishIngredients);

        assert pantry.getPantryStock().get(0).getQty() == 100;
        assert pantry.getPantryStock().get(1).getQty() == 200;
        assertEquals(expectedIsDishCooked, actualIsDishCooked);
    }

    @Test
    void calculateDishAvailability_orderCompleteNoRestock_showMaxDish() {
        // Create a dummy menu
        ArrayList<Ingredient> ingredients = new ArrayList<>(Arrays.asList(new Ingredient("Rice", 100, "g"),
                new Ingredient("Chicken", 50, "g")));

        Dish dishChickenRice = new Dish("Chicken Rice", ingredients, 2.50f);
        Dish dishChickenChop = new Dish("Chicken Chop", ingredients, 5.00f);
        Menu menu = new Menu();
        menu.addDish(dishChickenRice);
        menu.addDish(dishChickenChop);

        Order order = new Order(dishChickenRice, 2);
        order.setComplete(true);

        Ui ui = new Ui();

        Pantry pantry = new Pantry(ui);
        pantry.addIngredientToStock("Rice", 200, "g");
        pantry.addIngredientToStock("Chicken", 200, "g");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream consoleStream = new PrintStream(baos);

        PrintStream originalOut = System.out;
        System.setOut(consoleStream);

        pantry.calculateDishAvailability(menu, order);

        String actualOutput = baos.toString().trim();
        System.setOut(originalOut);

        String expectedOutput = "Dish: Chicken Rice"
                + "Available quantity: 2"
                + Messages.LINE_STRING
                + "Dish: Chicken Chop"
                + "Available quantity: 2" ;

        String normalizedExpected = expectedOutput.toLowerCase().replaceAll("\\s+", "").trim();
        String normalizedActual = actualOutput.toLowerCase().replaceAll("\\s+", "").trim();

        assertEquals(normalizedExpected, normalizedActual);
    }

    @Test
    void calculateMaxDish_orderCompleteNoRestock_showMaxDish() {
        // Create a dummy menu
        ArrayList<Ingredient> ingredientChickenRice = new ArrayList<>(Arrays.asList(new Ingredient("Rice", 100, "g"),
                new Ingredient("Chicken", 50, "g")));
        ArrayList<Ingredient> ingredientChickenChop = new ArrayList<>(List.of(new Ingredient("Chicken", 150, "g")));

        Dish dishChickenRice = new Dish("Chicken Rice", ingredientChickenRice, 2.50f);
        Dish dishChickenChop = new Dish("Chicken Chop", ingredientChickenChop, 5.00f);
        Menu menu = new Menu();
        menu.addDish(dishChickenRice);
        menu.addDish(dishChickenChop);

        Order order = new Order(dishChickenRice, 2);
        order.setComplete(true);

        Ui ui = new Ui();

        Pantry pantry = new Pantry(ui);
        pantry.addIngredientToStock("Rice", 200, "g");
        pantry.addIngredientToStock("Chicken", 200, "g");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream consoleStream = new PrintStream(baos);

        PrintStream originalOut = System.out;
        System.setOut(consoleStream);

        //max number of chicken rice
        assert pantry.calculateMaxDishes(dishChickenRice, menu, order) == 2;
        //max number of chicken chop
        assert pantry.calculateMaxDishes(dishChickenChop, menu, order) == 1;

        String actualOutput = baos.toString().trim();
        System.setOut(originalOut);

        String expectedOutput = "";

        String normalizedExpected = expectedOutput.toLowerCase().replaceAll("\\s+", "").trim();
        String normalizedActual = actualOutput.toLowerCase().replaceAll("\\s+", "").trim();

        //No restock strings should be printed out
        assertEquals(normalizedExpected, normalizedActual);
    }

    @Test
    void calculateMaxDish_orderCompleteRestock_showMaxDish() {
        //This test case is for when the order is completed and there are not enough ingredients for the next
        // Create a dummy menu
        ArrayList<Ingredient> ingredientChickenRice = new ArrayList<>(Arrays.asList(new Ingredient("Rice", 200, "g"),
                new Ingredient("Chicken", 200, "g")));
        ArrayList<Ingredient> ingredientChickenChop = new ArrayList<>(List.of(new Ingredient("Chicken", 200, "g")));

        Dish dishChickenRice = new Dish("Chicken Rice", ingredientChickenRice, 2.50f);
        Dish dishChickenChop = new Dish("Chicken Chop", ingredientChickenChop, 5.00f);
        Menu menu = new Menu();
        menu.addDish(dishChickenRice);
        menu.addDish(dishChickenChop);

        Order order = new Order(dishChickenRice, 2);
        order.setComplete(true);

        Ui ui = new Ui();

        Pantry pantry = new Pantry(ui);
        pantry.addIngredientToStock("Rice", 0, "g");
        pantry.addIngredientToStock("Chicken", 0, "g");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream consoleStream = new PrintStream(baos);

        PrintStream originalOut = System.out;
        System.setOut(consoleStream);

        //max number of chicken rice
        assert pantry.calculateMaxDishes(dishChickenRice, menu, order) == 0;

        String actualOutput = baos.toString().trim();
        System.setOut(originalOut);

        //when dishChickenRice is passed in as the dish parameter
        String expectedOutput = Messages.RESTOCK_CORNER
                + Messages.RESTOCK_TITLE
                + Messages.RESTOCK_CORNER
                + "| Rice                                   | 0g           | 200g         |"
                + Messages.RESTOCK_END_CAP
                + "| Chicken                                | 0g           | 200g         |"
                + Messages.RESTOCK_END_CAP;

        String normalizedExpected = expectedOutput.toLowerCase().replaceAll("\\s+", "").trim();
        String normalizedActual = actualOutput.toLowerCase().replaceAll("\\s+", "").trim();

        assertEquals(normalizedExpected, normalizedActual);
    }

    @Test
    void calculateMaxDish_orderIncompleteRestock_showMaxDish() {
        // Create a dummy menu
        ArrayList<Ingredient> ingredientChickenRice = new ArrayList<>(Arrays.asList(new Ingredient("Rice", 200, "g"),
                new Ingredient("Chicken", 200, "g")));
        ArrayList<Ingredient> ingredientChickenChop = new ArrayList<>(List.of(new Ingredient("Chicken", 200, "g")));

        Dish dishChickenRice = new Dish("Chicken Rice", ingredientChickenRice, 2.50f);
        Dish dishChickenChop = new Dish("Chicken Chop", ingredientChickenChop, 5.00f);
        Menu menu = new Menu();
        menu.addDish(dishChickenRice);
        menu.addDish(dishChickenChop);

        Order order = new Order(dishChickenRice, 2);
        order.setComplete(false);

        Ui ui = new Ui();

        Pantry pantry = new Pantry(ui);
        pantry.addIngredientToStock("Rice", 0, "g");
        pantry.addIngredientToStock("Chicken", 0, "g");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream consoleStream = new PrintStream(baos);

        PrintStream originalOut = System.out;
        System.setOut(consoleStream);

        //max number of chicken rice
        assert pantry.calculateMaxDishes(dishChickenRice, menu, order) == 0;

        String actualOutput = baos.toString().trim();
        System.setOut(originalOut);

        //when dishChickenRice is passed in as the dish parameter
        String expectedOutput = Messages.RESTOCK_CORNER
                + Messages.RESTOCK_TITLE
                + Messages.RESTOCK_CORNER
                + "| Rice                                   | 0g           | 400g         |"
                + Messages.RESTOCK_END_CAP
                + "| Chicken                                | 0g           | 400g         |"
                + Messages.RESTOCK_END_CAP;

        String normalizedExpected = expectedOutput.toLowerCase().replaceAll("\\s+", "").trim();
        String normalizedActual = actualOutput.toLowerCase().replaceAll("\\s+", "").trim();

        assertEquals(normalizedExpected, normalizedActual);
    }
}
