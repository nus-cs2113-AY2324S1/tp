package seedu.duke.ui;

import seedu.duke.data.dish.Dish;
import seedu.duke.data.dish.Ingredient;

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

    public String receiveUserInput() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        showToUser(UserOutput.WELCOME_MESSAGE.message);
    }

    public void showGoodbye() {
        showToUser(UserOutput.GOODBYE_MESSAGE.message);
    }

    /**
     * Prints out the quantity of each ingredient needed for the
     * dish that the user selects.
     *
     * @param selectedDish Dish for ingredients to be listed out.
     */
    public void printIngredients(Dish selectedDish) {
        showToUser(selectedDish.getName() + " Ingredients: \n");

        for (Ingredient ingredient : selectedDish.getIngredients()) {
            showToUser(ingredient.getName() + " - " + ingredient.getQuantity());
        }
    }

    public void printAddDishMessage(Dish dish) {
        String dishNameString = "Dish Name: " + dish.getName();
        String dishPriceString = "Dish Price: $" + dish.getPrice();
        StringBuilder dishIngredientsString = new StringBuilder("Ingredients:\n");

        for (int i = 0; i < dish.getIngredients().size(); i++) {
            Ingredient ingredient = dish.getIngredients().get(i);

            dishIngredientsString.append("\t")
                    .append(i + OFFSET_LIST_INDEX)
                    .append(". ")
                    .append(ingredient.toString())
                    .append("\n");
        }

        showToUser(UserOutput.ADD_DISH_MESSAGE.message,
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
    public static void showToUser(String... message) {
        for (String m: message) {
            System.out.println(m);
        }
    }

    /**
     * show edit price message to user
     * @param menuItem menuItem that has been modified
     */
    public static void showEditPriceMessage(String menuItem) {
        System.out.println(Messages.PRICE_MODIFIED_MESSAGE);
        showToUser(menuItem);
    }
}
