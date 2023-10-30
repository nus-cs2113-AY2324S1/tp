package seedu.stocker.commands;

import seedu.stocker.drugs.Drug;

/**
 * Adds a drug into the inventory
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new drug to the drug list. "
            + "Parameters: NAME, EXPIRY DATE, SERIAL NUMBER, QUANTITY,  " + System.lineSeparator()
            + "Example: " + COMMAND_WORD
            + " /n Doliprane /d 12/06/2035 /s ABC123 /q 52";

    public static final String MESSAGE_SUCCESS = "New drug added in the inventory: %1$s";

    private final Drug toAdd;
    private final long quantity;
    private final String serialNumber;

    public AddCommand(String name, String expiryDate, String serialNumber, Long quantity) {
        this.toAdd = new Drug(name, expiryDate);
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
        inventory.addNewDrug(toAdd.getName(), toAdd, serialNumber, quantity);
        return new CommandResult<>(String.format(MESSAGE_SUCCESS, toAdd.getName()));
    }
}
