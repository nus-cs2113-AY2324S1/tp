package seedu.financialplanner.commands;

import seedu.financialplanner.commands.utils.Command;
import seedu.financialplanner.commands.utils.RawCommand;
import seedu.financialplanner.goal.WishList;
import seedu.financialplanner.utils.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("unused")
public class WishListCommand extends Command {
    public static final String NAME = "wishlist";

    public static final String USAGE = "wishlist";
    public static final String EXAMPLE = "wishlist";

    private static final Logger logger = Logger.getLogger("Financial Planner Logger");

    /**
     * Constructor of the command to list goals.
     *
     * @param rawCommand The input from the user.
     * @throws IllegalArgumentException if erroneous inputs are detected.
     */
    public WishListCommand(RawCommand rawCommand) throws IllegalArgumentException {
        if (!rawCommand.extraArgs.isEmpty()) {
            String unknownExtraArgument = new java.util.ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            logger.log(Level.WARNING, "Invalid extra arguments found");
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }

    /**
     * Executes the command to list the goals.
     */
    @Override
    public void execute() {
        Ui ui = Ui.getInstance();
        WishList wishList = WishList.getInstance();
        if (wishList.list.isEmpty()) {
            ui.showMessage("You have no wish list.");
            return;
        }
        ui.showMessage("Here is your wish list:");
        ui.showMessage(wishList.toString());
    }
}
