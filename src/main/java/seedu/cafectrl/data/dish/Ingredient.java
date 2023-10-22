package seedu.cafectrl.data.dish;

public class Ingredient {
    private final String name;
    private String quantity;

    public Ingredient(String name) {
        this.name = name;
        quantity = null;
    }

    public Ingredient(String name, String quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String qty) {
        this.quantity = qty;
    }


    @Override
    public String toString() {
        return name + " - " + quantity;
    }

}
