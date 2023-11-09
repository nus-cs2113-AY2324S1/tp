package seedu.financialplanner.commands;

import seedu.financialplanner.commands.utils.Command;
import seedu.financialplanner.commands.utils.RawCommand;
import seedu.financialplanner.goal.WishList;
import seedu.financialplanner.utils.Ui;

@SuppressWarnings("unused")
public class WishListCommand extends Command {
    public static final String NAME = "wishlist";

    public static final String USAGE = "wishlist";
    public static final String EXAMPLE = "wishlist";

    public WishListCommand(RawCommand rawCommand) throws IllegalArgumentException {

    }

    @Override
    public void execute() {
        Ui ui = Ui.getInstance();
        WishList wishList = WishList.getInstance();
        ui.showMessage("Here is your wish list:");
        ui.showMessage(wishList.toString());
    }
}
