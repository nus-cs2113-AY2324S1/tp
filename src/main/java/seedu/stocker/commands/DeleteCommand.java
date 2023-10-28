package seedu.stocker.commands;

import seedu.stocker.drugs.StockEntry;
import seedu.stocker.exceptions.DrugNotFoundException;

public class DeleteCommand  extends Command{

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a drug from drug list. "
            + "Parameters: Name  " + System.lineSeparator()
            + "Example: " + COMMAND_WORD
            + " <Drug Name>";

    public static final String MESSAGE_SUCCESS = "Drug removed from inventory: %1$s";
    public static final String MESSAGE_FAILURE = "Drug not find in the inventory. ";



    private final String keyToDelete;

    /**
     * Constructs a DeleteCommand with the specified drug name.
     *
     */
    public DeleteCommand(String key) {
        this.keyToDelete = key.trim().toLowerCase();
    }

    /**
     * Executes the 'delete' command, removing a drug from the inventory list by name.
     *
     * @return A CommandResult indicating the result of the deletion operation.
     */
    @Override
    public CommandResult<StockEntry> execute() {
        try {
            StockEntry deletedEntry = inventory.deleteDrug(this.keyToDelete);
            return new CommandResult<>(String.format(MESSAGE_SUCCESS, deletedEntry.getDrug().getName()));
        } catch (DrugNotFoundException e) {
            return new CommandResult<>(MESSAGE_FAILURE);
        }
    }
}
