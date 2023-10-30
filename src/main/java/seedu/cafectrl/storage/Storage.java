package seedu.cafectrl.storage;

import seedu.cafectrl.OrderList;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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


    public Menu loadMenu() throws IOException {
        fileManager.openTextFile(FilePath.MENU_FILE_PATH);    ArrayList<String> menuFromTextFile = fileManager.readTextFile(FilePath.MENU_FILE_PATH);
        return Decoder.decodeMenuData(menuFromTextFile);}
    /**
     * Encode and write the data from menu to the text file * @param menu menu from current session
     * @throws IOException if the file is not found in the specified file path */
    public void saveMenu(Menu menu) throws IOException {    fileManager.overwriteFile(FilePath.MENU_FILE_PATH, Encoder.encodeMenu(menu));
    }

    /**
     * Read and decode pantryStock data from text file and pass it to the menu
     * @return pantryStock with data from the file
     * @throws FileNotFoundException if the file is not found in the specified file path
     */
    public Pantry loadPantryStock() throws FileNotFoundException {
        // ArrayList<String> encodedPantryStock = this.fileManager.readTextFile(FilePath.PANTRY_STOCK_FILE_PATH);
        // return Decoder.decodePantryStockData(encodedPantryStock);
        return new Pantry(ui);
    }

    /**
     * Loads order data from a text file, decodes it, and returns it as an OrderList object.
     *
     * @return An OrderList object containing data from the file.
     * @throws FileNotFoundException if the file is not found in the specified file path.
     */
    public OrderList loadOrderList(Menu menu) throws IOException {
        fileManager.openTextFile(FilePath.ORDERS_FILE_PATH);
        ArrayList<String> encodedOrderList = fileManager.readTextFile(FilePath.ORDERS_FILE_PATH);
        return Decoder.decodeOrderList(encodedOrderList, menu);
    }


    /**
     * Encode and write the data from menu, orderList and pantry to the respective text files
     * @param menu menu from current session
     * @param orderList orderList from current session
     * @param pantry pantry from current session
     * @throws IOException if the file is not found in the specified file path
     */
    public void saveAll(Menu menu, OrderList orderList, Pantry pantry) throws IOException {
        saveMenu(menu);
        //saveOrderList(orderList);
        savePantryStock(pantry);
    }

    /**
     * Encode and write the data from PantryStock to the text file
     * @param pantry pantry from current session
     * @throws IOException if the file is not found in the specified file path
     */
    private void savePantryStock(Pantry pantry) throws IOException {
        this.fileManager.overwriteFile(FilePath.PANTRY_STOCK_FILE_PATH, Encoder.encodePantryStock(pantry));
    }



}
