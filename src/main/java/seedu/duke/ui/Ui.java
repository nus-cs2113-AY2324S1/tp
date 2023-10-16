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
        System.out.println(UserOutput.WELCOME_MESSAGE.message);
    }

    public void showGoodbye() {
        System.out.println(UserOutput.GOODBYE_MESSAGE.message);
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
     * Shows messages(s) to the user
     * @param message string(s) of messages to print
     */
    public void showToUser(String... message) {
        for (String m: message) {
            System.out.println(m);
        }
    }
}
