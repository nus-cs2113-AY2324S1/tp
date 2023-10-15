package seedu.stocker.commands;

import seedu.stocker.drugs.Drug;

/**
 * Adds a drug into the inventory
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new drug to the drug list. "
            + "Parameters: NAME, EXPIRY DATE, QUANTITY,  " + System.lineSeparator()
            + "Example: " + COMMAND_WORD
            + " /n Doliprane /d 12/06/2035 /q 52";

    public static final String MESSAGE_SUCCESS = "New drug added in the inventory: %1$s";

    private final Drug toAdd;

    public AddCommand(String name, String expiryDate, Long quantity) {
        this.toAdd = new Drug(name, expiryDate, quantity);
    }

    public Drug getDrug() {
        return this.toAdd;
    }

    @Override
    public CommandResult execute() {
        inventory.addDrug(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.name));
    }
}
