package seedu.cafectrl.storage;

import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.Sales;
import seedu.cafectrl.ui.Ui;

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

    //@@author DextheChik3n
    /**
     * Read and decode menu data from text file and pass it to the menu
     * @return menu with data from the file
     */
    public Menu loadMenu() throws IOException {
        fileManager.openTextFile(FilePath.MENU_FILE_PATH);    ArrayList<String> menuFromTextFile = fileManager.readTextFile(FilePath.MENU_FILE_PATH);
        return Decoder.decodeMenuData(menuFromTextFile);}


    //@@author ziyi105
    /**
     * Read and decode pantryStock data from text file and pass it to the menu
     * @return pantryStock with data from the file
     */
    public Pantry loadPantryStock(Menu menu) {
        ArrayList<String> encodedPantryStock = this.fileManager.readTextFile(FilePath.PANTRY_STOCK_FILE_PATH);
        return Decoder.decodePantryStockData(encodedPantryStock, menu);
        //return new Pantry(ui);
    }

    //@@NaychiMin
    /**
     * Loads order lists from a text file, decodes it, and returns it as a Sales object.
     *
     * @return An OrderList object containing data from the file.
     * @throws IOException if the file is not found in the specified file path.
     */
    public Sales loadOrderList(Menu menu) throws IOException {
        fileManager.openTextFile(FilePath.ORDERS_FILE_PATH);
        ArrayList<String> encodedOrderList = fileManager.readTextFile(FilePath.ORDERS_FILE_PATH);
        return Decoder.decodeSales(encodedOrderList, menu);
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
    //@@author

    //@@author ziyi105
    /**
     * Encode and write the data from menu, orderList and pantry to the respective text files
     * @param menu menu from current session
     * @param sales sale object from current session
     * @param pantry pantry from current session
     * @throws IOException if the file is not found in the specified file path
     */
    public void saveAll(Menu menu, Sales sales, Pantry pantry) throws IOException {
        saveMenu(menu);
        saveOrderList(sales);
        saveMenu(menu);
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

    public void saveMenu(Menu menu) throws IOException {}

}
