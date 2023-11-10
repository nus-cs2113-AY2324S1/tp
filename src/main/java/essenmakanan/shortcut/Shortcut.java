package essenmakanan.shortcut;

public class Shortcut {

    private String ingredientName;
    private double quantity;

    public Shortcut(String ingredientName, double quantity) {
        this.ingredientName = ingredientName;
        this.quantity = quantity;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return ingredientName + ": add " + quantity;
    }
}
