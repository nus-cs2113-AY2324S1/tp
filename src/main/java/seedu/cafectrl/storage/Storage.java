package seedu.cafectrl.storage;

import seedu.cafectrl.OrderList;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

//@@author DextheChik3n
/**
 * Handles reading from and storing data to the text file.
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
     * Initializes reading data from text file process
     * @return ArrayList of respective type based on the storage functionality
     * @throws FileNotFoundException if the file is not found in the specified file path in main
     */
    public Menu loadMenu() throws FileNotFoundException {
        // ArrayList<String> encodedMenu = this.fileManager.readTextFile(FilePath.MENU_FILE_PATH);
        // return Decoder.decodeMenuData(encodedMenu);
        return new Menu();
    }

    public Pantry loadPantryStock() throws FileNotFoundException {
        // ArrayList<String> encodedPantryStock = this.fileManager.readTextFile(FilePath.PANTRY_STOCK_FILE_PATH);
        // return Decoder.decodePantryStockData(encodedPantryStock);
        return new Pantry(ui);
    }

    public OrderList loadOrderList() throws FileNotFoundException {
        // ArrayList<String> encodedOrderList = this.fileManager.readTextFile(FilePath.ORDERS_FILE_PATH);
        // return Decoder.decodeOrderListData(encodedOrderList);
        return new OrderList();
    }

    public void saveAll(Menu menu, OrderList orderList, Pantry pantry) throws IOException {
        saveMenu(menu);
        saveOrderList(orderList);
        savePantryStock(pantry);
    }

    private void savePantryStock(Pantry pantry) throws IOException {
        this.fileManager.overwriteFile(FilePath.PANTRY_STOCK_FILE_PATH, Encoder.encodePantryStock(pantry));
    }

    private void saveOrderList(OrderList orderList) throws IOException {
        this.fileManager.overwriteFile(FilePath.ORDERS_FILE_PATH, Encoder.encodeOrderList(orderList));
    }

    private void saveMenu(Menu menu) throws IOException {
        this.fileManager.overwriteFile(FilePath.MENU_FILE_PATH, Encoder.encodeMenu(menu));
    }

}
