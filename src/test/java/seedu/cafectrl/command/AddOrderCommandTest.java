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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddOrderCommandTest {
    @Test
    public void execute_addValidOrder_expectOneOrder() {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("chicken", 100, "g"));
        ingredients.add(new Ingredient("rice", 50, "g"));

        ArrayList<Ingredient> ingredients2 = new ArrayList<>();
        ingredients2.add(new Ingredient("chicken", 200, "g"));
        ingredients2.add(new Ingredient("rice", 50, "g"));

        ArrayList<Dish> menuItems = new ArrayList<>();
        Dish dishChickenRice = new Dish("chicken rice", ingredients, 2.50F);
        Dish dishChickenCurry = new Dish("chicken curry", ingredients2, 4.30F);
        menuItems.add(dishChickenRice);
        menuItems.add(dishChickenCurry);
        Menu menu = new Menu(menuItems);
        assertEquals(2, menu.getSize());

        Order orderChickenRice = new Order(dishChickenRice, 2);

        Ui ui = new Ui();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream consoleStream = new PrintStream(baos);

        PrintStream originalOut = System.out;
        System.setOut(consoleStream);

        ArrayList<Ingredient> pantryStock = new ArrayList<>();
        pantryStock.add(new Ingredient("chicken", 1000, "g"));
        pantryStock.add(new Ingredient("rice", 1000, "g"));
        Pantry pantry = new Pantry(ui, pantryStock);

        OrderList orderList = new OrderList();

        Command addOrderCommand = new AddOrderCommand(orderChickenRice, ui, pantry, orderList, menu);
        addOrderCommand.execute();

        String actualOutput = baos.toString().trim();
        System.setOut(originalOut);

        String expectedOutput =  Messages.CHEF_MESSAGE
                + Messages.LINE_STRING
                + Messages.COMPLETE_ORDER
                + "Total order cost: $5.00"
                + Messages.LINE_STRING
                + Messages.AVAILABLE_DISHES
                + Messages.EQUAL_LINE_STRING
                + "Dish: chicken rice"
                + "Available quantity: 8"
                + Messages.LINE_STRING
                + "Dish: chicken curry"
                + "Available quantity: 4";

        String normalizedExpected = expectedOutput.toLowerCase().replaceAll("\\s+", "").trim();
        String normalizedActual = actualOutput.toLowerCase().replaceAll("\\s+", "").trim();

        assertEquals(normalizedExpected, normalizedActual);
    }

    //one more test addInvalidOrder_expecterror
    @Test
    public void execute_addInvalidOrder_expectRestockMessage() {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("chicken", 200, "g"));
        ingredients.add(new Ingredient("rice", 100, "g"));

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

        Order orderChickenRice = new Order(dishChickenRice, 10);

        Ui ui = new Ui();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream consoleStream = new PrintStream(baos);

        PrintStream originalOut = System.out;
        System.setOut(consoleStream);

        ArrayList<Ingredient> pantryStock = new ArrayList<>();
        pantryStock.add(new Ingredient("chicken", 1000, "g"));
        pantryStock.add(new Ingredient("rice", 1000, "g"));
        Pantry pantry = new Pantry(ui, pantryStock);

        OrderList orderList = new OrderList();

        Command addOrderCommand = new AddOrderCommand(orderChickenRice, ui, pantry, orderList, menu);
        addOrderCommand.execute();

        String actualOutput = baos.toString().trim();
        System.setOut(originalOut);

        String expectedOutput = Messages.CHEF_MESSAGE
                + Messages.RESTOCK_CORNER
                + Messages.RESTOCK_TITLE
                + Messages.RESTOCK_CORNER
                + "| chicken                                | 1000g        | 2000g        |"
                + Messages.RESTOCK_END_CAP
                + Messages.INCOMPLETE_ORDER;


        String normalizedExpected = expectedOutput.toLowerCase().replaceAll("\\s+", "").trim();
        String normalizedActual = actualOutput.toLowerCase().replaceAll("\\s+", "").trim();

        assertEquals(normalizedExpected, normalizedActual);
    }

}
