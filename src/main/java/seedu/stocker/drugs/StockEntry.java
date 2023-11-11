package seedu.stocker.drugs;
import seedu.stocker.ui.Ui;



public class StockEntry {
    private Ui ui;

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
        checkThresholdAndAlert();
    }

    public long getThresholdQuantity() {
        return this.thresholdQuantity;
    }

    public void incrQuantity(long delta) {
        this.quantity += delta;
        checkThresholdAndAlert();
    }

    public void decrQuantity(long delta) {
        assert(this.quantity > delta);
        this.quantity -= delta;
        checkThresholdAndAlert();
    }

    public void checkThresholdAndAlert() {
        if (quantity < thresholdQuantity) {
            System.out.println("|| ALERT! " + this.drug.getName() + " is below the threshold level");
        }
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
            + ", Serial number: " + this.getSerialNumber()
            + ", Quantity: " + this.quantity;
    }
}
