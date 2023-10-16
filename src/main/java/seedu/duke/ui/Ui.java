package seedu.duke.ui;

import seedu.duke.data.Menu;
import seedu.duke.data.dish.Dish;

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
        System.out.println(UserOutput.WELCOME_MESSAGE.message);
    }

    public void showGoodbye() {
        System.out.println(UserOutput.GOODBYE_MESSAGE.message);
    }

    public static void listIngredients(Menu menu) {
        int index = 0;
        for (Dish dish : menu.getMenuItemsList()) {
            if (dish != null) {
                System.out.print("    " + ++index + ". ");
                System.out.println(dish);
                dish.getIngredients().forEach(System.out::println);
            }
        }
    }
}
