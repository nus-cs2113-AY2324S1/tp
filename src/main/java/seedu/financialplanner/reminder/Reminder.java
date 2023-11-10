package seedu.financialplanner.reminder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
public class Reminder {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String type;
    private LocalDate date;
    private boolean isDone = false;

    public Reminder(String type, LocalDate date) {
        this.type = type;
        this.date = date;
    }

    public Reminder(String type, String date, String status) {
        this.type = type;
        this.date = LocalDate.parse(date, FORMATTER);
        if (status.equals("Done")) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }
    public String toString() {
        String status = isDone ? "Done" : "Not Done";
        LocalDate currentTime = LocalDate.now();
        Duration duration = Duration.between(currentTime.atStartOfDay(), date.atStartOfDay());
        return "Reminder " + System.lineSeparator() + "   Type: " + type + System.lineSeparator()
                + "   Date: " + date.format(FORMATTER) + System.lineSeparator() + "   Status: " + status
                + System.lineSeparator() + "   Left Days: " + duration.toDays();
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
        return "R" + " | " + this.type + " | " + this.date.format(FORMATTER) + " | " + status;
    }
}
