package seedu.cafectrl.data;

import seedu.cafectrl.CafeCtrl;
import seedu.cafectrl.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Chef {
    private static final Logger logger = Logger.getLogger(CafeCtrl.class.getName());
    private final Order order;
    private final Pantry pantry;
    private final Ui ui;

    public Chef(Order order, Pantry pantry, Ui ui) {
        this.order = order;
        this.pantry = pantry;
        this.ui = ui;
    }

    public void cookDish() {
        logger.info("Checking if order can be made...");
        try {
            if (!order.getIsComplete()) {
                ui.showChefMessage();

                boolean isComplete = pantry.isDishCooked(order.getIngredientList());
                order.setComplete(isComplete);

                logger.info("Dish cooked: " + isComplete);
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Unsuccessful order: " + e.getMessage(), e);
            ui.showToUser(e.getMessage());
        }
    }
}
