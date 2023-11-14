package seedu.stocker.commands;

import seedu.stocker.drugs.Cart;
import seedu.stocker.drugs.CartEntry;
import seedu.stocker.drugs.StockEntry;
import seedu.stocker.storage.Storage;
import seedu.stocker.drugs.Drug;


import java.io.File;
import java.io.IOException;

/**
 * Backs up the current sales list into a text file.
 */
public class SaveSalesCommand extends Command {
    public static final String COMMAND_WORD = "saveSales";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Saves the current sold items to a file."
            + System.lineSeparator()
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Sales list successfully saved.";
    public static final String MESSAGE_FAILURE = "The sales list is empty. Nothing to save.";

    @Override
    public CommandResult execute() throws IOException {
        try {
            if (salesList.getAllSales().isEmpty()) {
                return new CommandResult(MESSAGE_FAILURE);
            }

            File salesFile = new File("./soldItems.txt");
            if (!salesFile.exists()) {
                salesFile.createNewFile();
            }

            Storage storageManager = new Storage(inventory);

            for (Cart cart : salesList.getAllSales()) {
                for (CartEntry entry : cart.getCurrentCart()) {
                    String serialNumber = entry.getSerialNumber();
                    long quantity = entry.getQuantity();
                    double sellingPrice = entry.getSellingPrice();

                    StockEntry stockEntry = inventory.get(serialNumber);
                    Drug drug = stockEntry.getDrug();

                    String toBeAppended = "Name: " + drug + ", "
                            + "Serial Number: " + serialNumber + ", "
                            + "Quantity: " + quantity + ", "
                            + "Selling Price: " + sellingPrice;
                    storageManager.appendToFile("soldItems.txt", toBeAppended);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new CommandResult<>("Error: Failed to save sales data.");
        }
        return new CommandResult<>(MESSAGE_SUCCESS);
    }
}
