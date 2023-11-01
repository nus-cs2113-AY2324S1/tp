package seedu.financialplanner.goal;

public class Goal {
    private final String label;
    private final int amount;
    private boolean isDone = false;

    public Goal(String label, int amount) {
        this.label = label;
        this.amount = amount;
    }

    public String toString() {
        String status = isDone ? "Achieved" : "Not Achieved";
        return "Goal " + System.lineSeparator()+ "   Label: " + label + System.lineSeparator() + "   Amount: " +
                amount + System.lineSeparator() + "   Status: "+status;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getLabel() {
        return this.label;
    }
    public int getAmount() {
        return this.amount;
    }
    public String formatString() {
        String status = isDone ? "Done" : "Not Done";
        return this.label + " | " + this.amount + " | " + this.isDone;
    }
}
