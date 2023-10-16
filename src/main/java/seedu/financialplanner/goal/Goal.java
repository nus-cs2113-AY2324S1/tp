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
        return "Goal: "+this.label + " | " + this.amount + " | " + status;
    }
    public void markAsDone() {
        //TODO edit the expense to mark the goal as done
        this.isDone = true;
    }
    //TODO delete the Reminde
    public String formatString() {
        String status = isDone ? "Done" : "Not Done";
        return this.label + " | " + this.amount + " | " + this.isDone;
    }
}
