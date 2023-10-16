package seedu.duke.command;

import seedu.duke.data.Menu;
//import seedu.duke.data.dish.Dish;
import seedu.duke.ui.Ui;

import java.text.DecimalFormat;

/**
 * Lists all dishes in the menu to the user.
 */
public class ListMenuCommand extends Command {

    public static final String COMMAND_WORD = "list_menu";
    private static final DecimalFormat dollarValue = new DecimalFormat("0.00");
    /**
     * Iterates through the menu arraylist, outputting the dish name and dish price.
     *
     * @param menu ArrayList of Dishes
     * @param ui Handles the interactions with user
     */

    @Override
    public void execute(Menu menu, Ui ui) {
        /*for (String d : menu) {
            System.out.println(d);
        }*/
    };
}
