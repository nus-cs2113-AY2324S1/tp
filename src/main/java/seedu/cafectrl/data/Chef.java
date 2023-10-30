package seedu.cafectrl.data;

import seedu.cafectrl.ui.Ui;

public class Chef {
    private final Order order;
    private final Pantry pantry;
    private final Ui ui;

    public Chef(Order order, Pantry pantry, Ui ui) {
        this.order = order;
        this.pantry = pantry;
        this.ui = ui;
    }

    public void cookDish() {
        try {
            if (!order.getIsComplete()) {
                ui.showChefMessage();
                pantry.decreaseIngredientsStock(order.getIngredientList());
                order.setComplete();
            }
            ui.showToUser("Is order completed?: " + order.getIsComplete());
        } catch (Exception e) {
            ui.showToUser("Unable to cook: " + e.getMessage());
        }
    }
}
