package seedu.financialplanner.reminder;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
public class Reminder {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String type;
    private LocalDate date;
    private boolean isDone = false;

    /**
     * Constructor for a reminder.
     *
     * @param type The description of the reminder.
     * @param date The deadline of the reminder.
     */
    public Reminder(String type, LocalDate date) {
        this.type = type;
        this.date = date;
    }

    /**
     * Constructor for a reminder. Used for loading from a file.
     *
     * @param type The description of the reminder.
     * @param date The deadline of the reminder.
     * @param status The status of the reminder.
     */
    public Reminder(String type, String date, String status) {
        this.type = type;
        this.date = LocalDate.parse(date, FORMATTER);
        if (status.equals("Done")) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    /**
     * Formats the reminder into an easy-to-read format to be output to the user.
     *
     * @return The formatted reminder.
     */
    public String toString() {
        String status = isDone ? "Done" : "Not Done";
        LocalDate currentTime = LocalDate.now();
        Duration duration = Duration.between(currentTime.atStartOfDay(), date.atStartOfDay());
        return "Reminder " + System.lineSeparator() + "   Type: " + type + System.lineSeparator()
                + "   Date: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + System.lineSeparator() + "   Status: " + status
                + System.lineSeparator() + "   Left Days: " + duration.toDays();
    }

    /**
     * Marks the reminder as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Formats the reminder into a standard format to be saved into a text file.
     *
     * @return The formatted reminder.
     */
    public String formatString() {
        String status = isDone ? "Done" : "Not Done";
        return "R" + " | " + this.type + " | " + this.date.format(FORMATTER) + " | " + status;
    }
}
