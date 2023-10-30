package cashleh;

import cashleh.budget.Budget;
import cashleh.budget.BudgetHandler;
import cashleh.exceptions.CashLehWriteToFileException;
import cashleh.parser.Parser;
import cashleh.transaction.ExpenseStatement;
import cashleh.transaction.FinancialStatement;
import cashleh.transaction.IncomeStatement;
import cashleh.exceptions.CashLehException;
import cashleh.commands.Command;
import cashleh.commands.Exit;


public class CashLeh {
    private final static int DEFAULT_BUDGET = 1;
    private final Input input = new Input();
    private final ExpenseStatement expenseStatement = new ExpenseStatement();
    private final IncomeStatement incomeStatement = new IncomeStatement();
    private final BudgetHandler budgetHandler =
            new BudgetHandler(new FinancialStatement(incomeStatement, expenseStatement), new Budget(DEFAULT_BUDGET));
    private final Parser parser = new Parser(expenseStatement, incomeStatement, budgetHandler);

    /**
     * Main entry-point for the application.
     */
    public void run() {
        
        String logo = "    ______           __    __         __  ___\n"
                    + "   / ____/___ ______/ /_  / /   ___  / /_/__ \\\n"
                    + "  / /   / __ `/ ___/ __ \\/ /   / _ \\/ __ \\/ _/\n"
                    + " / /___/ /_/ (__  ) / / / /___/  __/ / / /_/\n"
                    + " \\____/\\__,_/____/_/ /_/_____/\\___/_/ /_(_)\n";
        String userGuideLink = ("Here is the link to the user guide:"
                + "https://docs.google.com/document/d/"
                + "15h45BB5kMkTZ6bkwUHujpYwxVVl80tNEyNUsEVyk5AQ/edit?usp=drive_link");
        String[] greetingLines = {userGuideLink, logo, "Welcome to 'CashLeh?'! " +
                "Your one-stop app for managing your finances!", "What is your name?"};

        Ui.printMultipleText(greetingLines);

        String userName = input.getInputString();
        Ui.printText("Hello " + userName);
        FileStorage fileStorage = new FileStorage(userName);

        Ui.printText("Please begin by setting a budget " +
                "by using the format \"updateBudget DOUBLE\".");

        try {
            fileStorage.readFromFile(incomeStatement, expenseStatement);
        } catch (CashLehException e) {
            Ui.printMultipleText(new String[] {
                e.getMessage()
            });
        }

        Command command = null;
        while (!(command instanceof Exit)) {
            String inputString = input.getInputString();
            try {
                command = parser.parse(inputString);
                command.execute();
            } catch (CashLehException e) {
                Ui.printMultipleText(new String[]{
                        e.getMessage()
                });
            }
        }

        try {
            fileStorage.writeToFile(incomeStatement, expenseStatement);
        } catch (CashLehWriteToFileException e) {
            Ui.printMultipleText(new String[]{
                e.getMessage()
            });
        }
    }

    public static void main(String[] args) {
        new CashLeh().run();
    }
}
