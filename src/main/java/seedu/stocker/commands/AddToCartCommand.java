package seedu.stocker.commands;

import seedu.stocker.drugs.CartEntry;
import seedu.stocker.drugs.StockEntry;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Adds a certain quantity of one drug into the current cart
 */
public class AddToCartCommand extends Command {

    public static final String COMMAND_WORD = "addToCart";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a new drug to the current cart. "
            + "Parameters: SERIAL NUMBER, QUANTITY," + System.lineSeparator()
            + "Example: " + COMMAND_WORD
            + " /s ABC123 /q 2";

    public static final String MESSAGE_SUCCESS = "New drug added in the current cart: %1$s";

    private final String serialNumber;
    private final long quantity;

    public AddToCartCommand(String serialNumber, long quantity) {
        this.serialNumber = serialNumber;
        this.quantity = quantity;
    }

    @Override
    public <T> CommandResult<T> execute() {
        StockEntry entry = inventory.get(this.serialNumber);
        if (entry == null) {
            return new CommandResult<>("This drug is not in stock");
        } else {
            String inputDate = entry.getDrug().getExpiryDate();
            LocalDate expiryDate = parseDate(inputDate);
            LocalDate currentDate = LocalDate.now(); // Get the current date

            if (currentDate.isAfter(expiryDate)) {
                return new CommandResult<>("This drug is expired. Unable to add to cart");
            } else if (entry.getQuantity() < this.quantity + currentCart.getEntryQuantity(this.serialNumber)) {
                return new CommandResult<>("There is not enough stock on this drug.");
            } else {
                CartEntry cartEntry = currentCart.getEntryBySerialNumber(this.serialNumber);
                if (cartEntry == null) {
                    currentCart.addEntry(this.serialNumber, this.quantity);
                } else {
                    cartEntry.incrQuantity(this.quantity);
                }
                return new CommandResult<>(String.format(MESSAGE_SUCCESS, entry.getDrug().getName()));
            }
        }
    }

    /**
     * Helper method to parse a date string in "dd/mm/yyyy" format to a LocalDate.
     *
     * @param dateStr The input date string.
     * @return The parsed LocalDate.
     */
    private LocalDate parseDate(String dateStr) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateStr, inputFormatter);
    }


}
