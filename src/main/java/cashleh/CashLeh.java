package cashleh;

import exceptions.CashLehException;
import cashleh.commands.Command;
import cashleh.commands.Exit;

public class CashLeh {
    private final Ui ui = new Ui();
    private final Input input = new Input();
    private final Parser parser = new Parser();
    private final ExpenseStatement expenseStatement = new ExpenseStatement();
    private final IncomeStatement incomeStatement = new IncomeStatement();

    /**
     * Main entry-point for the application.
     */
    public void run() {

        String logo = "    ______           __    __         __  ___  \n"
                    + "   / ____/___ ______/ /_  / /   ___  / /_/__ \\ \n"
                    + "  / /   / __ `/ ___/ __ \\/ /   / _ \\/ __ \\/ _/ \n"
                    + " / /___/ /_/ (__  ) / / / /___/  __/ / / /_/   \n"
                    + " \\____/\\__,_/____/_/ /_/_____/\\___/_/ /_(_)    \n";
        String userGuideLink = ("Here is the link to the user guide:"
                + "https://docs.google.com/document/d/"
                + "15h45BB5kMkTZ6bkwUHujpYwxVVl80tNEyNUsEVyk5AQ/edit?usp=drive_link");
        String[] greetingLines = {userGuideLink, logo, "Welcome to 'CashLeh?'! " +
                "Your one-stop app for managing your finances!", "What is your name?"};

        ui.printMultipleText(greetingLines);

        String inputString = input.getInputString();
        ui.printText("Hello " + inputString);

        Command command = null;
        while (!(command instanceof Exit)) {
            inputString = input.getInputString();
            try {
                command = parser.parse(inputString);
                command.execute(expenseStatement, incomeStatement);
            } catch (CashLehException e) {
                ui.printMultipleText(new String[] {
                        e.getMessage()
                });
            }
        }
    }

    public static void main(String[] args) {
        new CashLeh().run();
    }
}
