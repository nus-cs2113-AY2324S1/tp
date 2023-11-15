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

    /**
     * To get the value of the enum for more natural user interaction
     *
     * @return the value of the enum
     */
    public String getValue() {
        return this.unit;
    }

}
