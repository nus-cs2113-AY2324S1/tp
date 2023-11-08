package seedu.cafectrl.data;

import seedu.cafectrl.ui.Messages;
import seedu.cafectrl.ui.Ui;

import java.text.DecimalFormat;

public class Chef {
    private final Order order;
    private final Pantry pantry;
    private final Ui ui;
    private Menu menu;

    public Chef(Order order, Pantry pantry, Ui ui, Menu menu) {
        this.order = order;
        this.pantry = pantry;
        this.ui = ui;
        this.menu = menu;
    }

    public void cookDish() {
        try {
            if (!order.getIsComplete()) {
                ui.showChefMessage();
                boolean isComplete = pantry.isDishCooked(order.getIngredientList());
                order.setComplete(isComplete);
            }
        } catch (Exception e) {
            ui.showToUser(e.getMessage());
        }
    }
}
