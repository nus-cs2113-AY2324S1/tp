package seedu.duke.ui;

import seedu.duke.data.Menu;
import seedu.duke.data.dish.Dish;
import seedu.duke.data.dish.Ingredient;

import java.util.Scanner;

public class Ui {
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

    /** Shows messages(s) to the user **/
    public void showToUser(String... message) {
        for (String m: message) {
            System.out.println(m);
        }
    }
}
