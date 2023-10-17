package essenmakanan.ingredient;

public class Ingredient {
    private String name;
    private String qty;
    public Ingredient(String name) {
        this.name = name;
    }
    public Ingredient(String name, String qty) {
        this.name = name;
        this.qty = qty;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQty() {
        return this.qty;
    }
    public void setQty(String qty) {
        this.qty = qty;
    }
}
