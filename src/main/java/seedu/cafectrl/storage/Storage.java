package seedu.cafectrl.storage;

import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.Sales;
import seedu.cafectrl.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

//@@author ziyi105
/**
 * Handles loading and saving data for menu, orderList, pantryStock
 */
public class Storage {
    FileManager fileManager;
    Ui ui;

    public Storage (Ui ui) {
        this.fileManager = new FileManager(ui);
        this.ui  = ui;
    }

    /**
     * Loads menu data from a text file, decodes it, and returns it as a Menu object.
     *
     * @return A Menu object containing data from the file.
     * @throws IOException if the file is not found in the specified file path.
     */
    public Menu loadMenu() throws IOException {
        fileManager.openTextFile(FilePath.MENU_FILE_PATH);
        ArrayList<String> menuFromTextFile = fileManager.readTextFile(FilePath.MENU_FILE_PATH);
        return Decoder.decodeMenuData(menuFromTextFile);
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
    public Pantry loadPantryStock() throws FileNotFoundException {
        ArrayList<String> encodedPantryStock = this.fileManager.readTextFile(FilePath.PANTRY_STOCK_FILE_PATH);
        return Decoder.decodePantryStockData(encodedPantryStock);
        //return new Pantry(ui);
    }

    /**
     * Read and decode order list data from text file and pass it to the menu
     * @return orderList with data from the file
     */
    public Sales loadSales() {
        // ArrayList<String> encodedOrderList = this.fileManager.readTextFile(FilePath.ORDERS_FILE_PATH);
        // return Decoder.decodeOrderListData(encodedOrderList);
        return new Sales();
    }

    //@@author ziyi105
    /**
     * Encode and write the data from menu, orderList and pantry to the respective text files
     * @param menu menu from current session
     * @param sales sales from current session
     * @param pantry pantry from current session
     * @throws IOException if the file is not found in the specified file path
     */
    public void saveAll(Menu menu, Sales sales, Pantry pantry) throws IOException {
        // to be uncommented when the following features are implemented
        //saveMenu(menu);
        //saveSales(sales);
        savePantryStock(pantry);
    }

    //@@author ziyi105
    /**
     * Encode and write the data from PantryStock to the text file
     * @param pantry pantry from current session
     * @throws IOException if the file is not found in the specified file path
     */
    private void savePantryStock(Pantry pantry) throws IOException {
        this.fileManager.overwriteFile(FilePath.PANTRY_STOCK_FILE_PATH, Encoder.encodePantryStock(pantry));
    }

    /**
     * Encode and write the data from orderList to the text file
     * @param sales
     * @throws IOException if the file is not found in the specified file path
     */
    private void saveSales(Sales sales) throws IOException {
        this.fileManager.overwriteFile(FilePath.ORDERS_FILE_PATH, Encoder.encodeSales(sales));
    }
}
