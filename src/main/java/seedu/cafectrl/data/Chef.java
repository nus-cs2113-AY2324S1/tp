package seedu.cafectrl.data;

import seedu.cafectrl.ui.Messages;
import seedu.cafectrl.ui.Ui;

import java.text.DecimalFormat;

public class Chef {
    private final Order order;
    private final Pantry pantry;
    private final Ui ui;
    private final DecimalFormat dollarValue = new DecimalFormat("0.00");

    public Chef(Order order, Pantry pantry, Ui ui) {
        this.order = order;
        this.pantry = pantry;
        this.ui = ui;
    }

    public void cookDish() {
        try {
            if (!order.getIsComplete()) {
                ui.showChefMessage();
                boolean isComplete = pantry.isDishCooked(order.getIngredientList());
                order.setComplete(isComplete);
            }
            String orderStatus = order.getIsComplete()? Messages.COMPLETE_ORDER : Messages.INCOMPLETE_ORDER;
            String totalCost = dollarValue.format(order.getTotalOrderCost());
            ui.showOrderStatus(orderStatus, totalCost);
            pantry.calculateDishAvailability();
        } catch (Exception e) {
            ui.showToUser(Messages.INCOMPLETE_ORDER);
        }
    }


}
