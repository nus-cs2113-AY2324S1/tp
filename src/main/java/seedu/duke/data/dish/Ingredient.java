package seedu.duke.data.dish;

public class Ingredient {
    private final String name;
    private final String quantity;

    public Ingredient(String name) {
        this.name = name;
        quantity = null;
    }

    public Ingredient(String name, String quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name + " - " + quantity;
    }
}
