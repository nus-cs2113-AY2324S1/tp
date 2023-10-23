package seedu.cafectrl.ui;

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
        StringBuilder dishIngredientsString = new StringBuilder("Ingredients:\n");

        for (int i = 0; i < dish.getIngredients().size(); i++) {
            Ingredient ingredient = dish.getIngredients().get(i);

            dishIngredientsString.append("\t")
                    .append(i + OFFSET_LIST_INDEX)
                    .append(". ")
                    .append(ingredient.toString())
                    .append("\n");
        }

        showToUser(Messages.ADD_DISH_MESSAGE,
                dishNameString,
                dishPriceString,
                dishIngredientsString.toString());
    }

    /**
     * Shows delete message to user
     *
     * @param selectedDish Dish to be deleted
     */
    public void showDeleteMessage(Dish selectedDish) {
        showToUser("Okay! " + selectedDish.getName() + " is deleted! :)");
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
        String leftAlignFormat = "| %-24s | %-12s |%n";
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
        showToUserWithSpaceBetweenLines(Messages.LIST_OF_COMMANDS, Messages.INSTRUCTION_ON_COMMAND_FORMAT,
                Messages.ADD_DISH_GUIDE, Messages.LIST_MENU_GUIDE, Messages.LIST_INGREDIENTS_GUIDE,
                Messages.DELETE_GUIDE, Messages.EDIT_PRICE_GUIDE);
    }

    public void showToUserWithSpaceBetweenLines(String... message) {
        for (String m: message) {
            System.out.println(m + "\n");
        }
    }
}
