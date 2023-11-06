package seedu.stocker.commands;

import seedu.stocker.drugs.Drug;
import seedu.stocker.drugs.StockEntry;

/**
 * Adds a drug into the inventory
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new drug to the drug list. "
            + "Parameters: NAME, EXPIRY DATE, SERIAL NUMBER, QUANTITY,  " + System.lineSeparator()
            + "Example: " + COMMAND_WORD
            + " /n Doliprane /d 12/06/2035 /s ABC123 /q 52 /p 12.90";

    public static final String MESSAGE_SUCCESS = "New drug added in the inventory: %1$s";

    private final Drug toAdd;
    private final long quantity;
    private final String serialNumber;

    public AddCommand(String name, String expiryDate, String serialNumber, Long quantity, double sellingPrice) {
        this.toAdd = new Drug(name, expiryDate, sellingPrice);
        this.serialNumber = serialNumber;
        this.quantity = quantity;
    }

    public Drug getDrug() {
        return this.toAdd;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    @Override
    public CommandResult execute() {
        StockEntry matchingEntry = inventory.getStockEntries().stream()
            .filter(entry -> entry
                .getDrug().getName()
                .equalsIgnoreCase(this.toAdd.getName()))
            .findAny()
            .orElse(null);
        if (matchingEntry != null) {
            matchingEntry.incrQuantity(this.quantity);
            return new CommandResult<>(String.format(MESSAGE_SUCCESS, matchingEntry.getDrug().getName()));
        } else {
            inventory.addNewDrug(toAdd.getName().trim().toLowerCase(), toAdd, serialNumber, quantity);
            return new CommandResult<>(String.format(MESSAGE_SUCCESS, toAdd.getName()));
        }
    }
}
