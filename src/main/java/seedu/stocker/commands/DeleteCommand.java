package seedu.stocker.commands;

public class DeleteCommand  extends Command{

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a drug from drug list. "
            + "Parameters: Name  \n"
            + "Example: " + COMMAND_WORD
            + " <Drug Name>";

    public static final String MESSAGE_SUCCESS = "Drug removed from inventory: %1$s";

    //Edit functions below to give delete functionality

    // public AddCommand(String name, String expiryDate, Long quantity) {
    //     this.toAdd = new Drug(name, expiryDate, quantity);
    // }

    // public Drug getDrug() {
    //     return this.toAdd;
    // }


    //edit function to replace placeholder
    @Override
    public CommandResult execute() {
        return new CommandResult(String.format(MESSAGE_SUCCESS, inventory.allDrugs));
    }
}


