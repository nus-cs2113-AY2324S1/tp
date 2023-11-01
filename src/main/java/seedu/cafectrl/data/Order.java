package seedu.cafectrl.data;

import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.data.dish.Ingredient;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Order {
    private static final DecimalFormat dollarValue = new DecimalFormat("0.00");
    private final Dish orderedDish;
    private final int dishQty;
    private final ArrayList<Ingredient> ingredientList;
    private boolean isComplete = false;
    private final float totalOrderCost;

    public Order(Dish orderedDish, int dishQty) {
        this.dishQty = dishQty;
        this.orderedDish = orderedDish;
        this.ingredientList = setIngredientList();
        this.totalOrderCost = totalOrderCost();
    }

    public Order(Dish orderedDish, int dishQty, float orderCost) {
        this.dishQty = dishQty;
        this.orderedDish = orderedDish;
        this.ingredientList = setIngredientList();
        this.totalOrderCost = orderCost;
    }

    @Override
    public String toString() {
        return "Order: " + getDishName() + " Quantity: "+ dishQty
                + "\nIngredientList: " + ingredientList
                + "\nTotal Order Cost: $" + dollarValue.format(totalOrderCost);
    }

    /**
     * Calculates the total price of the order
     * Multiplies cost per dish by number of dishes
     *
     * @return Total calculated cost
     */

    public float totalOrderCost() {
        float dishCost = orderedDish.getPrice();
        return dishCost * dishQty;
    }

    /**
     * Gets and prepares the ingredients used in the dish.
     * Calculates the total ingredient used and stores in an Ingredient ArrayList
     *
     * @return Arraylist of Ingredients
     */
    private ArrayList<Ingredient> setIngredientList() {
        ArrayList<Ingredient> dishIngredient = new ArrayList<>();
        for (Ingredient ingredient : orderedDish.getIngredients()) {
            String ingredientName = ingredient.getName();
            int ingredientQty = ingredient.getQty() * dishQty;
            String ingredientUnit = ingredient.getUnit();
            dishIngredient.add(new Ingredient(ingredientName, ingredientQty, ingredientUnit));
        }
        return dishIngredient;
    }

    public ArrayList<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public float getTotalOrderCost() {
        return totalOrderCost;
    }

    public void setComplete() {
        this.isComplete = true;
    }

    public boolean getIsComplete() {
        return isComplete;
    }

    public String getDishName() {
        return orderedDish.getName();
    }

    public int getQuantity() {
        return dishQty;
    }

}
