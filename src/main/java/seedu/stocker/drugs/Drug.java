package seedu.stocker.drugs;

public class Drug {

    public String name;
    String expiryDate;
    Long quantity;

    public Drug(String name, String expiryDate, Long quantity) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
    }

    /**
     * Gets the name of the drug.
     *
     * @return The name of the drug.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the drug.
     *
     * @param name The new name to set for the drug.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the expiry date of the drug.
     *
     * @return The expiry date of the drug.
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the expiry date of the drug.
     *
     * @param expiryDate The new expiry date to set for the drug.
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Gets the quantity of the drug.
     *
     * @return The quantity of the drug.
     */
    public Long getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the drug.
     *
     * @param quantity The new quantity to set for the drug.
     */
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns a string representation of the drug.
     *
     * @return A string containing the name, expiry date, and quantity of the drug.
     */
    @Override
    public String toString() {
        return "Name: " + name + ", Expiry Date: " + expiryDate + ", Quantity: " + quantity;
    }
}
