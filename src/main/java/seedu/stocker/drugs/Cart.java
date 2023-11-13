package seedu.stocker.drugs;

import seedu.stocker.exceptions.DrugNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private ArrayList<CartEntry> entries;

    public Cart() {
        this.entries = new ArrayList<>();
    }

    public Cart(List<CartEntry> cartEntries) {
        this.entries = new ArrayList<>(cartEntries);
    }

    public void addEntry(String key, long quantity) {
        this.entries.add(new CartEntry(key, quantity));
    }

    public void addEntry(Drug drug, String serialNumber, long quantity, double sellingPrice, Inventory inventory) {
        StockEntry stockEntry = inventory.get(serialNumber);
        if (stockEntry != null) {
            double totalCost = sellingPrice * quantity;
            this.entries.add(new CartEntry(serialNumber, quantity, drug));
        }
    }

    public List<CartEntry> getCurrentCart() {
        return this.entries;
    }

    public boolean isEmpty() {
        return entries.isEmpty();
    }

    public CartEntry getEntryBySerialNumber(String serialNumber) {
        return this.entries.stream()
                .filter(entry -> entry.getSerialNumber().equals(serialNumber))
                .findAny().orElse(null);
    }

    public long getEntryQuantity(String serialNumber) {
        return this.entries
                .stream()
                .filter(entry -> serialNumber.equals(entry.getSerialNumber()))
                .findFirst()
                .map(CartEntry::getQuantity)
                .orElse(0L);
    }

    public void checkOut(SalesList salesList, Inventory inventory) throws DrugNotFoundException {
        salesList.addSale(this);
        for (CartEntry entry : entries) {
            inventory.removeFromStock(entry.getSerialNumber(), entry.getQuantity());
        }
        this.entries = new ArrayList<>();
    }
}
