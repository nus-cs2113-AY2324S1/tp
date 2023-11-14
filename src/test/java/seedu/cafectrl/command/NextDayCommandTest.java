package seedu.cafectrl.command;

import org.junit.jupiter.api.Test;
import seedu.cafectrl.data.CurrentDate;
import seedu.cafectrl.data.Sales;
import seedu.cafectrl.ui.Ui;

class NextDayCommandTest {
    @Test
    public void execute_oneNextDay_expectDayTwo() {
        Ui ui = new Ui();
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate(sales);
        Command nextDayCommand = new NextDayCommand(ui, sales, currentDate);
        nextDayCommand.execute();
        int actualDay = currentDate.getCurrentDay() + 1;
        int expectedDay = 2;
        assert actualDay == expectedDay;
    }

    @Test
    public void execute_nineNextDay_expectDayTen() {
        Ui ui = new Ui();
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate(sales);
        for (int i = 0; i < 9; i ++) {
            Command nextDayCommand = new NextDayCommand(ui, sales, currentDate);
            nextDayCommand.execute();
        }
        int actualDay = currentDate.getCurrentDay() + 1;
        int expectedDay = 10;
        assert actualDay == expectedDay;
    }
}
