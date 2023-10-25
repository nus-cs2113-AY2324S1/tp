package cashleh.parser;

import cashleh.transaction.Categories;

import java.time.LocalDate;
import java.util.Optional;

public class FindParser {

    private final String description;
    private final Optional amount;
    private final LocalDate date;
    private final Categories category;

    public FindParser(String description, Optional amount, LocalDate date, Categories category) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public Optional getAmount() {
        return amount;
    }
    public LocalDate getDate() {
        return date;
    }
    public Categories getCategory() {
        return category;
    }
}
