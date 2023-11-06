package seedu.cafectrl.data.dish;

import java.util.ArrayList;
import java.text.DecimalFormat;

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
        return this.name + " $" + this.dollarValue.format(this.price);
    }

    //@@author ziyi105

    /**
     * Compare the original price and new price
     * @param newPrice
     * @return
     */
    public int comparePrice(float newPrice) {
        String formattedPrice = this.dollarValue.format(price);
        String formattedNewPrice = this.dollarValue.format(newPrice);
        return formattedPrice.compareTo(formattedNewPrice);
    }
}
