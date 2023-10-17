package seedu.duke.financialrecords;

import seedu.duke.commands.KaChinnnngException;
import seedu.duke.financialrecords.expensetypes.TransportationType;
import java.time.LocalDate;

// Transport class extending Expense
public class Transport extends Expense {
    private TransportationType transportationType;

    public Transport(String description, LocalDate date, double amount,
                     TransportationType transportationType) throws KaChinnnngException {
        super(description, date, amount);
        this.transportationType = transportationType;
    }

    // Getter for transportation type
    public TransportationType getTransportationType() {
        return transportationType;
    }

    @Override
    public String toString() {
        return "Transportation Expense: " + getDescription() +
                " | Date: " + getDateString() + " | Amount: $" + String.format("%.2f", getAmount());
    }
}
