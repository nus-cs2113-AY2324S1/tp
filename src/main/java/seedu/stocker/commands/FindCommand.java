package seedu.stocker.commands;

import seedu.stocker.drugs.Drug;
import java.util.List;

import static seedu.stocker.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds drug in inventory " + System.lineSeparator() +
             "Example: " + COMMAND_WORD + " panadol";


    public static final String MESSAGE_SUCCESS = "Listed all drugs with the keyword in the inventory.";

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    // Edit the function to give find functionality
    @Override
    public CommandResult execute() {
        if (keyword == null || keyword.trim().isEmpty()) {
            return new CommandResult(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        List<Drug> drugs = inventory.getAllDrugs();
        int j = 1;
        for (int i = 0; i < drugs.size(); i++) {
            Drug drug = drugs.get(i);
            String drugDescription = drug.toString().toLowerCase();
            if (drugDescription.contains(keyword.toLowerCase())) {
                System.out.println("|| " + (j) + ". " + "Name: " + drug.getName() + ", Expiry Date: "
                        + drug.getExpiryDate() + ", Quantity: " + drug.getQuantity());
                j++;
            }
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
