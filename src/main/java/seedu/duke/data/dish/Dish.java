package seedu.duke.data.dish;

import java.util.ArrayList;

public class Dish {
    private String name;
    private ArrayList<Ingredient> ingredients;
    private float price;

    public Dish(String name, ArrayList<Ingredient> ingredients, float price) {
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
    }
}
