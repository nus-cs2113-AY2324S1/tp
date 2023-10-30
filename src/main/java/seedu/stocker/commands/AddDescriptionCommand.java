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

    /**
     * Creates an AddDescriptionCommand to add a drug description to the description list.
     *
     * @param drugName       The name of the drug for which the description is added.
     * @param drugDescription The description to be added for the drug.
     */
    public AddDescriptionCommand(String drugName, String drugDescription ) {
        this.drugName = drugName;
        this.drugDescription = drugDescription;
    }

    /**
     * Executes the command to add the drug description.
     *
     * @return CommandResult indicating the success of adding the description.
     */
    @Override
    public CommandResult execute() {
        seedu.stocker.drugs.Description.addDescription(drugName, drugDescription);
        return new CommandResult<>(String.format(MESSAGE_SUCCESS, drugName, drugDescription));
    }
}
