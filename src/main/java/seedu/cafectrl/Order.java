package seedu.cafectrl;

import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.data.dish.Ingredient;

import java.util.ArrayList;

public class Order {
    protected String dishName;
    protected int dishQty;
    protected Dish orderedDish;
    protected ArrayList<Ingredient> usedIngredientList;
    protected boolean isComplete = false;
    protected float totalOrderCost;

    public Order(String dishName, int dishQty) {
        this.dishName = dishName;
        this.dishQty = dishQty;
    }

    public Order(String dishName, int dishQty, ArrayList<Ingredient> usedIngredientList) {
        this.dishName = dishName;
        this.dishQty = dishQty;
        this.usedIngredientList = usedIngredientList;
    }

    public Order(String dishName, int dishQty, ArrayList<Ingredient> usedIngredientList, float totalOrderCost) {
        this.dishName = dishName;
        this.dishQty = dishQty;
        this.usedIngredientList = usedIngredientList;
        this.totalOrderCost = totalOrderCost;
    }

    public String toString() {
        return "Order: " + dishName + " Quantity: "+ dishQty
                + "\nIngredientList: " + usedIngredientList
                + "\nTotal Order Cost: $" + totalOrderCost;
    }

    public void setComplete() {
        this.isComplete = true;
    }

}
