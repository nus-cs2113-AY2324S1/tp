package seedu.duke;

import seedu.duke.commands.*;
import seedu.duke.financialrecords.Expense;
import seedu.duke.ui.Ui;
import seedu.duke.parser.Parser;
import seedu.duke.financialrecords.Income;

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
                    ui.showLineDivider();
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
                case "list":
                    new ListCommand(incomes, expenses).execute();
                    break;
                case "help":
                    new UsageInstructions(ui).getHelp();
                    break;

                default:
                    ui.showLineDivider();
                    System.out.println("Invalid command. Please try again.");
                    ui.showLineDivider();
                    break;
                }
            } catch (KaChinnnngException e) {
                System.out.println(e.getMessage());
            }
        }
        ui.printGoodbyeMessage();
    }


    public static void main(String[] args) {
        new Duke().run();
    }

}
