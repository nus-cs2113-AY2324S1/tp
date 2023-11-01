package seedu.cafectrl.data.dish;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Dish {
    private String name;
    private ArrayList<Ingredient> ingredients;
    private float price;
    private final DecimalFormat dollarValue = new DecimalFormat("0.00");

    public Dish(String name, ArrayList<Ingredient> ingredients, float price) {
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
    }
    public Dish(String name, float price) {
        this.name = name;
        this.ingredients = null;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float newPrice) {
        this.price = newPrice;
    }

    @Override
    public String toString() {
        return this.name + " $" + this.dollarValue.format(price);
    }
}
