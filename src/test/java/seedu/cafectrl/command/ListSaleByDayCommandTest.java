package seedu.cafectrl.command;

import org.junit.jupiter.api.Test;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Order;
import seedu.cafectrl.data.OrderList;
import seedu.cafectrl.data.Sales;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.ErrorMessages;
import seedu.cafectrl.ui.Messages;
import seedu.cafectrl.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListSaleByDayCommandTest {

    @Test
    public void execute_validDayIndex_listSaleOfDay() {
        // Create a dummy menu
        ArrayList<Ingredient> ingredients = new ArrayList<>(
                Arrays.asList(new Ingredient("Lettuce", 100, "g"),
                    new Ingredient("Chicken", 50, "g")));

        Dish dishChickenRice = new Dish("Chicken Rice", ingredients, 2.50f);
        Dish dishChickenChop = new Dish("Chicken Chop", ingredients, 5.00f);
        Menu menu = new Menu();
        menu.addDish(dishChickenRice);
        menu.addDish(dishChickenChop);

        // Create a dummy order for day 1
        Order order1 = new Order(dishChickenRice, 2);
        order1.setComplete(true);
        Order order2 = new Order(dishChickenChop, 1);
        order2.setComplete(true);

        // Create a dummy order list for day 1
        OrderList orderList1 = new OrderList();
        orderList1.addOrder(order1);
        orderList1.addOrder(order2);

        // Create a dummy order for day 2
        Order order3 = new Order(dishChickenRice, 4);
        order3.setComplete(false);
        Order order4 = new Order(dishChickenChop, 1);
        order4.setComplete(true);
        Order order5 = new Order(dishChickenChop, 1);
        order5.setComplete(true);

        // Create a dummy order list for day 2
        OrderList orderList2 = new OrderList();
        orderList2.addOrder(order3);

        ArrayList<OrderList> orderLists= new ArrayList<>(Arrays.asList(orderList1, orderList2));
        // Create a sales object and add the order lists
        Sales sales = new Sales(orderLists);

        Ui ui = new Ui();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream consoleStream = new PrintStream(baos);

        PrintStream originalOut = System.out;
        System.setOut(consoleStream);

        int dayToListSale = 1;
        ListSaleByDayCommand listSaleByDayCommand =
                new ListSaleByDayCommand(dayToListSale, ui, sales);
        listSaleByDayCommand.execute();

        String actualOutput = baos.toString().trim();
        System.setOut(originalOut);

        String expectedOutput = Messages.SHOW_SALES_END_CAP
                + Messages.SHOW_SALES_DAY_PART_1
                + dayToListSale
                + Messages.SHOW_SALES_DAY_PART_2
                + Messages.SHOW_SALES_CORNER
                + Messages.SHOW_SALES_TITLE
                + Messages.SHOW_SALES_CORNER
                + "| chicken rice                           | 2            | 5.00              |"
                + "| chicken chop                           | 1            | 5.00              |"
                + Messages.SHOW_SALES_END_CAP
                + "| Total for day:                                        | $10.00            |"
                + Messages.SHOW_SALES_END_CAP;

        String normalizedExpected = expectedOutput.toLowerCase().replaceAll("\\s+", "").trim();
        String normalizedActual = actualOutput.toLowerCase().replaceAll("\\s+", "").trim();

        assertEquals(normalizedExpected, normalizedActual);
    }

    @Test
    public void execute_invalidDayIndex_errorMessage() {
        // Create a dummy menu
        ArrayList<Ingredient> ingredients = new ArrayList<>(Arrays.asList(new Ingredient("Lettuce", 100, "g"),
                new Ingredient("Chicken", 50, "g")));

        Dish dishChickenRice = new Dish("Chicken Rice", ingredients, 2.50f);
        Dish dishChickenChop = new Dish("Chicken Chop", ingredients, 5.00f);
        Menu menu = new Menu();
        menu.addDish(dishChickenRice);
        menu.addDish(dishChickenChop);

        // Create a dummy order for day 1
        Order order1 = new Order(dishChickenRice, 2);
        order1.setComplete(true);
        Order order2 = new Order(dishChickenChop, 1);
        order2.setComplete(true);

        // Create a dummy order list for day 1
        OrderList orderList1 = new OrderList();
        orderList1.addOrder(order1);
        orderList1.addOrder(order2);

        ArrayList<OrderList> orderLists= new ArrayList<>(Arrays.asList(orderList1));
        // Create a sales object and add the order lists
        Sales sales = new Sales(orderLists);

        Ui ui = new Ui();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream consoleStream = new PrintStream(baos);

        PrintStream originalOut = System.out;
        System.setOut(consoleStream);

        int dayToListSale = 200;
        ListSaleByDayCommand listSaleByDayCommand =
                new ListSaleByDayCommand(dayToListSale, ui, sales);
        listSaleByDayCommand.execute();

        String actualOutput = baos.toString().trim();
        System.setOut(originalOut);

        String expectedOutput = ErrorMessages.INVALID_SALE_DAY;;

        String normalizedExpected = expectedOutput.toLowerCase().replaceAll("\\s+", "").trim();
        String normalizedActual = actualOutput.toLowerCase().replaceAll("\\s+", "").trim();

        assertEquals(normalizedExpected, normalizedActual);
    }

    @Test
    public void execute_validDayNoSales_showNoSalesMessage() {
        // Create a dummy menu
        ArrayList<Ingredient> ingredients = new ArrayList<>(Arrays.asList(new Ingredient("Lettuce", 100, "g"),
                new Ingredient("Chicken", 50, "g")));

        Dish dishChickenRice = new Dish("Chicken Rice", ingredients, 2.50f);
        Dish dishChickenChop = new Dish("Chicken Chop", ingredients, 5.00f);
        Menu menu = new Menu();
        menu.addDish(dishChickenRice);
        menu.addDish(dishChickenChop);

        // Create a dummy order for day 1
        Order order1 = new Order(dishChickenRice, 2);
        order1.setComplete(true);
        Order order2 = new Order(dishChickenChop, 1);
        order2.setComplete(true);

        // Create a dummy order list for day 1
        OrderList orderList1 = new OrderList();
        orderList1.addOrder(order1);
        orderList1.addOrder(order2);

        OrderList orderList2 = new OrderList();

        OrderList orderList3 = new OrderList();
        orderList1.addOrder(order1);

        ArrayList<OrderList> orderLists= new ArrayList<>(Arrays.asList(orderList1, orderList2, orderList3));
        Sales sales = new Sales(orderLists);

        Ui ui = new Ui();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream consoleStream = new PrintStream(baos);

        PrintStream originalOut = System.out;
        System.setOut(consoleStream);

        int dayToListSale = 2;
        ListSaleByDayCommand listSaleByDayCommand =
                new ListSaleByDayCommand(dayToListSale, ui, sales);
        listSaleByDayCommand.execute();

        String actualOutput = baos.toString().trim();
        System.setOut(originalOut);

        String expectedOutput = "No sales for this day.";

        String normalizedExpected = expectedOutput.toLowerCase().replaceAll("\\s+", "").trim();
        String normalizedActual = actualOutput.toLowerCase().replaceAll("\\s+", "").trim();

        assertEquals(normalizedExpected, normalizedActual);
    }
}
