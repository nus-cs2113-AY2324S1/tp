package seedu.cafectrl.data.dish;

public class Ingredient {
    private final String name;
    private int qty;
    private String unit;

    public Ingredient(String name) {
        this.name = name;
        this.qty = 0;
        this.unit = null;
    }

    public Ingredient(String name, int qty, String unit) {
        this.name = name;
        this.qty = qty;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }


    @Override
    public String toString() {
        return name + " - " + qty;
    }

}
