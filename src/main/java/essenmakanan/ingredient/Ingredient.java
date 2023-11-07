package essenmakanan.ingredient;

public class Ingredient {

    private String name;
    private String quantity;
    private IngredientUnit unit;

    public Ingredient(String name) {
        this.name = name;
        this.quantity = "1";
        this.unit = IngredientUnit.PIECE;
    }

    public Ingredient(String name, String qty, IngredientUnit unit) {
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

    public String getQuantity() {
        return this.quantity;
    }

    public void setQuantity(String quantity) {
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
        return  this.name + ": " + this.quantity + this.unit.getValue();
    }

    public boolean equals(Ingredient i) {
        boolean nameEqual = this.getName().equals(i.getName());
        boolean quantityEqual = this.getQuantity().equals(i.getQuantity());
        boolean unitEqual = this.getUnit().equals(i.getUnit());

        return (nameEqual && quantityEqual && unitEqual);
    }
}
