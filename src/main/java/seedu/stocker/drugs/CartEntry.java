package seedu.stocker.drugs;

public class CartEntry {

    private String serialNumber;
    private long quantity;
    private double totalCost;
    private Drug drug;

    public CartEntry(String serialNumber, long quantity) {
        this.serialNumber = serialNumber;
        this.quantity = quantity;
        this.totalCost = calculateTotalCost();
    }

    public CartEntry(String serialNumber, long quantity, Drug drug) {
        this.serialNumber = serialNumber;
        this.quantity = quantity;
        this.drug = drug;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public double getSellingPrice() {
        if (drug != null) {
            return drug.getSellingPrice();
        } else {
            return 0.0; // Default value, please adjust as needed.
        }
    }

    public double calculateTotalCost() {
        return this.quantity * getSellingPrice();
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
