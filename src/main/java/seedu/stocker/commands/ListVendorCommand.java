package seedu.stocker.commands;

import seedu.stocker.vendors.Vendor;

import java.util.List;

/**
 * Generates a list of vendors tracked by system
 */
public class ListVendorCommand extends Command {

    public static final String COMMAND_WORD = "listVendors";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List all vendors that are being "
            + "tracked by the system." + System.lineSeparator()
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed all vendors in the list.";

    /**
     * Executes the list command.
     *
     * @return A CommandResult containing the success message and the list of drugs.
     */
    @Override
    public CommandResult<Vendor> execute() {
        // Retrieve the list of vendors from the inventory
        List<Vendor> vendorEntries = this.vendorsList.getVendorEntries();

        // Check if the inventory is empty
        if (vendorEntries.isEmpty()) {
            // Return a CommandResult indicating that the inventory is empty
            return new CommandResult<>("The inventory is empty.");
        } else {
            // Return a CommandResult with the success message and the list of vendors
            return new CommandResult<Vendor>(MESSAGE_SUCCESS, vendorEntries);
        }
    }
}
