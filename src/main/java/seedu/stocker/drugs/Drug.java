package seedu.stocker.drugs;

public class Drug {

    public String name;
    String expiryDate;

    public Drug(String name, String expiryDate) {
        this.name = name;
        this.expiryDate = expiryDate;
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
     * Returns a string representation of the drug.
     *
     * @return A string containing the name and expiry date of the drug.
     */
    @Override
    public String toString() {
        return "Name: " + name + ", Expiry Date: " + expiryDate;
    }
}
