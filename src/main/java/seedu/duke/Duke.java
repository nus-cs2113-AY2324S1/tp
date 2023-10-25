package seedu.duke;


import seedu.duke.commands.Balance;
import seedu.duke.commands.DeleteExpenseCommand;
import seedu.duke.commands.DeleteIncomeCommand;
import seedu.duke.commands.EditExpenseCommand;
import seedu.duke.commands.EditIncomeCommand;
import seedu.duke.commands.ExpenseLister;
import seedu.duke.commands.ExpenseManager;
import seedu.duke.commands.IncomeLister;
import seedu.duke.commands.IncomeManager;
import seedu.duke.commands.KaChinnnngException;
import seedu.duke.commands.ListCommand;
import seedu.duke.commands.UsageInstructions;
import seedu.duke.financialrecords.Income;
import seedu.duke.financialrecords.Expense;
import seedu.duke.ui.Ui;
import seedu.duke.parser.Parser;
import java.util.ArrayList;

/**
 * This class is the main class of the program.
 * It contains the main method that runs the program.
 */
public class Duke {
    private Ui ui;
    private ArrayList<Income> incomes;
    private ArrayList<Expense> expenses;

    public Duke() {
        ui = new Ui();
        incomes = new ArrayList<>();
        expenses = new ArrayList<>();
    }
    /**
     * This method runs the program.
     */
    public void run() {
        Ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                String command = Parser.parse(fullCommand);
                String[] parts = command.split("-", 2);
                switch (parts[0]) {
                case "exit":
                    isExit = true;
                    break;

                case "add_income":
                    try{
                        IncomeManager incomeCommand = new IncomeManager(fullCommand);
                        incomeCommand.execute();
                        Income newIncome = incomeCommand.getNewIncome();
                        incomes.add(newIncome);
                        ui.showLineDivider();
                        ui.printIncomeAddedMessage(newIncome);
                        ui.showLineDivider();
                    } catch (KaChinnnngException e) {
                        ui.showLineDivider();
                        System.out.println(e.getMessage());
                        ui.showLineDivider();
                    }
                    break;

                case "list_income":
                    ui.showLineDivider();
                    new IncomeLister(incomes, ui).listIncomes();
                    break;

                case "add_expense":
                    try{
                        ExpenseManager expenseCommand = new ExpenseManager(fullCommand);
                        expenseCommand.execute();
                        Expense newExpense = expenseCommand.getNewExpense();
                        expenses.add(newExpense);
                        ui.showLineDivider();
                        ui.printExpenseAddedMessage(newExpense);
                        ui.showLineDivider();
                    } catch (KaChinnnngException e) {
                        ui.showLineDivider();
                        System.out.println(e.getMessage());
                        ui.showLineDivider();
                    }
                    break;

                case "list_expense":
                    new ExpenseLister(expenses, ui).listExpenses();
                    break;

                case "list":
                    ui.showLineDivider();
                    new ListCommand(incomes, expenses, ui).execute();
                    ui.showLineDivider();
                    break;

                case "delete_income":
                    ui.showLineDivider();
                    new DeleteIncomeCommand().execute(incomes, fullCommand, ui);
                    ui.showLineDivider();
                    break;

                case "delete_expense":
                    ui.showLineDivider();
                    new DeleteExpenseCommand().execute(expenses, fullCommand, ui);
                    ui.showLineDivider();
                    break;

                case "help":
                    new UsageInstructions(ui).getHelp();
                    break;

                case "balance":
                    ui.showLineDivider();
                    new Balance(incomes, expenses).getBalanceMessage();
                    ui.showLineDivider();
                    break;

                case "edit_income":
                    ui.showLineDivider();
                    new EditIncomeCommand(incomes, fullCommand).execute();
                    ui.showLineDivider();
                    break;

                case "edit_expense":
                    ui.showLineDivider();
                    new EditExpenseCommand(expenses, fullCommand).execute();
                    ui.showLineDivider();
                    break;

                default:
                    ui.showLineDivider();
                    System.out.println("Invalid command. Please try again.");
                    ui.showLineDivider();
                    break;
                }
            } catch (KaChinnnngException e) {
                System.out.println(e.getMessage());
                ui.showLineDivider();
            }
        }
        ui.printGoodbyeMessage();
    }


    public static void main(String[] args) {
        new Duke().run();
    }

}
