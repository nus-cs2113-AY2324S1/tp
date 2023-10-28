package seedu.stocker.commands;

import seedu.stocker.vendors.Vendor;
import seedu.stocker.vendors.VendorsList;

/**
 * Adds a drug into the inventory
 */
public class AddVendorCommand extends Command {

    public static final String COMMAND_WORD = "addVendor";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new vendor to the vendors list. "
            + "Parameter: NAME " + System.lineSeparator()
            + "Example: " + COMMAND_WORD
            + " Moderna";

    public static final String MESSAGE_SUCCESS = "New vendor added in the vendors list: %1$s";

    private final Vendor toAdd;

    public AddVendorCommand(String name) {
        this.toAdd = new Vendor(name);
    }

    @Override
    public CommandResult execute() {
        VendorsList.addNewVendor(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.getName()));
    }
}
