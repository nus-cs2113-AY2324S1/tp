package seedu.duke.parser;

import seedu.duke.commands.KaChinnnngException;
import seedu.duke.financialrecords.ExchangeRateManager;
import seedu.duke.financialrecords.Expense;
import seedu.duke.financialrecords.Food;
import seedu.duke.financialrecords.Transport;
import seedu.duke.financialrecords.Utilities;
import seedu.duke.financialrecords.expensetypes.MealType;
import seedu.duke.financialrecords.expensetypes.TransportationType;
import seedu.duke.financialrecords.expensetypes.UtilityType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.HashMap;

public class ExpenseParser {
    public static final String CATEGORY_FIELD = "ca";
    public static final String TYPE_FIELD = "ty";
    public static final String DESCRIPTION_FIELD = "de";
    public static final String DATE_FIELD = "da";
    public static final String AMOUNT_FIELD = "am";
    public static final String INDEX_FIELD = "in";

    public static LocalDate parseDate(String expenseDateString) throws KaChinnnngException {
        LocalDate expenseDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu")
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            expenseDate = LocalDate.parse(expenseDateString, formatter);
        } catch (DateTimeParseException e) {
            throw new KaChinnnngException("Please enter a valid date in the format dd/MM/yyyydon");
        }
        if (expenseDate.isAfter(LocalDate.now())) {
            throw new KaChinnnngException("Please enter a date that is not in the future");
        }
        return expenseDate;
    }

    public static Expense parseExpense(HashMap<String, String> argumentsByField) throws KaChinnnngException {
        if (!argumentsByField.containsKey(CATEGORY_FIELD) ||
                !argumentsByField.containsKey(TYPE_FIELD) ||
                !argumentsByField.containsKey(DESCRIPTION_FIELD) ||
                !argumentsByField.containsKey(DATE_FIELD) ||
                !argumentsByField.containsKey(AMOUNT_FIELD)) {
            throw new KaChinnnngException("Missing fields detected");
        }

        String expenseCategoryString = argumentsByField.get(CATEGORY_FIELD).toLowerCase();
        String expenseTypeString = argumentsByField.get(TYPE_FIELD).toLowerCase();
        String expenseDescriptionString = argumentsByField.get(DESCRIPTION_FIELD);
        String expenseDateString = argumentsByField.get(DATE_FIELD);
        String expenseAmountString = argumentsByField.get(AMOUNT_FIELD);
        LocalDate expenseDate = parseDate(expenseDateString);
        double expenseAmount;

        try {
            if (!isOtherCurrency(expenseAmountString)) {
                expenseAmount = Double.parseDouble(expenseAmountString);
            } else {
                // Convert Currency to SGD
                ExchangeRateManager exchangeRateManager = ExchangeRateManager.getInstance();
                String[] tokens = expenseAmountString.split(" ");
                String currency = tokens[0].trim();
                expenseAmount = Double.parseDouble(tokens[1].trim());
                expenseAmount = exchangeRateManager.convertCurrency(currency, expenseAmount);
                exchangeRateManager.showCurrencyConversionMessage(currency);
            }
        } catch (NumberFormatException e) {
            throw new KaChinnnngException("Please enter a valid amount");
        }

        if (expenseAmount > 999999.99 || expenseAmount <= 0) {
            throw new KaChinnnngException("Expense amount must be between $0.01 and $999999.99");
        }

        if (expenseCategoryString.equals("food")) {
            if (expenseTypeString.equals("breakfast")) {
                return new Food(expenseDescriptionString, expenseDate, expenseAmount, MealType.BREAKFAST);
            } else if (expenseTypeString.equals("lunch")) {
                return new Food(expenseDescriptionString, expenseDate, expenseAmount, MealType.LUNCH);
            } else if (expenseTypeString.equals("dinner")) {
                return new Food(expenseDescriptionString, expenseDate, expenseAmount, MealType.DINNER);
            } else {
                return new Food(expenseDescriptionString, expenseDate, expenseAmount, MealType.OTHER);
            }
        } else if (expenseCategoryString.equals("transport")){
            if (expenseTypeString.equals("train")) {
                return new Transport(expenseDescriptionString, expenseDate, expenseAmount,
                        TransportationType.TRAIN);
            } else if (expenseTypeString.equals("bus")) {
                return new Transport(expenseDescriptionString, expenseDate, expenseAmount,
                        TransportationType.BUS);
            } else if (expenseTypeString.equals("taxi")) {
                return new Transport(expenseDescriptionString, expenseDate, expenseAmount,
                        TransportationType.TAXI);
            } else if (expenseTypeString.equals("fuel")) {
                return new Transport(expenseDescriptionString, expenseDate, expenseAmount,
                        TransportationType.FUEL);
            } else {
                return new Transport(expenseDescriptionString, expenseDate, expenseAmount,
                        TransportationType.OTHER);
            }
        } else if (expenseCategoryString.equals("utilities")) {
            if (expenseTypeString.equals("water")) {
                return new Utilities(expenseDescriptionString, expenseDate, expenseAmount, UtilityType.WATER);
            } else if (expenseTypeString.equals("electricity")) {
                return new Utilities(expenseDescriptionString, expenseDate, expenseAmount, UtilityType.ELECTRICITY);
            } else if (expenseTypeString.equals("gas")) {
                return new Utilities(expenseDescriptionString, expenseDate, expenseAmount, UtilityType.GAS);
            } else{
                return new Utilities(expenseDescriptionString, expenseDate, expenseAmount, UtilityType.OTHER);
            }
        } else {
            throw new KaChinnnngException("Please enter a valid category");
        }
    }

    public static int getIndex(HashMap<String, String> argumentsByFields) throws KaChinnnngException {
        if (!argumentsByFields.containsKey(INDEX_FIELD)) {
            throw new KaChinnnngException("Missing index field detected");
        }
        try {
            return Integer.parseInt(argumentsByFields.get(INDEX_FIELD));
        } catch (NumberFormatException e) {
            throw new KaChinnnngException("Please enter a valid index");
        }
    }

    private static boolean isOtherCurrency(String amount) {
        return (amount.split(" ").length > 1);
    }
}
