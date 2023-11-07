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

    @Override
    public <T> CommandResult<T> execute() {
        StockEntry entry = inventory.get(serialNumber);
        if (entry != null) {
            entry.incrQuantity(this.quantity);
            return new CommandResult<>(String.format(MESSAGE_SUCCESS, entry.getDrug().getName()));
        } else {
            inventory.addNewDrug(serialNumber, toAdd, quantity);
            return new CommandResult<>(String.format(MESSAGE_SUCCESS, toAdd.getName()));
        }
    }
}
