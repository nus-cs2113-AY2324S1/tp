package seedu.stocker.commands;

import seedu.stocker.vendors.Vendor;

/**
 * Deletes a vendor from the inventory.
 */
public class DeleteVendorCommand extends Command {

    /**
     * The command word for deleting a vendor.
     */
    public static final String COMMAND_WORD = "deleteVendor";

    /**
     * Usage message for the deleteVendor command.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a vendor from the vendors list. "
            + "Parameter: NAME" + System.lineSeparator()
            + "Example: " + COMMAND_WORD
            + " /v Moderna";

    /**
     * Message for indicating successful deletion of a vendor.
     */
    public static final String MESSAGE_SUCCESS = "Vendor deleted from the vendors list: %1$s";

    /**
     * Message for indicating that the specified vendor was not found.
     */
    public static final String MESSAGE_VENDOR_NOT_FOUND = "Vendor not found in the vendors list: %1$s";

    /**
     * The name of the vendor to be deleted.
     */
    private final String vendorNameToDelete;

    /**
     * Constructs a DeleteVendorCommand object with the specified vendor name to delete.
     *
     * @param name The name of the vendor to be deleted.
     */
    public DeleteVendorCommand(String name) {
        this.vendorNameToDelete = name;
    }

    /**
     * Executes the DeleteVendorCommand to remove the specified vendor from the vendors list.
     *
     * @return A CommandResult indicating the outcome of the command execution.
     */
    @Override
    public <T> CommandResult<T> execute() {
        boolean vendorFound = false;
        Vendor vendorToDelete = null;

        // Iterate through the vendor entries to find the vendor to delete.
        for (Vendor vendor : vendorsList.getVendorEntries()) {
            if (vendor.getName().equalsIgnoreCase(vendorNameToDelete)) {
                vendorFound = true;
                vendorToDelete = vendor;
                break;
            }
        }

        if (vendorFound) {
            // Delete the vendor and return a success message.
            vendorsList.deleteVendor(vendorToDelete);
            return new CommandResult<>(String.format(MESSAGE_SUCCESS, vendorToDelete.getName()));
        } else {
            // Return an error message if the vendor was not found.
            return new CommandResult<>(String.format(MESSAGE_VENDOR_NOT_FOUND, vendorNameToDelete));
        }
    }
}
