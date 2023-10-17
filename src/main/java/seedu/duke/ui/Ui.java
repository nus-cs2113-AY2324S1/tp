package seedu.duke.ui;

import seedu.duke.data.Menu;
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
     * @param menu The list of menu items.
     * @param dishIndex The index of the dish whose ingredients need to be printed.
     */
    public void printIngredients(Menu menu, int dishIndex) {
        Dish selectedDish = menu.getMenuItemsList().get(dishIndex - 1);
        if (selectedDish != null) {
            showToUser(selectedDish.getName() + " Ingredients: \n");

            for (Ingredient ingredient : selectedDish.getIngredients()) {
                showToUser(ingredient.getName() + " - " + ingredient.getQuantity());
            }
        } else {
            showToUser("Please select a valid dish index :)");
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
     * @param menuItem String of menu item deleted
     */
    public void showDeleteMessage(String menuItem) {
        showToUser("Okay! " + menuItem + " is deleted! :)");
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
}
