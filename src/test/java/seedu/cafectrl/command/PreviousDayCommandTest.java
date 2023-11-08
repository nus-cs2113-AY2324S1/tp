package seedu.cafectrl.command;

import org.junit.jupiter.api.Test;
import seedu.cafectrl.data.CurrentDate;
import seedu.cafectrl.ui.Ui;

class PreviousDayCommandTest {
    @Test
    public void execute_setTwoDayOneRun_expectDayOne() {
        Ui ui = new Ui();
        CurrentDate currentDate = new CurrentDate(2);
        Command previousDayCommand = new PreviousDayCommand(ui, currentDate);
        previousDayCommand.execute();
        int actualDay = currentDate.getCurrentDay() + 1;
        int expectedDay = 1;
        assert actualDay == expectedDay;
    }

    @Test
    public void execute_setTenDayNineRun_expectDayOne() {
        Ui ui = new Ui();
        CurrentDate currentDate = new CurrentDate(10);
        for (int i = 0; i < 9; i++) {
            Command previousDayCommand = new PreviousDayCommand(ui, currentDate);
            previousDayCommand.execute();
        }
        int actualDay = currentDate.getCurrentDay() + 1;
        int expectedDay = 1;
        assert actualDay == expectedDay;
    }
}
