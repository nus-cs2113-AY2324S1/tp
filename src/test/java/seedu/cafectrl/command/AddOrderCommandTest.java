package seedu.cafectrl.command;

import org.junit.jupiter.api.Test;
import seedu.cafectrl.Order;
import seedu.cafectrl.OrderList;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.data.dish.Ingredient;
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
        ingredients2.add(new Ingredient("chicken", 100, "g"));
        ingredients2.add(new Ingredient("rice", 50, "g"));

        ArrayList<Dish> menuItems = new ArrayList<>();
        Dish dishChickenRice = new Dish("Chicken Rice", ingredients, 2.50F);
        Dish dishChickenCurry = new Dish("Chicken Curry", ingredients2, 4.30F);
        menuItems.add(dishChickenRice);
        menuItems.add(dishChickenCurry);
        Menu menu = new Menu(menuItems);
        assertEquals(2, menu.getSize());

        ArrayList<Ingredient> pantryStock = new ArrayList<>();
        pantryStock.add(new Ingredient("chicken", 1000, "g"));
        pantryStock.add(new Ingredient("rice", 1000, "g"));
        Pantry pantry = new Pantry(new Ui(), pantryStock);

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

        OrderList orderList = new OrderList();

        Command addOrderCommand = new AddOrderCommand(orderChickenRice, ui, pantry, orderList);
        addOrderCommand.execute();

        String actualOutput = String.join(",", commandOutput);

        String expectedOutput = "I'm busy crafting your selected dish in the virtual kitchen of your dreams. "
                + "Bon appétit!"
                + "Is order completed?: true"
                + "Total orderList cost: $5.00";

        assert (expectedOutput.trim().replaceAll(",", "").equals(actualOutput.trim().replaceAll(",", "")));
    }

    @Test
    public void execute_addTwoOrder_expectTwoOrder() {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("chicken", 100, "g"));
        ingredients.add(new Ingredient("rice", 50, "g"));

        ArrayList<Ingredient> ingredients2 = new ArrayList<>();
        ingredients2.add(new Ingredient("chicken", 100, "g"));
        ingredients2.add(new Ingredient("rice", 50, "g"));

        ArrayList<Dish> menuItems = new ArrayList<>();
        Dish dishChickenRice = new Dish("Chicken Rice", ingredients, 2.50F);
        Dish dishChickenCurry = new Dish("Chicken Curry", ingredients2, 4.30F);
        menuItems.add(dishChickenRice);
        menuItems.add(dishChickenCurry);
        Menu menu = new Menu(menuItems);
        assertEquals(2, menu.getSize());

        ArrayList<Ingredient> pantryStock = new ArrayList<>();
        pantryStock.add(new Ingredient("chicken", 1000, "g"));
        pantryStock.add(new Ingredient("rice", 1000, "g"));
        Pantry pantry = new Pantry(new Ui(), pantryStock);

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

        OrderList orderList = new OrderList();

        Command addOrderCommand = new AddOrderCommand(orderChickenRice, new Ui(), pantry, orderList);
        addOrderCommand.execute();

        Command addOrderCommand2 = new AddOrderCommand(orderChickenCurry, ui, pantry, orderList);
        addOrderCommand2.execute();

        String actualOutput = String.join(",", commandOutput);

        String expectedOutput = "I'm busy crafting your selected dish in the virtual kitchen of your dreams. "
                + "Bon appétit!"
                + "Is order completed?: true"
                + "Total orderList cost: $17.90";

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
