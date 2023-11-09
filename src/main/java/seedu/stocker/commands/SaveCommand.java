package seedu.stocker.commands;

import seedu.stocker.drugs.StockEntry;
import seedu.stocker.storage.Storage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Backups existing drug list inventory into txt file to be uploaded later.
 */
public class SaveCommand extends Command{
    public static final String COMMAND_WORD = "saveDrugs";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Saves existing druglist that is loaded "
            +"into inventory when system is booted up."
            + System.lineSeparator()
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Drugs successfully saved.";

    public CommandResult execute() throws IOException {

        File holder = new File("./drugs.txt");
        if (!holder.exists()) {
            holder.createNewFile();
        }

        List<Map.Entry<String,StockEntry>> entries= inventory.getStockEntries();
        Storage storageManager = new Storage(inventory);
        storageManager.writeToFile("drugs.txt", "");

        for (Map.Entry<String, StockEntry> entry : entries) {
            String name = entry.getValue().getDrug().getName();
            String date = entry.getValue().getDrug().getExpiryDate();
            String serialNumber = entry.getKey();
            String quantity = String.valueOf(entry.getValue().getQuantity());
            String sellingPrice = String.valueOf(entry.getValue().getDrug().getSellingPrice());
            String toBeAppended = "Name: " + name + ", " + "Expiry Date: " + date + ", "
                    + "Serial Number: " + serialNumber + ", " + "Quantity: " + quantity
                    + ", " + "Selling Price: " + sellingPrice;
            storageManager.appendToFile("drugs.txt", toBeAppended);
        }
        return new CommandResult<>(MESSAGE_SUCCESS);
    }
}
