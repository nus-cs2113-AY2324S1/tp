package seedu.duke.storage;

import seedu.duke.commands.KaChinnnngException;
import seedu.duke.financialrecords.*;
import seedu.duke.financialrecords.expensetypes.MealType;
import seedu.duke.financialrecords.expensetypes.TransportationType;
import seedu.duke.financialrecords.expensetypes.UtilityType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class GetFromTxt {
    private final File file;
    private static final String FILE_CREATION_ERROR =
            "Something went wrong while creating the file from storage file ";
    private static final String INDEX_OUT_OF_BOUND_FROM_FILE_ERROR =
            "Details missed on this line from storage file ";
    private static final String AMOUNT_NOT_SUPPORT_ERROR =
            "Amount field from this line is not supported from storage file ";
    private static final String DATE_TIME_FORMAT_ERROR =
            "Date time format incorrect on this line from storage file ";
    private static final String NUMBER_FROM_FILE_INCORRECT =
            "The expense type is incorrect on this line from storage file ";

    public GetFromTxt(String path) {
        file = new File(path);
    }

    public void getFromTextFile(ArrayList<Income> incomes, ArrayList<Expense> expenses) throws FileNotFoundException {
        try {
            if (file.createNewFile()) {
                return;
            }
        } catch (IOException e) {
            System.out.println(FILE_CREATION_ERROR);
        }
        Scanner s = new Scanner(file);                      // Create a Scanner using the File as the source
        while (s.hasNext()) {
            String textLine = s.nextLine();
            try {
                int expenseType = 0;
                String commandFromFile = textLine.split(" \\| ")[0];
                String descriptionFromFile = textLine.split(" \\| ")[1];
                double amountFromFile = Double. parseDouble(textLine.split(" \\| ")[2]);
                LocalDate dateFromFile =  LocalDate.parse(textLine.split(" \\| ")[3]);
                if (amountFromFile > 999999.99 || amountFromFile <= 0) {
                    throw new KaChinnnngException("Expense amount must be between $0.01 and $999999.99");
                }
                if (!commandFromFile.equals("I")){
                    expenseType = Integer.parseInt(textLine.split(" \\| ")[4]);
                }
                switch (commandFromFile) {
                case "I":
                    incomes.add(new Income(descriptionFromFile, dateFromFile, amountFromFile));
                    break;
                case "EF":
                    expenses.add(new Food(descriptionFromFile, dateFromFile,
                            amountFromFile, MealType.values()[expenseType]));
                    break;
                case "ET":
                    expenses.add(new Transport(descriptionFromFile, dateFromFile,
                            amountFromFile, TransportationType.values()[expenseType]));
                    break;
                case "EU":
                    expenses.add(new Utilities(descriptionFromFile, dateFromFile,
                            amountFromFile, UtilityType.values()[expenseType]));
                    break;
                default:
                    System.out.println("The format of this line is incorrect " + "\"" + textLine + "\"");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(INDEX_OUT_OF_BOUND_FROM_FILE_ERROR + "\"" + textLine + "\"");
            } catch (KaChinnnngException e) {
                System.out.println(AMOUNT_NOT_SUPPORT_ERROR + "\"" + textLine + "\"");
            } catch (DateTimeParseException e){
                System.out.println(DATE_TIME_FORMAT_ERROR + "\"" + textLine + "\"");
            } catch(NumberFormatException e){
                System.out.println(NUMBER_FROM_FILE_INCORRECT + "\"" + textLine + "\"");
            }
        }
    }
}
