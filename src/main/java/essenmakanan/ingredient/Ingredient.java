package essenmakanan.ingredient;

public class Ingredient {
    private String name;
    private String quantity;
    public Ingredient(String name) {
        this.name = name;
    }
    public Ingredient(String name, String qty) {
        this.name = name;
        this.quantity = qty;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return this.quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
