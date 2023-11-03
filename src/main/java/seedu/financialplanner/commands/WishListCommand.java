package seedu.financialplanner.commands;

import seedu.financialplanner.goal.WishList;
import seedu.financialplanner.utils.Ui;
public class WishListCommand extends Command {
    public WishListCommand(RawCommand rawCommand) throws IllegalArgumentException{

    }

    @Override
    public void execute() {
        Ui ui = Ui.getInstance();
        WishList wishList = WishList.getInstance();
        ui.showMessage("Here is your wish list:");
        ui.showMessage(wishList.toString());
    }
}
