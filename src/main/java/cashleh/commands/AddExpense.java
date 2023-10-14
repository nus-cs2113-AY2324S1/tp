package cashleh.commands;
import cashleh.Expense;
import cashleh.exception.CashLehException;

public class AddExpense extends Command {
    public static final String COMMAND = "addExpense";
    private final Expense expenseToAdd;

    public AddExpense(String description, double amount) {
        this.expenseToAdd = new Expense(description, amount);
    }

    public AddExpense(Expense expenseToAdd) {
        this.expenseToAdd = expenseToAdd;
    }

    @Override
    public void execute() throws CashLehException {
        expenseStatement.addExpense(expenseToAdd);
        System.out.println("The following expense was added:\n" + getExpense());
    }
}

//    public static void addExpense(ArrayList<Expense> expenseList, String userInput) {
//        int DESCRIPTION_INDEX = 10;
//        int AMOUNT_INDEX = 3;
//        try {
//            String[] parts = userInput.split("/");
//            String description = parts[0].substring(DESCRIPTION_INDEX).trim();
//            String inputAmount = (parts[1].substring(AMOUNT_INDEX).trim());
//            double amount = Double.parseDouble(inputAmount);
//            if (description.isEmpty() || inputAmount.isEmpty()) {
//                throw new CashLehException("Please include description and amount of expense in following format:" +
//                        "addExpense <DESCRIPTION> /amt <AMOUNT>");
//            }
//            expenseList.add(new Expense(description, amount));
//            System.out.println("The following expense has been added:");
//            System.out.println(expenseList.get(expenseList.size() - 1));
//            System.out.println("There are " + expenseList.size() + " expense(s) in the list.");
//        } catch (CashLehException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }

