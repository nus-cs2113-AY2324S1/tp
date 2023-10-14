package seedu.stocker.commands;

import seedu.stocker.drugs.Drug;
import java.util.List;

/**
 * Represents a command to list all drugs in the inventory.
 * This command retrieves the list of drugs from the inventory and provides it as part of the command result.
 * If the inventory is empty, it informs the user that the inventory has no drugs.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List all drug information that is being "
            + "tracked by the system. \n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed all drugs in the inventory.";

    /**
     * Executes the list command.
     *
     * @return A CommandResult containing the success message and the list of drugs.
     *         If the inventory is empty, the success message indicates that the inventory is empty.
     */
    @Override
    public CommandResult execute() {
        // Retrieve the list of drugs from the inventory
        List<Drug> drugs = inventory.getAllDrugs();

        // Check if the inventory is empty
        if (drugs.isEmpty()) {
            // Return a CommandResult indicating that the inventory is empty
            return new CommandResult("The inventory is empty.");
        } else {
            // Return a CommandResult with the success message and the list of drugs
            return new CommandResult(MESSAGE_SUCCESS, drugs);
        }
    }
}
