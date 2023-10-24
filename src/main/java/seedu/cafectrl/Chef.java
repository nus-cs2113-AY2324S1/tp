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
            if (!order.isComplete) {
                ui.showToUser("I'm busy crafting your selected dish in the virtual kitchen of your dreams. Bon appétit!");
                pantry.decreaseIngredientsStock(order.usedIngredientList);
                order.setComplete();
            }
            ui.showToUser("Is order completed?: " + order.isComplete);
        } catch (Exception e) {
            ui.showToUser("Unable to cook: " + e.getMessage());
        }
    }
}
