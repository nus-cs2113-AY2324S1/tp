package seedu.stocker.commands;

/**
 * Adds a drug description to the description list.
 */
public class AddDescriptionCommand extends Command {

    public static final String COMMAND_WORD = "addDescription";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new description for a specific drug "
            + "Parameters: NAME, DESCRIPTION" + System.lineSeparator()
            + "Example: " + COMMAND_WORD + " /n Panadol /desc Pain Relief ";

    public static final String MESSAGE_SUCCESS = "New drug description added for %1$s: %2$s";

    private final String drugName;
    private final String drugDescription;

    public AddDescriptionCommand(String drugName, String drugDescription ) {
        this.drugName = drugName;
        this.drugDescription = drugDescription;
    }

    @Override
    public CommandResult execute() {
        seedu.stocker.drugs.Description.addDescription(drugName, drugDescription);
        return new CommandResult<>(String.format(MESSAGE_SUCCESS, drugName, drugDescription));
    }
}
