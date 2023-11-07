package seedu.stocker.drugs;

import seedu.stocker.exceptions.DrugNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private ArrayList<CartEntry> entries;

    public Cart() {
        this.entries = new ArrayList<>();
    }

    public void addEntry(String key, long quantity) {
        this.entries.add(new CartEntry(key, quantity));
    }

    public List<CartEntry> getCurrentCart() {
        return this.entries;
    }

    public boolean isEmpty() {
        return entries.isEmpty();
    }

    public void checkOut(SalesList salesList, Inventory inventory) throws DrugNotFoundException {
        salesList.addSale(this);
        for (CartEntry entry : entries) {
            inventory.removeFromStock(entry.getSerialNumber(), entry.getQuantity());
        }
        this.entries = new ArrayList<>();
    }
}
