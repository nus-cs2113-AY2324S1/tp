package seedu.cafectrl;

import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.ui.Ui;

public class Chef {
    protected Order order;
    protected Pantry pantry;
    protected Ui ui;

    public Chef(Order order, Pantry pantry, Ui ui) {
        this.order = order;
        this.pantry = pantry;
        this.ui = ui;
    }

    public void cookDish() {
        try {
            pantry.decreaseIngredientsStock(order.usedIngredientList);
            order.setComplete();
            ui.showToUser("Is order completed?: " + order.isComplete);
        } catch (Exception e) {
            ui.showToUser("Unable to cook: " + e.getMessage());
        }
    }
}
