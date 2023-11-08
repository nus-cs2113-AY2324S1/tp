package seedu.stocker.drugs;

public class CartEntry {

    private String serialNumber;
    private long quantity;
    private double totalCost;

    public CartEntry(String serialNumber, long quantity) {
        this.serialNumber = serialNumber;
        this.quantity = quantity;
    }

    public CartEntry(String serialNumber, long quantity, double totalCost) {
        this.serialNumber = serialNumber;
        this.quantity = quantity;
        this.totalCost = totalCost;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public long getQuantity() {
        return this.quantity;
    }

    public double getTotalCost() {
        return this.totalCost;
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
