package seedu.stocker.commands;

import java.util.Map;

/**
 * Lists all descriptions for corresponding drugs.
 */
public class ListDescriptionsCommand extends Command {

    public static final String COMMAND_WORD = "listDescriptions";

    public static final String MESSAGE_SUCCESS = "List of Drug Descriptions:";

    /**
     * Creates a ListDescriptionsCommand to list all drug descriptions for all corresponding drugs.
     */
    public ListDescriptionsCommand() {
    }

    /**
     * Executes the command to list all drug descriptions.
     *
     * @return CommandResult that displays the list of drug descriptions.
     */
    @Override
    public CommandResult execute() {
        Map<String, String> descriptions = seedu.stocker.drugs.Description.getAllDescriptions();
        StringBuilder result = new StringBuilder(MESSAGE_SUCCESS);

        for (Map.Entry<String, String> entry : descriptions.entrySet()) {
            result.append(System.lineSeparator());
            result.append(entry.getKey()).append(": ").append(entry.getValue());
        }

        return new CommandResult<>(result.toString());
    }
}
