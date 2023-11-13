package seedu.cafectrl.data.dish;

public class Ingredient {
    private final String name;
    private final String unit;
    private int qty;

    public Ingredient(String name) {
        this.name = name;
        this.unit = null;
        this.qty = 0;
    }

    public Ingredient(String name, int qty, String unit) {
        this.name = name;
        this.unit = unit;
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public int getQty() {
        return qty;
    }

    public String getUnit() {
        return unit;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return name + " - " + qty + unit;
    }

    /**
     * Compare the name of this ingredient to the other ingredient
     *
     * @param obj the other ingredient to be compared with
     * @return true if they have the same name, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Ingredient) {
            String otherName = ((Ingredient) obj).name;
            return this.name.equals(otherName);
        }
        return false;
    }
}
