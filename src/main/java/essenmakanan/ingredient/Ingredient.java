package essenmakanan.ingredient;

public class Ingredient {

    private String name;
    private Double quantity;
    private IngredientUnit unit;

    public Ingredient(String name, Double qty, IngredientUnit unit) {
        this.name = name;
        this.quantity = qty;
        this.unit = unit;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public IngredientUnit getUnit() {
        return this.unit;
    }

    public void setUnit(IngredientUnit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        if (String.valueOf(this.quantity).endsWith(".0")) {
            // if quantity is a whole number, remove the decimal point
            String qtyString = String.valueOf(this.quantity);
            qtyString = qtyString.substring(0, qtyString.length() - 2);
            return  this.name + ": " + Integer.parseInt(qtyString) + this.unit.getValue();
        }
        return this.name + ": " + this.quantity + this.unit.getValue();
    }

    public boolean equals(Ingredient i) {
        boolean nameEqual = this.getName().equals(i.getName());
        boolean quantityEqual = this.getQuantity().equals(i.getQuantity());
        boolean unitEqual = this.getUnit().equals(i.getUnit());

        return (nameEqual && quantityEqual && unitEqual);
    }
}
