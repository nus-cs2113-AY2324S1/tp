package cashleh.parser;

public class FindParser {

    private final String description;
    private final double amount;

    public FindParser(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }
}
