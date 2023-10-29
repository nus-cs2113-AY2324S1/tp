package seedu.stocker.drugs;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.stream.Collectors;

import seedu.stocker.exceptions.DrugNotFoundException;

public class Inventory {

    public HashMap<String, StockEntry> stock;

    public Inventory() {
        this.stock = new HashMap<String, StockEntry>();
    }

    public void addNewDrug(String key, Drug drug, long quantity) {
        this.stock.put(key, new StockEntry(drug, quantity));
    }

    public void removeFromStock(String key, long quantity) {
        this.stock.get(key).decrQuantity(quantity);
    }

    public List<StockEntry> getStockEntries() {
        return this.stock.values().stream()
        .collect(Collectors.toCollection(ArrayList::new));
    }

    public StockEntry deleteDrug(String key) throws DrugNotFoundException {
        if (this.stock.containsKey(key)) {
            return stock.remove(key);
        } else {
            throw new DrugNotFoundException("");
        }
    }

    public StockEntry getStockEntry(String drugName) {
        for (StockEntry stockEntry : stock.values()) {
            if (stockEntry.getDrug().getName().equalsIgnoreCase(drugName)) {
                return stockEntry;
            }
        }
        return null; // Drug not found in inventory
    }

}
