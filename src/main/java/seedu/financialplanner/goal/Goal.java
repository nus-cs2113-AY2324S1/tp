package seedu.financialplanner.goal;

public class Goal {
    private final String label;
    private final int amount;
    private boolean isDone = false;

    /**
     * Constructor for a goal.
     *
     * @param label The description of the goal.
     * @param amount The amount of the goal.
     */
    public Goal(String label, int amount) {
        this.label = label;
        this.amount = amount;
    }

    /**
     * Constructor for a goal. Used for loading from a file.
     *
     * @param label The description of the goal.
     * @param amount The amount of the goal.
     * @param status The status of the goal.
     */
    public Goal(String label, int amount, String status) {
        this.label = label;
        this.amount = amount;
        if (status.equals("Achieved")) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    /**
     * Formats the goal into an easy-to-read format to be output to the user.
     *
     * @return The formatted goal.
     */
    public String toString() {
        String status = isDone ? "Achieved" : "Not Achieved";
        return "Goal " + System.lineSeparator()+ "   Label: " + label + System.lineSeparator() + "   Amount: " +
                amount + System.lineSeparator() + "   Status: "+status;
    }

    /**
     * Marks the goal as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public String getLabel() {
        return this.label;
    }
    public int getAmount() {
        return this.amount;
    }

    /**
     * Formats the goal into an easy-to-read format to be output to the user.
     *
     * @return The formatted goal.
     */
    public String formatString() {
        String status = isDone ? "Achieved" : "Not Achieved";
        return "G" + " | " + this.label + " | " + this.amount + " | " + status;
    }
}
