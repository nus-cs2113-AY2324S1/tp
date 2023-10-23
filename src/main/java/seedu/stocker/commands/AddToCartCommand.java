package seedu.stocker.commands;

import seedu.stocker.drugs.StockEntry;

/**
 * Adds a certain quantity of one drug into the current cart
 */
public class AddToCartCommand extends Command {

    public static final String COMMAND_WORD = "addtocart";

    public static final String MESSAGE_USAGE = COMMAND_WORD 
            + ": Adds a new drug to the current cart. "
            + "Parameters: NAME, QUANTITY,  " + System.lineSeparator()
            + "Example: " + COMMAND_WORD
            + " /n Doliprane /q 2";

    public static final String MESSAGE_SUCCESS = "New drug added in the current cart: %1$s";

    private String drugName;
    private long quantity;

    public AddToCartCommand(String name, long quantity) {
        this.drugName = name;
        this.quantity = quantity;
    }

    @Override
    public CommandResult execute() {
        StockEntry matchingEntry = inventory.getStockEntries().stream()
            .filter(entry -> entry
                .getDrug().getName()
                .equalsIgnoreCase(this.drugName) && 
                entry.getQuantity() > this.quantity)
            .findAny()
            .orElse(null);
        if (matchingEntry != null) {
            currentCart.addEntry(this.drugName, this.quantity);
            return new CommandResult(String.format(MESSAGE_SUCCESS, matchingEntry.getDrug().getName()));
        } else {
            return new CommandResult("This drug is not in stock. ");
        }
    }

}
