package essenmakanan.shortcut;

public class Shortcut {

    private String ingredientName;
    private Double quantity;

    public Shortcut(String ingredientName, Double quantity) {
        this.ingredientName = ingredientName;
        this.quantity = quantity;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public Double getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return ingredientName + ": add " + quantity;
    }
}
