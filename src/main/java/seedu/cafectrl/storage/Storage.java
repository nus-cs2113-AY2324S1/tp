package seedu.cafectrl.storage;

import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.Sales;
import seedu.cafectrl.ui.ErrorMessages;
import seedu.cafectrl.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

//@@author ziyi105
/**
 * Handles loading and saving data for menu, orderList, pantryStock
 */
public class Storage {
    protected FileManager fileManager;
    protected Ui ui;

    public Storage (Ui ui) {
        this.fileManager = new FileManager(ui);
        this.ui = ui;
    }

    //@@author Cazh1
    /**
     * Boolean to detect if the text save file has been tampered with
     *
     * @param encodedStringArrayList The arraylist of string read from text save file
     * @return true is the file's hash is not normal or does not match the newly generated hash, false otherwise
     */
    private boolean isFileCorrupted(ArrayList<String> encodedStringArrayList) {
        //Hash string is stored as last in the ArrayList
        int lastIndex = encodedStringArrayList.size() - 1;
        String hashString = encodedStringArrayList.get(lastIndex);

        //Checks if the saved Hash is abnormal
        if (((!hashString.matches("^[0-9]+$")) && (!hashString.matches("^-[0-9]+$"))) ||
                hashString.matches("^0{2,}$")) {
            //logger.log(Level.INFO, "Corrupted data file");
            return true;
        }

        int fileHash = Integer.parseInt(hashString);
        //Removes the saved Hash String for decoding
        encodedStringArrayList.remove(lastIndex);

        //Prepares String in same format as when encoding, generates Hash from the save file content
        String encodedMenuAsString = String.join(", ", encodedStringArrayList).trim();
        int encodedMenuHash = encodedMenuAsString.hashCode();

        //Checks if the generated Hash matches the saved Hash
        if (encodedMenuHash != fileHash) {
            return true;
        }
        return false;
    }

    //@@author ShaniceTang
    /**
     * Loads menu data from a text file, decodes it, and returns it as a Menu object.
     *
     * @return A Menu object containing data from the file.
     */
    public Menu loadMenu(){
        try {
            ArrayList<String> encodedMenu = fileManager.readTextFile(FilePath.MENU_FILE_PATH);
            if (isFileCorrupted(encodedMenu)) {
                throw new CorruptedDataException();
            }
            return Decoder. decodeMenuData(encodedMenu);
        } catch (FileNotFoundException e) {
            ui.showToUser(ErrorMessages.MENU_FILE_NOT_FOUND_MESSAGE, System.lineSeparator());
            return new Menu();
        } catch (CorruptedDataException e) {
            ui.showToUser("ERROR: Menu Data file is corrupted.",
                    "Clear the Menu data file or restore data to uncorrupted state before trying again.",
                    System.lineSeparator());
            System.out.println("Help");
            return new Menu();
        }
    }

    /**
     * Encodes the provided menu data and writes it to a text file.
     *
     * @param menu The Menu object to be saved to the file.
     * @throws IOException if the file is not found in the specified file path.
     */
    private void saveMenu(Menu menu) throws IOException {
        fileManager.overwriteFile(FilePath.MENU_FILE_PATH, Encoder.encodeMenu(menu));
    }

    //@@author ziyi105
    /**
     * Read and decode pantryStock data from text file and pass it to the menu
     * @return pantryStock with data from the file
     */
    public Pantry loadPantryStock() {
        try {
            ArrayList<String> encodedPantryStock = this.fileManager.readTextFile(FilePath.PANTRY_STOCK_FILE_PATH);
            if (isFileCorrupted(encodedPantryStock)) {
                throw new CorruptedDataException();
            }
            return Decoder.decodePantryStockData(encodedPantryStock);
        } catch (FileNotFoundException e) {
            ui.showToUser(ErrorMessages.PANTRY_FILE_NOT_FOUND_MESSAGE, System.lineSeparator());
            return new Pantry(ui);
        } catch (CorruptedDataException e) {
            ui.showToUser("ERROR: Pantry Data file is corrupted.",
                    "Clear the Pantry data file or restore data to uncorrupted state before trying again.",
                    System.lineSeparator());
            return new Pantry(ui);
        }
    }

    /**
     * Encode and write the data from PantryStock to the text file
     * @param pantry pantry from current session
     * @throws IOException if the file is not found in the specified file path
     */
    private void savePantryStock(Pantry pantry) throws IOException {
        this.fileManager.overwriteFile(FilePath.PANTRY_STOCK_FILE_PATH, Encoder.encodePantryStock(pantry));
    }

    //@@author NaychiMin
    /**
     * Loads order lists from a text file, decodes it, and returns it as a Sales object.
     *
     * @return An OrderList object containing data from the file.
     */
    public Sales loadOrderList(Menu menu) {
        try {
            ArrayList<String> encodedOrderList = fileManager.readTextFile(FilePath.ORDERS_FILE_PATH);
            if (isFileCorrupted(encodedOrderList)) {
                throw new CorruptedDataException();
            }
            return Decoder.decodeSales(encodedOrderList, menu);
        } catch (FileNotFoundException e) {
            ui.showToUser(ErrorMessages.ORDER_LIST_FILE_NOT_FOUND_MESSAGE, System.lineSeparator());
            return new Sales();
        } catch (CorruptedDataException e) {
            ui.showToUser("ERROR: Sales Data file is corrupted.",
                    "Clear the Sales data file or restore data to uncorrupted state before trying again.",
                    System.lineSeparator());
            return new Sales();
        }
    }

    /**
     * Encodes the provided OrderList data from Sales object and writes it to a text file
     *
     * @param sales The Sales object containing the order to be saved to the file.
     * @throws IOException if the file is not found in the specified file path.
     */
    private void saveOrderList(Sales sales) throws IOException {
        this.fileManager.overwriteFile(FilePath.ORDERS_FILE_PATH, Encoder.encodeSales(sales));
    }

    //@@author ziyi105
    /**
     * Encode and write the data from menu, orderList and pantry to the respective text files
     * @param menu menu from current session
     * @param sales sale object from current session
     * @param pantry pantry from current session
     */
    public void saveAll(Menu menu, Sales sales, Pantry pantry) {
        try {
            saveMenu(menu);
            saveOrderList(sales);
            savePantryStock(pantry);
        } catch (IOException e) {
            ui.showToUser(e.getMessage());
        }
    }

}
