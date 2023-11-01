package seedu.financialplanner.goal;

public class Goal {
    private String label;
    private int amount;
    private boolean isDone = false;

    public Goal(String label, int amount) {
        this.label = label;
        this.amount = amount;
    }

    public String toString() {
        String status = isDone ? "Done" : "Not Done";
        return "Goal " + System.lineSeparator()+ "   Label: " + label + System.lineSeparator() + "   Amount: " +
                amount + System.lineSeparator() + "   Status: "+status;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }
    public String formatString() {
        String status = isDone ? "Done" : "Not Done";
        return this.label + " | " + this.amount + " | " + this.isDone;
    }
}
