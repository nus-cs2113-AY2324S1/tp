package seedu.stocker.commands;

import seedu.stocker.vendors.VendorSupplyList;

/**
 * Deletes a drug from a vendor's supply list.
 */
public class DeleteVendorSupplyCommand extends Command {

    public static final String COMMAND_WORD = "deleteVendorSupply";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a drug from a vendor's supply list. "
            + "Parameters: VENDOR_NAME, DRUG_NAME" + System.lineSeparator()
            + "Example: " + COMMAND_WORD + " /v Moderna /n Paracetamol";

    public static final String MESSAGE_SUCCESS = "Drug '%2$s' removed from %1$s's supply list";
    public static final String MESSAGE_VENDOR_NOT_FOUND = "Vendor not found: %1$s";
    public static final String MESSAGE_DRUG_NOT_FOUND = "The drug '%1$s' is not in the vendor's supply list";

    private final String vendorName;
    private final String drugName;

    /**
     * Creates a DeleteVendorSupplyCommand to remove a drug from a vendor's supply list.
     *
     * @param vendorName The name of the vendor.
     * @param drugName   The name of the drug to be removed from the vendor's supply list.
     */
    public DeleteVendorSupplyCommand(String vendorName, String drugName) {
        this.vendorName = vendorName;
        this.drugName = drugName;
    }

    /**
     * Executes the command to remove a drug from the specified vendor's supply list.
     *
     * @return CommandResult indicating the success of a drug removal depending on whether vendor and drug are found.
     */
    @Override
    public <T> CommandResult<T> execute() {
        String lowercaseVendorName = vendorName.toLowerCase();
        String lowercaseDrugName = drugName.toLowerCase();

        if (this.vendorsList.getVendorEntries().stream()
                .noneMatch(vendor -> vendor.getName().equalsIgnoreCase(lowercaseVendorName))) {
            return new CommandResult<>(String.format(MESSAGE_VENDOR_NOT_FOUND, lowercaseVendorName));
        }

        boolean removed = VendorSupplyList.removeDrugFromVendor(lowercaseVendorName, lowercaseDrugName);

        if (removed) {
            return new CommandResult<>(String.format(MESSAGE_SUCCESS, lowercaseVendorName, lowercaseDrugName));
        } else {
            return new CommandResult<>(String.format(MESSAGE_DRUG_NOT_FOUND, lowercaseDrugName));
        }
    }
}
