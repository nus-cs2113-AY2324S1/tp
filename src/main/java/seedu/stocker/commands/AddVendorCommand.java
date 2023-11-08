package seedu.stocker.commands;

import seedu.stocker.vendors.Vendor;

/**
 * Adds a vendor into the inventory
 */
public class AddVendorCommand extends Command {

    public static final String COMMAND_WORD = "addVendor";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new vendor to the vendors list. "
            + "Parameter: NAME" + System.lineSeparator()
            + "Example: " + COMMAND_WORD
            + " /v Moderna";

    public static final String MESSAGE_SUCCESS = "New vendor added in the vendors list: %1$s";

    private final Vendor toAdd;

    public AddVendorCommand(String name) {
        this.toAdd = new Vendor(name);
    }

    public boolean vendorAlreadyExist(String name) {
        for (int i = 0; i < this.vendorsList.vendorArrayList.size(); i += 1) {
            String vendorName = vendorsList.vendorArrayList.get(i).getName();

            if (vendorName.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public <T> CommandResult<T> execute() {
        if (vendorAlreadyExist(toAdd.getName())) {
            return new CommandResult<>("No Duplicates allowed!");
        }

        this.vendorsList.addNewVendor(toAdd);
        return new CommandResult<>(String.format(MESSAGE_SUCCESS, toAdd.getName()));
    }
}
