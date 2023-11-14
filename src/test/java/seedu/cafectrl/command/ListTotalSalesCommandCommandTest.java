package seedu.cafectrl.command;

import org.junit.jupiter.api.Test;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Order;
import seedu.cafectrl.data.OrderList;
import seedu.cafectrl.data.Sales;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.Messages;
import seedu.cafectrl.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListTotalSalesCommandCommandTest {

    @Test
    public void execute_existingSales_listTotalSales() {
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

        // Create a dummy order list for day 2
        OrderList orderList2 = new OrderList();

        // Create a dummy order for day 3
        Order order3 = new Order(dishChickenRice, 4);
        order3.setComplete(false);
        Order order4 = new Order(dishChickenChop, 1);
        order4.setComplete(true);
        Order order5 = new Order(dishChickenChop, 1);
        order5.setComplete(true);

        // Create a dummy order list for day 3
        OrderList orderList3 = new OrderList();
        orderList3.addOrder(order3);
        orderList3.addOrder(order4);
        orderList3.addOrder(order5);

        ArrayList<OrderList> orderLists= new ArrayList<>(Arrays.asList(orderList1, orderList2, orderList3));
        // Create a sales object and add the order lists
        Sales sales = new Sales(orderLists);


        Ui ui = new Ui();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream consoleStream = new PrintStream(baos);

        PrintStream originalOut = System.out;
        System.setOut(consoleStream);

        ListTotalSalesCommand listTotalSalesCommand =
                new ListTotalSalesCommand(sales, ui);
        listTotalSalesCommand.execute();

        String actualOutput = baos.toString().trim();
        System.setOut(originalOut);

        String expectedOutput = Messages.SHOW_SALES_END_CAP
                + Messages.SHOW_SALES_DAY_PART_1
                + "1"
                + Messages.SHOW_SALES_DAY_PART_2
                + Messages.SHOW_SALES_CORNER
                + Messages.SHOW_SALES_TITLE
                + Messages.SHOW_SALES_CORNER
                + "| chicken rice                           | 2            | 5.00              |"
                + "| chicken chop                           | 1            | 5.00              |"
                + Messages.SHOW_SALES_END_CAP
                + "| Total for day:                                        | $10.00            |"
                + Messages.SHOW_SALES_END_CAP
                + "No sales for day 2."
                + Messages.SHOW_SALES_END_CAP
                + Messages.SHOW_SALES_DAY_PART_1
                + "3"
                + Messages.SHOW_SALES_DAY_PART_2
                + Messages.SHOW_SALES_CORNER
                + Messages.SHOW_SALES_TITLE
                + Messages.SHOW_SALES_CORNER
                + "| chicken chop                           | 2            | 10.00             |"
                + Messages.SHOW_SALES_END_CAP
                + "| Total for day:                                        | $10.00            |"
                + Messages.SHOW_SALES_END_CAP;

        String normalizedExpected = expectedOutput.toLowerCase().replaceAll("\\s+", "").trim();
        String normalizedActual = actualOutput.toLowerCase().replaceAll("\\s+", "").trim();

        assertEquals(normalizedExpected, normalizedActual);
    }

    @Test
    public void execute_noSales_showNoSalesMessage() {

        Menu menu = new Menu();
        OrderList orderList1 = new OrderList();
        ArrayList<OrderList> orderLists= new ArrayList<>(List.of(orderList1));
        Sales sales = new Sales(orderLists);

        Ui ui = new Ui();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream consoleStream = new PrintStream(baos);

        PrintStream originalOut = System.out;
        System.setOut(consoleStream);

        ListTotalSalesCommand listTotalSalesCommand =
                new ListTotalSalesCommand(sales, ui);
        listTotalSalesCommand.execute();

        String actualOutput = baos.toString().trim();
        System.setOut(originalOut);

        String expectedOutput = "No sales made.";

        String normalizedExpected = expectedOutput.toLowerCase().replaceAll("\\s+", "").trim();
        String normalizedActual = actualOutput.toLowerCase().replaceAll("\\s+", "").trim();

        assertEquals(normalizedExpected, normalizedActual);
    }
}
