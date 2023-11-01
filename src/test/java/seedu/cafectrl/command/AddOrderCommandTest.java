package seedu.cafectrl.command;

import org.junit.jupiter.api.Test;
import seedu.cafectrl.data.Order;
import seedu.cafectrl.data.OrderList;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.Messages;
import seedu.cafectrl.ui.Ui;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddOrderCommandTest {
    @Test
    public void execute_addOneOrder_expectOneOrder() {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("chicken", 100, "g"));
        ingredients.add(new Ingredient("rice", 50, "g"));

        ArrayList<Ingredient> ingredients2 = new ArrayList<>();
        ingredients2.add(new Ingredient("chicken", 200, "g"));
        ingredients2.add(new Ingredient("rice", 50, "g"));

        ArrayList<Dish> menuItems = new ArrayList<>();
        Dish dishChickenRice = new Dish("Chicken Rice", ingredients, 2.50F);
        Dish dishChickenCurry = new Dish("Chicken Curry", ingredients2, 4.30F);
        menuItems.add(dishChickenRice);
        menuItems.add(dishChickenCurry);
        Menu menu = new Menu(menuItems);
        assertEquals(2, menu.getSize());



        Order orderChickenRice = new Order(dishChickenRice, 2);

        ArrayList<String> commandOutput = new ArrayList<>();
        Ui ui = new Ui() {
            @Override
            public void showToUser(String... message) {
                String parseString = convertArrayToString(message, ",");
                commandOutput.add(parseString);
            }

            @Override
            public void showTotalCost(String dollarCost) {
                String parseString = ("Total orderList cost: $" + dollarCost);
                commandOutput.add(parseString);
            }
        };

        ArrayList<Ingredient> pantryStock = new ArrayList<>();
        pantryStock.add(new Ingredient("chicken", 1000, "g"));
        pantryStock.add(new Ingredient("rice", 1000, "g"));
        Pantry pantry = new Pantry(ui, pantryStock);

        OrderList orderList = new OrderList();

        Command addOrderCommand = new AddOrderCommand(orderChickenRice, ui, pantry, orderList, menu);
        addOrderCommand.execute();

        String actualOutput = String.join(",", commandOutput);

        String expectedOutput = Messages.CHEF_MESSAGE
                + Messages.LINE_STRING
                + "Order is ready!"
                + "Total orderList cost: $5.00"
                + Messages.LINE_STRING
                + Messages.AVAILABLE_DISHES
                + "Dish: Chicken Rice"
                + "Available Dishes: 8"
                + Messages.LINE_STRING
                + "Dish: Chicken Curry"
                + "Available Dishes: 4";

        assert (expectedOutput.trim().replaceAll(",", "").equals(actualOutput.trim().replaceAll(",", "")));
    }

    @Test
    public void execute_addTwoOrder_expectTwoOrder() {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("chicken", 100, "g"));
        ingredients.add(new Ingredient("rice", 50, "g"));

        ArrayList<Ingredient> ingredients2 = new ArrayList<>();
        ingredients2.add(new Ingredient("chicken", 200, "g"));
        ingredients2.add(new Ingredient("rice", 50, "g"));

        ArrayList<Dish> menuItems = new ArrayList<>();
        Dish dishChickenRice = new Dish("Chicken Rice", ingredients, 2.50F);
        Dish dishChickenCurry = new Dish("Chicken Curry", ingredients2, 4.30F);
        menuItems.add(dishChickenRice);
        menuItems.add(dishChickenCurry);
        Menu menu = new Menu(menuItems);
        assertEquals(2, menu.getSize());

        Order orderChickenRice = new Order(dishChickenRice, 2);
        Order orderChickenCurry = new Order(dishChickenCurry, 3);

        ArrayList<String> commandOutput = new ArrayList<>();
        Ui ui = new Ui() {
            @Override
            public void showToUser(String... message) {
                String parseString = convertArrayToString(message, ",");
                commandOutput.add(parseString);
            }

            @Override
            public void showTotalCost(String dollarCost) {
                String parseString = ("Total orderList cost: $" + dollarCost);
                commandOutput.add(parseString);
            }
        };

        ArrayList<Ingredient> pantryStock = new ArrayList<>();
        pantryStock.add(new Ingredient("chicken", 1000, "g"));
        pantryStock.add(new Ingredient("rice", 1000, "g"));
        Pantry pantry = new Pantry(ui, pantryStock);

        Ui uiPrintNothing = new Ui() {
            @Override
            public void showToUser(String... message) {

            }

            @Override
            public void showTotalCost(String dollarCost) {

            }
        };

        OrderList orderList = new OrderList();

        Command addOrderCommand = new AddOrderCommand(orderChickenRice, ui, pantry, orderList, menu);
        addOrderCommand.execute();

        Command addOrderCommand2 = new AddOrderCommand(orderChickenCurry, ui, pantry, orderList, menu);
        addOrderCommand2.execute();

        String actualOutput = String.join(",", commandOutput);

        String expectedOutputForFirstOrder = Messages.CHEF_MESSAGE
                + Messages.LINE_STRING
                + "Order is ready!"
                + "Total orderList cost: $5.00"
                + Messages.LINE_STRING
                + Messages.AVAILABLE_DISHES
                + "Dish: Chicken Rice"
                + "Available Dishes: 8"
                + Messages.LINE_STRING
                + "Dish: Chicken Curry"
                + "Available Dishes: 4";

        String expectedOutputForSecondOrder = Messages.CHEF_MESSAGE
                + Messages.LINE_STRING
                + "Order is ready!"
                + "Total orderList cost: $12.90"
                + Messages.LINE_STRING
                + Messages.AVAILABLE_DISHES
                + "Dish: Chicken Rice"
                + "Available Dishes: 2"
                + Messages.LINE_STRING
                + "Dish: Chicken Curry"
                + "Available Dishes: 1";

        String expectedOutput = expectedOutputForFirstOrder + expectedOutputForSecondOrder;

        assert (expectedOutput.trim().replaceAll(",", "").equals(actualOutput.trim().replaceAll(",", "")));
    }

    private static String convertArrayToString(String[] message, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (String str : message) {
            sb.append(str.toString()).append(delimiter);
        }
        return sb.substring(0, sb.length() - 1);
    }
}
