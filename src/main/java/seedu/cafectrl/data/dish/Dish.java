package seedu.cafectrl.data.dish;

import java.util.ArrayList;
import java.text.DecimalFormat;

public class Dish {
    private final DecimalFormat dollarValue = new DecimalFormat("0.00");

    private final String name;
    private final ArrayList<Ingredient> ingredients;
    private float price;

    public Dish(String name, ArrayList<Ingredient> ingredients, float price) {
        this.name = name;
        this.ingredients = ingredients;
        this.price = Math.abs(price);
    }
    public Dish(String name, float price) {
        this.name = name;
        this.ingredients = new ArrayList<>();
        this.price = Math.abs(price);
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

    public String getPriceString() {
        return this.dollarValue.format(this.price);
    }

    public void setPrice(float newPrice) {
        this.price = Math.abs(newPrice);
    }

    //@@author ziyi105
    @Override
    public String toString() {
        return this.name + " $" + this.dollarValue.format(this.price);
    }

    /**
     * Compare the original price and new price
     * @param otherPrice price value to be compared with
     * @return 0 if both price values are the same, -1 if the this.price is lower, +1 otherwise
     */
    public int comparePrice(float otherPrice) {
        String formattedPrice = this.dollarValue.format(price);
        String formattedNewPrice = this.dollarValue.format(otherPrice);

        return formattedPrice.compareTo(formattedNewPrice);
    }
}
