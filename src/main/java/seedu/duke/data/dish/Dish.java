package seedu.duke.data.dish;

import java.util.ArrayList;

public class Dish {
    private String name;
    private ArrayList<Ingredient> ingredients;
    private double price;

    public Dish(String name, ArrayList<Ingredient> ingredients, double price) {
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
    }
}
