package seedu.cafectrl;

import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.dish.Ingredient;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Order {
    private static final DecimalFormat dollarValue = new DecimalFormat("0.00");
    protected String dishName;
    protected int dishQty;
    protected ArrayList<Ingredient> usedIngredientList;
    protected boolean isComplete = false;
    protected float totalOrderCost;

    public Order(String dishName, int dishQty, ArrayList<Ingredient> usedIngredientList, float totalOrderCost) {
        this.dishName = dishName;
        this.dishQty = dishQty;
        this.usedIngredientList = usedIngredientList;
        this.totalOrderCost = totalOrderCost;
    }

    public String toString() {
        return "Order: " + dishName + " Quantity: "+ dishQty
                + "\nIngredientList: " + usedIngredientList
                + "\nTotal Order Cost: $" + dollarValue.format(totalOrderCost);
    }

    public void setComplete() {
        this.isComplete = true;
    }
}
