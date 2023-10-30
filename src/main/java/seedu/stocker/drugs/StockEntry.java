package seedu.stocker.drugs;

public class StockEntry {

    private final Drug drug;
    private final String serialNumber;  // Add serial number field

    private long quantity;
    private long thresholdQuantity;

    public StockEntry(Drug drug, long quantity, String serialNumber) {
        this.drug = drug;
        this.quantity = quantity;
        this.serialNumber = serialNumber; // Initialise serial number
        this.thresholdQuantity = 100;
    }

    public String getSerialNumber() {
        return this.serialNumber; // Getter for serial number
    }

    public Drug getDrug() {
        return this.drug;
    }

    public long getQuantity() {
        return this.quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public void setThresholdQuantity(long thresholdQuantity) {
        this.thresholdQuantity = thresholdQuantity;
    }

    public long getThresholdQuantity() {
        return this.thresholdQuantity;
    }

    public void incrQuantity(long delta) {
        this.quantity += delta;
    }

    public void decrQuantity(long delta) {
        assert(this.quantity > delta);
        this.quantity -= delta;
    }

    /**
     * Returns a string representation of the stock entry.
     *
     * @return A string containing the key and quantity date of the stock entry.
     */
    @Override
    public String toString() {
        return "Name: " + this.drug.getName()
            + ", Expiry date: " + this.drug.getExpiryDate()
            + ", Quantity: " + this.quantity;
    }
}
