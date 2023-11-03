package seedu.financialplanner.goal;

public class Goal {
    private final String label;
    private final int amount;
    private boolean isDone = false;

    public Goal(String label, int amount) {
        this.label = label;
        this.amount = amount;
    }

    public Goal(String label, int amount, String status) {
        this.label = label;
        this.amount = amount;
        if (status.equals("Achieved")) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
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
        String status = isDone ? "Achieved" : "Not Achieved";
        return "G" + " | " + this.label + " | " + this.amount + " | " + status;
    }
}
