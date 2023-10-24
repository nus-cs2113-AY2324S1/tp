package seedu.duke.storage;

import seedu.duke.financialrecords.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class SaveToTxt {
    private final String path;
    private static final String IO_ERROR_MESSAGE = "An error occurred while saving tasks to the file.";

    public SaveToTxt(String path) {
        this.path = path;
    }

    public void saveIncomeAndExpense(ArrayList<Income> incomes, ArrayList<Expense> expenses){
        try {
            saveIncomeToTextFile(incomes);
            saveExpenseToTextFile(expenses);
        } catch (IOException e) {
            System.out.println(IO_ERROR_MESSAGE);
        }
    }

    public void saveIncomeToTextFile(ArrayList<Income> incomes) throws IOException {
        try (FileWriter fw = new FileWriter(path)) {
            for (Income income : incomes) {
                String incomeDescription = income.getDescription();
                String incomeDate = String.valueOf(income.getDate());
                String incomeAmount = String.valueOf(income.getAmount());
                String textToAdd = "I" + " | " + incomeDescription + " | " + incomeAmount + " | " + incomeDate + "\n";
                fw.write(textToAdd);
            }
        } catch (IOException e) {
            System.out.println(IO_ERROR_MESSAGE);
        }
    }

    public void saveExpenseToTextFile(ArrayList<Expense> expenses) throws IOException{
        try (FileWriter fw = new FileWriter(path, true)) {
            for (Expense expense : expenses) {
                String textToAdd = "";
                String expenseDescription = expense.getDescription();
                String expenseDate = String.valueOf(expense.getDate());;
                String expenseAmount = String.valueOf(expense.getAmount());

                if (expense.getClass() == Food.class) {
                    textToAdd = "EF" + " | " + expenseDescription + " | " + expenseAmount + " | "
                            + expenseDate + " | " + ((Food) expense).getMealType().ordinal() + "\n";
                } else if (expense.getClass() == Transport.class) {
                    textToAdd = "ET" + " | " + expenseDescription + " | " + expenseAmount + " | "
                            + expenseDate + " | " + ((Transport) expense).getTransportationType().ordinal() + "\n";
                } else if (expense.getClass() == Utilities.class) {
                    textToAdd = "EU" + " | " + expenseDescription + " | " + expenseAmount + " | "
                            + expenseDate + " | " + ((Utilities) expense).getUtilityType().ordinal() + "\n";
                }
                fw.write(textToAdd);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(IO_ERROR_MESSAGE);
        }
    }
}
