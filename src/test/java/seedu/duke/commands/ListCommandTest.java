package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.financialrecords.Expense;
import seedu.duke.financialrecords.Food;
import seedu.duke.financialrecords.Income;
import seedu.duke.financialrecords.expensetypes.MealType;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

public class ListCommandTest {
    private ArrayList<Income> incomes;
    private ArrayList<Expense> expenses;
    private Ui ui;
    private ListCommand listCommand;

    @BeforeEach
    public void setup() throws KaChinnnngException {
        incomes = new ArrayList<>();
        expenses = new ArrayList<>();
        ui = new Ui();
        listCommand = new ListCommand(incomes,expenses);
    }

    @Test
    public void testWithBothListEmpty(){
        listCommand.execute();
    }

    @Test
    public void testWithIncomeListEmpty() throws KaChinnnngException {
        expenses.add(new Food("chicken sandwich",
                LocalDate.of(2023, 10, 1),10.0, MealType.LUNCH));
        listCommand.execute();
        expenses.clear();
    }

    @Test
    public void testWithExpenseListEmpty() throws KaChinnnngException {
        incomes.add(new Income("Salary", LocalDate.of(2023, 10, 10), 5000.0));
        listCommand.execute();
        incomes.clear();
    }

    @Test
    public void testBothListNotEmpty() throws KaChinnnngException {
        expenses.add(new Food("chicken sandwich",
                LocalDate.of(2023, 10, 1),10.0, MealType.LUNCH));
        incomes.add(new Income("Salary", LocalDate.of(2023, 10, 10), 5000.0));
        listCommand.execute();
    }
}
