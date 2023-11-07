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
import seedu.cafectrl.command.NextDayCommand;
import seedu.cafectrl.command.PreviousDayCommand;
import seedu.cafectrl.command.ShowSalesByDayCommand;
import seedu.cafectrl.command.ViewTotalStockCommand;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.data.dish.Ingredient;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Ui {
    public static final int OFFSET_LIST_INDEX = 1;
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
        System.out.print("> ");
        return scanner.nextLine();
    }

    public void showWelcome() {
        showToUser(Messages.WELCOME_MESSAGE);
    }

    public void showGoodbye() {
        showToUser(Messages.GOODBYE_MESSAGE);
    }

    /**
     * Prints out the quantity of each ingredient needed for the
     * dish that the user selects.
     *
     * @param selectedDish Dish for ingredients to be listed out.
     */
    public void printIngredients(Dish selectedDish) {
        String ingredientsString = selectedDish.getName() + " Ingredients: \n";

        for (Ingredient ingredient : selectedDish.getIngredients()) {
            ingredientsString += ingredient.toString() + "\n";
        }

        showToUser(ingredientsString.trim());
    }

    public void printAddDishMessage(Dish dish) {
        String dishNameString = "Dish Name: " + dish.getName();
        DecimalFormat dollarValue = new DecimalFormat("0.00");
        float dishPrice = dish.getPrice();
        String dishPriceString = "Dish Price: $" + dollarValue.format(dishPrice);

        showToUser(Messages.ADD_DISH_MESSAGE,
                dishNameString,
                dishPriceString);

        printIngredients(dish);
    }

    /**
     * Shows delete message to user
     *
     * @param selectedDish Dish to be deleted
     */
    public void printDeleteMessage(Dish selectedDish) {
        showToUser("Okay! " + selectedDish.getName() + " is deleted! :)");
    }

    public void printBuyIngredientHeader() {
        showToUser("Added to stock:");
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
     * @param dishName
     * @param dishPrice
     */
    public void formatListMenu(String dishName, String dishPrice) {
        String leftAlignFormat = "| %-38s | %-12s |%n";
        System.out.format(leftAlignFormat, dishName, dishPrice);
    }
    //+-----------------+------+

    /**
     * show edit price message to user
     * @param menuItem menuItem that has been modified
     */
    public void showEditPriceMessage(String menuItem) {
        this.showToUser(Messages.PRICE_MODIFIED_MESSAGE, menuItem);
    }

    public void showHelp() {
        showToUserWithSpaceBetweenLines(Messages.LIST_OF_COMMANDS,
                Messages.INSTRUCTION_ON_COMMAND_FORMAT,
                AddDishCommand.MESSAGE_USAGE,
                AddOrderCommand.MESSAGE_USAGE,
                BuyIngredientCommand.MESSAGE_USAGE,
                DeleteDishCommand.MESSAGE_USAGE,
                EditPriceCommand.MESSAGE_USAGE,
                ExitCommand.MESSAGE_USAGE,
                HelpCommand.MESSAGE_USAGE,
                ListIngredientCommand.MESSAGE_USAGE,
                ListMenuCommand.MESSAGE_USAGE,
                NextDayCommand.MESSAGE_USAGE,
                PreviousDayCommand.MESSAGE_USAGE,
                ViewTotalStockCommand.MESSAGE_USAGE,
                ShowSalesByDayCommand.MESSAGE_USAGE);
    }

    public void showToUserWithSpaceBetweenLines(String... message) {
        for (String m: message) {
            System.out.println(m + "\n");
        }
    }

    public void showDishAvailability(int numberOfDishes){
        showToUser("Available Dishes: " + numberOfDishes);
    }

    public void showNeededRestock(String ingredientName, int currentQuantity, String unit, String neededIngredient){
        showToUser("Please Restock: " + ingredientName
                , "Current " + ingredientName + ": " + currentQuantity + unit
                , "Needed " + ingredientName + ": " + neededIngredient);
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
        formatListMenu(indexNum + ". " + dishName," $" + dishPrice);
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

    public void showOrderStatus(String orderStatus, String totalCost) {
        printLine();
        showToUser(orderStatus);
        showTotalCost(totalCost);
        printLine();
        showDishAvailabilityMessage();
    }

    public void showDishAvailabilityMessage() {
        showToUser(Messages.AVAILABLE_DISHES);
    }
    public void showPreviousDay() {
        showToUser(Messages.PREVIOUS_DAY_COMMAND_MESSAGE);
    }
    public void showNextDay() {
        showToUser(Messages.NEXT_DAY_COMMAND_MESSAGE);
    }
}
