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
                boolean isComplete = pantry.isDishCooked(order.getIngredientList());
                order.setComplete(isComplete);
            }
        } catch (Exception e) {
            ui.showToUser(e.getMessage());
        }
    }
}
