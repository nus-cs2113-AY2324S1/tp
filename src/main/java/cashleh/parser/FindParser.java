package cashleh.parser;

import cashleh.transaction.Categories;

import java.time.LocalDate;

public class FindParser {

    private final String description;
    private final double amount;
    private final LocalDate date;
    private final Categories category;

    public FindParser(String description, double amount, LocalDate date, Categories category) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
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
    public Categories getCategory() {
        return category;
    }
}
