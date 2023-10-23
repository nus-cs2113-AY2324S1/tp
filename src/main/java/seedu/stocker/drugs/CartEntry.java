package seedu.stocker.drugs;

public class CartEntry {

    private String key;
    private long quantity;

    public CartEntry(String key, long quantity) {
        this.key = key;
        this.quantity = quantity;
    }

    public String getKey() {
        return this.key;
    }

    public long getQuantity() {
        return this.quantity;
    }

    /**
     * Returns a string representation of the stock entry.
     *
     * @return A string containing the key and quantity date of the stock entry.
     */
    @Override
    public String toString() {
        return "Key: " + this.key
            + ", Quantity: " + this.quantity;
    }
}
