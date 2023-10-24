package cashleh.parser;

import java.time.LocalDate;

public class FindParser {

    private final String description;
    private final double amount;
    private final LocalDate date;

    public FindParser(String description, double amount, LocalDate date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }
    public LocalDate getDate() {
        return date;
    }
}
