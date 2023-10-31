package seedu.financialplanner.reminder;

public class Reminder {
    private String type;
    private String date;
    private boolean isDone = false;

    public Reminder(String type, String date) {
        this.type = type;
        this.date = date;
    }

    public String toString() {
        String status = isDone ? "Done" : "Not Done";
        return "Reminder " + System.lineSeparator() + "   Type: " + type + System.lineSeparator()
                + "   Date: " + date + System.lineSeparator() + "   Status: " + status;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    /*
        * Returns a string that can be saved to a file.
        * Format: type | date | isDone
        * Example: "Reminder: Birthday | 2020-10-10 | false"
     */
    public String formatString() {
        String status = isDone ? "Done" : "Not Done";
        return this.type + " | " + this.date + " | " + this.isDone;
    }
}
