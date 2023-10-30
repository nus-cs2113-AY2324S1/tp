package seedu.stocker.commands;

/**
 * Gets the description of a specific drug.
 */
public class GetDescriptionCommand extends Command {

    public static final String COMMAND_WORD = "getDescription";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Gets the description of a specific drug."
            + System.lineSeparator()
            + "Parameters: NAME" + System.lineSeparator()
            + "Example: " + COMMAND_WORD + " /n Panadol";

    public static final String MESSAGE_DESCRIPTION_NOT_FOUND = "Description not found for %1$s";

    private final String drugName;

    /**
     * Creates a GetDescriptionCommand to retrieve the description of a specific drug.
     *
     * @param drugName The name of the drug for which the description should be retrieved.
     */
    public GetDescriptionCommand(String drugName) {
        this.drugName = drugName;
    }

    /**
     * Executes the command to retrieve the description of the specified drug.
     *
     * @return CommandResult containing the drug description if found, or an error message if not found.
     */
    @Override
    public CommandResult execute() {
        String description = seedu.stocker.drugs.Description.getDescription(drugName);

        if (description != null) {
            return new CommandResult<>(description);
        } else {
            return new CommandResult<>(String.format(MESSAGE_DESCRIPTION_NOT_FOUND, drugName));
        }
    }
}
