package seedu.cafectrl;

import seedu.cafectrl.data.Pantry;

public class Chef {
    protected Order order;
    protected Pantry pantry;
    public Chef(Order order, Pantry pantry) {
        this.order = order;
        this.pantry = pantry;
    }

    public void cookDish() {
        try {
            pantry.decreaseIngredientsStock(order.usedIngredientList);
            order.setComplete();
            System.out.println("Is order completed?: " + order.isComplete);
        } catch (Exception e) {
            System.out.println("Unable to cook: " + e.getMessage());
        }
    }
}
