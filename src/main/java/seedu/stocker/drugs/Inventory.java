package seedu.stocker.drugs;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import seedu.stocker.exceptions.DrugNotFoundException;

public class Inventory {

    public HashMap<String, StockEntry> stock;

    public Inventory() {
        this.stock = new HashMap<String, StockEntry>();
    }

    public void addNewDrug(String serialNumber, Drug drug, long quantity) {
        this.stock.put(serialNumber, new StockEntry(drug, quantity, serialNumber));
    }

    public void removeFromStock(String serialNumber, long quantity) throws DrugNotFoundException {
        if (this.stock.containsKey(serialNumber)) {
            this.stock.get(serialNumber).decrQuantity(quantity);
        } else {
            throw new DrugNotFoundException("");
        }
    }

    public List<Map.Entry<String, StockEntry>> getStockEntries() {
        return new ArrayList<>(this.stock.entrySet());
    }

    public StockEntry deleteDrug(String serialNumber) throws DrugNotFoundException {
        if (this.stock.containsKey(serialNumber)) {
            return stock.remove(serialNumber);
        } else {
            throw new DrugNotFoundException("");
        }
    }

    public StockEntry get(String serialNumber) {
        return this.stock.get(serialNumber);
    }

    public void clearInventory(){
        stock.clear();
    }
}

