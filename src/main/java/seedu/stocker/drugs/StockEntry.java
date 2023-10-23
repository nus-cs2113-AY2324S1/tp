package seedu.stocker.drugs;

public class StockEntry {

    private Drug drug;

    private long quantity;

    public StockEntry(Drug drug, long quantity) {
        this.drug = drug;
        this.quantity = quantity;
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
