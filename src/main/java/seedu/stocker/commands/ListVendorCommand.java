package seedu.stocker.commands;

import seedu.stocker.vendors.Vendor;
import seedu.stocker.vendors.VendorsList;

import java.util.List;

/**
 * Generates a list of vendors tracked by system
 */
public class ListVendorCommand extends Command {

    public static final String COMMAND_WORD = "listVendors";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List all vendor information that is being "
            + "tracked by the system." + System.lineSeparator()
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed all vendors in the list.";

    /**
     * Executes the list command.
     *
     * @return A CommandResult containing the success message and the list of drugs.
     */
    @Override
    public CommandResult execute() {
        // Retrieve the list of drugs from the inventory
        List<Vendor> vendorEntries = VendorsList.getVendorEntries();

        // Check if the inventory is empty
        if (vendorEntries.isEmpty()) {
            // Return a CommandResult indicating that the inventory is empty
            return new CommandResult<>("The inventory is empty.");
        } else {
            // Return a CommandResult with the success message and the list of drugs
            return new CommandResult<>(MESSAGE_SUCCESS, vendorEntries);
        }
    }
}
