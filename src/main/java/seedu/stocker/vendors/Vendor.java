package seedu.stocker.vendors;

public class Vendor {

    public String name;

    public Vendor(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the dendor.
     *
     * @return The name of the vendor.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the vendor.
     *
     * @param name The new name to set for the vendor.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a string representation of the vendor.
     *
     * @return A string containing the name of the vendor.
     */
    @Override
    public String toString() {
        return "Name: " + name;
    }
}
