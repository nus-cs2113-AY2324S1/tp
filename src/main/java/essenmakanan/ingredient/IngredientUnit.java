package essenmakanan.ingredient;

public enum IngredientUnit {
    GRAM("g"),
    KILOGRAM("kg"),
    MILLILITER("ml"),
    LITER("l"),
    TEASPOON("tsp"),
    TABLESPOON("tbsp"),
    CUP("cup"),
    PIECE("pc");

    private String unit;
    IngredientUnit(String unit) {
        this.unit = unit;
    }

    public String getValue() {
        return this.unit;
    }

}
