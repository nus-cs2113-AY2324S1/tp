package cashleh.exception;
/**
 * The <code>CashLehException</code> class represents a custom exception used in the CashLeh application.
 * It extends the standard Java `Exception` class and is used to handle specific application-related exceptions.
 */
public class CashLehException extends Exception {

    /**
     * Constructs a new <code>CashLehException</code> with the specified error message to be displayed.
     * @param message the error message associated with the exception.
     */
    public CashLehException(String message) {
        super(message);
    }
    public static CashLehException invalidAddIncomeFormat() {
        return new CashLehException("Invalid income. Please include description, amount "
                + " and date of income in following format:"
                + "\"addIncome DESCRIPTION /amtAMOUNT /dDATE\"");
    }
    public static CashLehException invalidDeleteIncomeFormat() {
        return new CashLehException("Invalid input format. Please provide a valid"
                + "task index to delete");
    }

}
