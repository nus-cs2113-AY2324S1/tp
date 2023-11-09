package seedu.stocker.commands;

import seedu.stocker.drugs.StockEntry;
import seedu.stocker.exceptions.DrugNotFoundException;

public class DeleteCommand  extends Command{

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a drug from drug list. "
            + "Parameters: Serial Number  " + System.lineSeparator()
            + "Example: " + COMMAND_WORD
            + " /s <Serial Number>";

    public static final String MESSAGE_SUCCESS = "Drug removed from inventory: %1$s";
    public static final String MESSAGE_FAILURE = "Drug not found in the inventory. ";



    private final String serialNumber;

    /**
     * Constructs a DeleteCommand with the specified drug name.
     *
     */
    public DeleteCommand(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * Executes the 'delete' command, removing a drug from the inventory list by name.
     *
     * @return A CommandResult indicating the result of the deletion operation.
     */
    @Override
    public <T> CommandResult<T> execute() {
        try {
            StockEntry deletedEntry = inventory.deleteDrug(this.serialNumber);
            return new CommandResult<>(String.format(MESSAGE_SUCCESS, deletedEntry.getDrug().getName()));
        } catch (DrugNotFoundException e) {
            return new CommandResult<>(MESSAGE_FAILURE);
        }
    }
}
