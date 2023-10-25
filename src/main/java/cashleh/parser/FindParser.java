package cashleh.parser;

import cashleh.transaction.Categories;

import java.time.LocalDate;
import java.util.OptionalDouble;

public class FindParser {

    private final String description;
    private final OptionalDouble amount;
    private final LocalDate date;
    private final Categories category;

    public FindParser(String description, OptionalDouble amount, LocalDate date, Categories category) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public OptionalDouble getAmount() {
        return amount;
    }
    public LocalDate getDate() {
        return date;
    }
    public Categories getCategory() {
        return category;
    }
}
