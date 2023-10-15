package seedu.stocker.commands;

import seedu.stocker.drugs.Drug;
import java.util.List;

import static seedu.stocker.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;


public class FindCommand  extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds drug in inventory \n"
            + "Example: " + COMMAND_WORD + " panadol";

    public final String MESSAGE_SUCCESS = "Listed all drugs with the keyword in the inventory.";
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;

    }

    //edit function to give find functionality
    @Override
    public CommandResult execute() {

        if (keyword == null || keyword.trim().isEmpty()) {
            return new CommandResult(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        List<Drug> drugs = inventory.getAllDrugs();
        for (int i = 0; i < drugs.size(); i++) {
            Drug drug = drugs.get(i);
            String taskDescription = drug.toString().toLowerCase();
            if (taskDescription.contains(keyword.toLowerCase())) {
                System.out.println("|| " + "Name: " + drug.getName() + ", Expiry Date: " + drug.getExpiryDate() + ", Quantity: " + drug.getQuantity());
            }
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS));

    }
}