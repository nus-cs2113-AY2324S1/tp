package seedu.stocker.commands;

import seedu.stocker.drugs.Drug;

import java.util.List;

public class DeleteCommand  extends Command{

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a drug from drug list. "
            + "Parameters: Name  " + System.lineSeparator()
            + "Example: " + COMMAND_WORD
            + " <Drug Name>";

    public static final String MESSAGE_SUCCESS = "Drug removed from inventory: %1$s";



    private final String nameToDelete;

    /**
     * Constructs a DeleteCommand with the specified drug name.
     *
     * @param name The name of the drug to be deleted.
     */
    public DeleteCommand(String name) {
        this.nameToDelete = name;
    }

    /**
     * Executes the 'delete' command, removing a drug from the inventory list by name.
     *
     * @return A CommandResult indicating the result of the deletion operation.
     */
    @Override
    public CommandResult execute() {
        List<Drug> drugList = inventory.getAllDrugs();

        // Find the drug by name and remove it from the list
        Drug drugToRemove = null;
        for (Drug drug : drugList) {
            if (drug.getName().equalsIgnoreCase(nameToDelete)) {
                drugToRemove = drug;
                break;
            }
        }

        if (drugToRemove != null) {
            drugList.remove(drugToRemove);
            return new CommandResult(String.format(MESSAGE_SUCCESS, drugToRemove));
        } else {
            return new CommandResult("Drug not found in inventory.");
        }
    }
}