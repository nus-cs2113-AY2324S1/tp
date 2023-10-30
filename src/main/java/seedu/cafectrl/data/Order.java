package seedu.cafectrl.data;

import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.data.dish.Ingredient;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Order {
    private static final DecimalFormat dollarValue = new DecimalFormat("0.00");
    protected Dish orderedDish;
    protected int dishQty;
    protected ArrayList<Ingredient> usedIngredientList;
    protected boolean isComplete = false;
    protected float totalOrderCost;

    public Order(Dish orderedDish, int dishQty) {
        this.dishQty = dishQty;
        this.orderedDish = orderedDish;
        this.usedIngredientList = getIngredientList();
        this.totalOrderCost = getDishPrice();
    }

    @Override
    public String toString() {
        return "Order: " + orderedDish.getName() + " Quantity: "+ dishQty
                + "\nOrder Cost: $" + dollarValue.format(totalOrderCost);
    }

    /**
     * Calculates the total price of the order
     * Multiplies cost per dish by number of dishes
     *
     * @return Total calculated cost
     */
    public float getDishPrice() {
        float dishCost = orderedDish.getPrice();
        float totalOrderCost = dishCost * dishQty;
        return totalOrderCost;
    }

    /**
     * Gets and prepares the ingredients used in the dish.
     * Calculates the total ingredient used and stores in an Ingredient ArrayList
     *
     * @return Arraylist of Ingredients
     */
    private ArrayList<Ingredient> getIngredientList() {
        ArrayList<Ingredient> dishIngredient = new ArrayList<>();
        for (Ingredient ingredient : orderedDish.getIngredients()) {
            String ingredientName = ingredient.getName();
            int ingredientQty = ingredient.getQty() * dishQty;
            String ingredientUnit = ingredient.getUnit();
            dishIngredient.add(new Ingredient(ingredientName, ingredientQty, ingredientUnit));
        }
        return dishIngredient;
    }

    public void setComplete() {
        this.isComplete = true;
    }

    public boolean isComplete() {
        return isComplete;
    }

}
