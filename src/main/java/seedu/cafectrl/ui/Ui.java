package seedu.cafectrl.ui;

import seedu.cafectrl.command.AddDishCommand;
import seedu.cafectrl.command.AddOrderCommand;
import seedu.cafectrl.command.BuyIngredientCommand;
import seedu.cafectrl.command.DeleteDishCommand;
import seedu.cafectrl.command.EditPriceCommand;
import seedu.cafectrl.command.ExitCommand;
import seedu.cafectrl.command.HelpCommand;
import seedu.cafectrl.command.ListIngredientCommand;
import seedu.cafectrl.command.ListMenuCommand;
import seedu.cafectrl.command.ListSaleByDayCommand;
import seedu.cafectrl.command.ListTotalSalesCommand;
import seedu.cafectrl.command.NextDayCommand;
import seedu.cafectrl.command.PreviousDayCommand;
import seedu.cafectrl.command.ViewTotalStockCommand;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.data.dish.Ingredient;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public static final int OFFSET_LIST_INDEX = 1;
    public static final String DECIMAL_POINT_STRING = ". ";
    public static final String DOLLAR_SIGH_STRING = " $";
    public static final String DISH_LEFT_ALIGN_FORMAT = "|%-55s|";
    public static final String USER_INPUT_CHARACTER = "> ";
    private final Scanner scanner;

    /**
     * Constructs a UI instance with a Scanner for user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void printLine() {
        showToUser(Messages.LINE_STRING);
    }

    public String receiveUserInput() {
        System.out.print(USER_INPUT_CHARACTER);
        return scanner.nextLine();
    }

    public void showWelcome() {
        showToUser(Messages.WELCOME_MESSAGE);
    }

    public void showGoodbye() {
        showToUser(Messages.GOODBYE_MESSAGE);
    }

    //@@author NaychiMin
    /**
     * Prints out the quantity of each ingredient needed for the
     * dish in a table format.
     *
     * @param dish Dish for ingredients to be listed out.
     */
    public void showListIngredientsMessage(Dish dish) {
        showDishNameHeader(dish);
        showIngredientList(dish);
        showIngredientsEndCap();
    }

    public void showDishNameHeader(Dish dish) {
        String dishNameString = String.format(DISH_LEFT_ALIGN_FORMAT, " Dish: " + dish.getName());
        showToUser(Messages.INGREDIENTS_END_CAP,
                dishNameString,
                Messages.INGREDIENTS_CORNER);
    }

    public void showIngredientList(Dish dish) {
        showToUser(Messages.INGREDIENTS_TITLE,
                Messages.INGREDIENTS_CORNER);

        ArrayList<Ingredient> ingredients = dish.getIngredients();
        for (Ingredient ingredient : ingredients) {
            formatListIngredient(ingredient.getName(), ingredient.getQty() + ingredient.getUnit());
        }
    }

    public void showIngredientsEndCap() {
        showToUser(Messages.INGREDIENTS_END_CAP);
    }

    //@@author DextheChik3n
    public void printAddDishMessage(Dish dish) {
        showToUser(Messages.ADD_DISH_MESSAGE);
        showDishNameHeader(dish);
        showDishPrice(dish);
        showIngredientList(dish);
        showIngredientsEndCap();
    }

    public void showDishPrice(Dish dish) {
        String dishPriceString = String.format(DISH_LEFT_ALIGN_FORMAT, " Price: $" + dish.getPriceString());
        showToUser(dishPriceString, Messages.INGREDIENTS_CORNER);
    }

    //@@author ShaniceTang
    /**
     * Shows delete message to user
     *
     * @param selectedDish Dish to be deleted
     */
    public void printDeleteMessage(Dish selectedDish) {
        showToUser("Okay! " + selectedDish.getName() + " is deleted! :)");
    }

    public void printBuyIngredientHeader() {
        showToUser(Messages.BUY_INGREDIENT_HEADER);
    }

    /**
     * Shows messages(s) to the user
     * @param message string(s) of messages to print
     */
    public void showToUser(String... message) {
        for (String m: message) {
            System.out.println(m);
        }
    }

    /**
     * Shows menu to user is table format
     *
     * @param dishName name text of the dish
     * @param dishPrice price text of the dish
     */
    public void formatListMenu(String dishName, String dishPrice) {
        String leftAlignFormat = "| %-38s | %-12s |%n";
        System.out.format(leftAlignFormat, dishName, dishPrice);
    }

    /**
     * show edit price message to user
     *
     * @param menuItem menuItem that has been modified
     */
    public void showEditPriceMessage(String menuItem) {
        this.showToUser(Messages.PRICE_MODIFIED_MESSAGE, menuItem);
    }

    public void showHelp() {
        showToUserWithSpaceBetweenLines(Messages.LIST_OF_COMMANDS,
                Messages.INSTRUCTION_ON_COMMAND_FORMAT);

        showToUser(Messages.LINE_STRING);
        ArrayList<String> usagesTexts = new ArrayList<>();

        usagesTexts.add(AddDishCommand.MESSAGE_USAGE);
        usagesTexts.add(DeleteDishCommand.MESSAGE_USAGE);
        usagesTexts.add(EditPriceCommand.MESSAGE_USAGE);
        usagesTexts.add(ListMenuCommand.MESSAGE_USAGE);
        usagesTexts.add(ListIngredientCommand.MESSAGE_USAGE);
        usagesTexts.add(BuyIngredientCommand.MESSAGE_USAGE);
        usagesTexts.add(ViewTotalStockCommand.MESSAGE_USAGE);
        usagesTexts.add(AddOrderCommand.MESSAGE_USAGE);
        usagesTexts.add(ListTotalSalesCommand.MESSAGE_USAGE);
        usagesTexts.add(ListSaleByDayCommand.MESSAGE_USAGE);
        usagesTexts.add(NextDayCommand.MESSAGE_USAGE);
        usagesTexts.add(PreviousDayCommand.MESSAGE_USAGE);
        usagesTexts.add(ExitCommand.MESSAGE_USAGE);
        usagesTexts.add(HelpCommand.MESSAGE_USAGE);

        showToUserWithSpaceBetweenLines(usagesTexts);
    }

    public void showToUserWithSpaceBetweenLines(String... message) {
        for (String m: message) {
            System.out.println(m + "\n");
        }
    }

    public void showToUserWithSpaceBetweenLines(ArrayList<String> message) {
        for (String m: message) {
            System.out.println(m);
            System.out.println(Messages.LINE_STRING);
        }
    }

    public void showDishAvailability(int numberOfDishes){
        showToUser("Available quantity: " + numberOfDishes);
    }

    public void showNeededRestock(String ingredientName, int currentQuantity, String unit, int neededIngredient) {
        String rowFormat = "| %-38s | %-12s | %-12s |%n";
        System.out.format(rowFormat, ingredientName, currentQuantity + unit, neededIngredient + unit);
        showToUser(Messages.RESTOCK_END_CAP);
    }

    /**
     * Shows the top portion of the menu
     */
    public void showMenuTop() {
        showToUser(Messages.MENU_END_CAP, Messages.LIST_MENU_MESSAGE,
                Messages.MENU_CORNER, Messages.MENU_TITLE, Messages.MENU_CORNER);
    }

    /**
     * Shows the bottom portion of the menu
     */
    public void showMenuBottom() {
        showToUser(Messages.MENU_END_CAP);
    }

    /**
     * Shows the message for empty menu
     */
    public void showEmptyMenu() {
        showToUser(Messages.MENU_EMPTY_MESSAGE);
    }

    /**
     * Shows the dishes in the menu, formatted in the proper format
     *
     * @param indexNum The index number of the dish in the menu print
     * @param dishName The name of the dish in the menu
     * @param dishPrice The price of the dish in the menu
     */
    public void showMenuDish(String indexNum, String dishName, String dishPrice) {
        formatListMenu(indexNum + DECIMAL_POINT_STRING + dishName, DOLLAR_SIGH_STRING + dishPrice);
    }

    public void showIngredientTop() {
        showToUser(Messages.MENU_END_CAP, Messages.VIEW_STOCK_MESSAGE,
                Messages.MENU_CORNER, Messages.VIEW_STOCK_TITLE_MESSAGE, Messages.MENU_CORNER);
    }

    public void showIngredientStock(String ingredientName, int ingredientQty, String ingredientUnit) {
        formatListIngredient(ingredientName, ingredientQty + ingredientUnit);
    }

    public void formatListIngredient(String ingredientName, String ingredientAmount) {
        String leftAlignFormat = "| %-38s | %-12s |%n";
        System.out.format(leftAlignFormat, ingredientName, ingredientAmount);
    }

    public void showSalesTop(int day) {
        showToUser(Messages.SHOW_SALES_END_CAP, Messages.SHOW_SALES_DAY_PART_1 + day + Messages.SHOW_SALES_DAY_PART_2,
                Messages.SHOW_SALES_CORNER, Messages.SHOW_SALES_TITLE, Messages.SHOW_SALES_CORNER);
    }
    public void showSalesBottom() {
        showToUser(Messages.SHOW_SALES_END_CAP);
    }
    public void showSalesAll(String dishName, int dishQty, String dishPrice) {
        formatShowSales(dishName, dishQty, dishPrice);
    }

    public void formatShowSales(String dishName, int dishQty, String dishPrice) {
        String leftAlignFormat = "| %-38s | %-12s | %-17s |%n";
        System.out.format(leftAlignFormat, dishName, dishQty, dishPrice);
    }
    public void showSalesCost(String front, String back) {
        String leftAlignFormat = "| %-53s | %-17s |%n";
        System.out.format(leftAlignFormat, front, back);
    }

    public void showChefMessage() {
        showToUser(Messages.CHEF_MESSAGE);
    }

    /**
     * Shows the total cost in the order list, formatted in the proper format
     *
     * @param dollarCost The price of the orders
     */
    public void showTotalCost(String dollarCost) {
        showToUser("Total order cost: $" + dollarCost);
    }

    public void showOrderStatus(String totalCost) {
        printLine();
        showToUser(Messages.COMPLETE_ORDER);
        showTotalCost(totalCost);
        printLine();
        showDishAvailabilityMessage();
    }
    public void showIncompleteOrder() {
        showToUser(Messages.INCOMPLETE_ORDER);
    }

    public void showDishAvailabilityMessage() {
        showToUser(Messages.AVAILABLE_DISHES,
                Messages.EQUAL_LINE_STRING);
    }
    public void showPreviousDay() {
        showToUser(Messages.PREVIOUS_DAY_COMMAND_MESSAGE);
    }
    public void showNextDay() {
        showToUser(Messages.NEXT_DAY_COMMAND_MESSAGE);
    }
}
