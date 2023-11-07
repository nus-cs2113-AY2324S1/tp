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
    public static final String MESSAGE_FAILURE = "A different drugs already exists with differents properties. ";

    private final String name;
    private final String expiryDate;
    private final double sellingPrice;
    private final long quantity;
    private final String serialNumber;

    public AddCommand(String name, String expiryDate, String serialNumber, Long quantity, double sellingPrice) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.sellingPrice = sellingPrice;
        this.serialNumber = serialNumber;
        this.quantity = quantity;
    }

    @Override
    public <T> CommandResult<T> execute() {
        StockEntry entry = inventory.get(serialNumber);
        if (entry != null) {
            Drug existingDrug = entry.getDrug();
            if (existingDrug.getName().equals(this.name) &
                    existingDrug.getExpiryDate().equals(this.expiryDate) &
                    existingDrug.getSellingPrice() == this.sellingPrice) {
                entry.incrQuantity(this.quantity);
                return new CommandResult<>(String.format(MESSAGE_SUCCESS, entry.getDrug().getName()));
            } else {
                return new CommandResult<>(String.format(MESSAGE_FAILURE));
            }
        } else {
            Drug newDrug = new Drug(this.name, this.expiryDate, this.sellingPrice);
            inventory.addNewDrug(serialNumber, newDrug, quantity);
            return new CommandResult<>(String.format(MESSAGE_SUCCESS, newDrug.getName()));
        }
    }
}
