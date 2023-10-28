package seedu.cafectrl.data;

import seedu.cafectrl.data.Order;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.ui.Messages;
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
                ui.showToUser(Messages.CHEF_MESSAGE);
                pantry.decreaseIngredientsStock(order.usedIngredientList);
                order.setComplete();
            }
            ui.showToUser("Is order completed?: " + order.isComplete);
        } catch (Exception e) {
            ui.showToUser("Unable to cook: " + e.getMessage());
        }
    }
}
