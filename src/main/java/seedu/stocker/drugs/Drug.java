package seedu.stocker.drugs;

public class Drug {

    public String name;
    String expiryDate;
    private double vendorPrice;
    private double sellingPrice;

    public Drug(String name, String expiryDate, double sellingPrice) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.sellingPrice = sellingPrice;
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

    public double getVendorPrice() {
        return vendorPrice;
    }

    public void setVendorPrice(double vendorPrice) {
        this.vendorPrice = vendorPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    /**
     * Returns a string representation of the drug.
     *
     * @return A string containing the name and expiry date of the drug.
     */
    @Override
    public String toString() {
        return "Name: " + name + ", Expiry Date: " + expiryDate
                + ", Vendor Price: $" + vendorPrice + ", Selling Price: $" + sellingPrice;
    }
}
