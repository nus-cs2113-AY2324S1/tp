package seedu.cafectrl.command;

import seedu.cafectrl.data.CurrentDate;
import seedu.cafectrl.ui.Ui;

public class PreviousDayCommand extends Command{
    public static final String COMMAND_WORD = "previous_day";
    public static final String MESSAGE_USAGE = "To go back to previous day:\n" + COMMAND_WORD;

    protected Ui ui;
    protected CurrentDate currentDate;

    public PreviousDayCommand(Ui ui, CurrentDate currentDate) {
        this.ui = ui;
        this.currentDate = currentDate;
    }

    @Override
    public void execute() {
        ui.printLine();
        currentDate.previousDay();
        ui.showPreviousDay();
        ui.showToUser("Today is Day " + (currentDate.getCurrentDay() + 1));
    }
}
