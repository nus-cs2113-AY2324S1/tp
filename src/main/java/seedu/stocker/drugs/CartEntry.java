package seedu.stocker.drugs;

public class CartEntry {

    private String serialNumber;
    private long quantity;

    public CartEntry(String serialNumber, long quantity) {
        this.serialNumber = serialNumber;
        this.quantity = quantity;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public long getQuantity() {
        return this.quantity;
    }

    public void incrQuantity(long delta) {
        this.quantity += delta;
    }

    /**
     * Returns a string representation of the stock entry.
     *
     * @return A string containing the key and quantity date of the stock entry.
     */
    @Override
    public String toString() {
        return "Serial number: " + this.serialNumber
            + ", Quantity: " + this.quantity;
    }
}
