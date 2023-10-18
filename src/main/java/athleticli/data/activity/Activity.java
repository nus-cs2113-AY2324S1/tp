package athleticli.data.activity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a physical activity consisting of basic sports data.
 */
public class Activity {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("\"MMMM d, " +
            "yyyy 'at' h:mm a\"");
    private static final int columnWidth = 40;

    private String description;
    private final String caption;
    private final int movingTime;

    private final int distance;
    private int calories;
    private final LocalDateTime startDateTime;

    /**
     * Generates a new general sports activity with some basic stats.
     * By default, calories is 0, i.e., not tracked.
     * @param movingTime duration of the activity in minutes
     * @param distance distance covered in meters
     * @param startDateTime start date and time of the activity
     * @param caption a caption of the activity chosen by the user (e.g., "Morning Run")
     */
    public Activity(String caption, int movingTime, int distance, LocalDateTime startDateTime) {
        this.movingTime = movingTime;
        this.distance = distance;
        this.startDateTime = startDateTime;
        this.caption = caption;
    }

    public int getMovingTime() {
        return movingTime;
    }

    public int getDistance() {
        return distance;
    }

    public String getCaption() {
        return caption;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public int getCalories() {
        return this.calories;
    }

    public int getColumnWidth() {
        return columnWidth;
    }

    /**
     * Returns a single line summary of the activity.
     * @return a string representation of the activity
     */
    @Override
    public String toString() {
        String movingTimeOutput = generateMovingTimeStringOutput();
        String distanceOutput = generateDistanceStringOutput();
        String startDateTimeOutput = generateStartDateTimeStringOutput();
        return "[Activity] " + caption + " | " + distanceOutput + " | " + movingTimeOutput + " | " +
                startDateTimeOutput;
    }

    public String generateDistanceStringOutput() {
        double distanceInKm = distance / 1000.0;
        return "Distance: " + String.format("%.2f", distanceInKm).replace(",", ".")
                + " km";
    }

    public String generateMovingTimeStringOutput() {
        int movingTimeHours = movingTime / 60;
        int movingTimeMinutes = movingTime % 60;
        return "Time: " + movingTimeHours + "h " + movingTimeMinutes + "m";
    }

    public String generateStartDateTimeStringOutput() {
        return startDateTime.format(DATE_TIME_FORMATTER).replace("\"", "");
    }

    /**
     * Returns a detailed summary of the activity.
     * @return a multiline string representation of the activity
     */
    public String toDetailedString() {
        String startDateTimeOutput = generateStartDateTimeStringOutput();
        String movingTimeOutput = generateMovingTimeStringOutput();
        String distanceOutput = generateDistanceStringOutput();

        String header = "[Activity - " + this.getCaption() + " - " + startDateTimeOutput + "]";
        String firstRow = formatTwoColumns("\tDistance: " + distanceOutput, "Moving Time: " +
                movingTimeOutput, columnWidth);
        String secondRow = formatTwoColumns("\tCalories: " +
                this.getCalories() + " kcal", "...", columnWidth);

        return String.join(System.lineSeparator(), header, firstRow, secondRow);
    }

    public String formatTwoColumns(String left, String right, int columnWidth) {
        return String.format("%-" + columnWidth + "s%s", left, right);
    }

}
