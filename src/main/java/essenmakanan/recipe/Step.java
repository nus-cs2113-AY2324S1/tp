package essenmakanan.recipe;

import java.sql.Time;
import java.time.LocalDateTime;

public class Step {

    private String description;
    private LocalDateTime startTime;
    private int estimatedTimeInMinutes;
    private Tag tag;

    private final static int currentYear = LocalDateTime.now().getYear();
    private final static int currentMonth = LocalDateTime.now().getMonthValue();
    private final static int currentDay = LocalDateTime.now().getDayOfMonth();

    public Step(String description, String startTime, int estimatedTimeInMinutes, Tag tag) {
        // startTime has to be in the format "hours:Minutes"
        this.description = description;
        String[] timeDetails = startTime.split(":");
        this.startTime = LocalDateTime.of(currentYear, currentMonth, currentDay,Integer.parseInt(timeDetails[0]), Integer.parseInt(timeDetails[0]));
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
        this.tag = tag;
    }

    public String chDescription() {
        return description;
    }










    public void setDescription(String description) {
        this.description = description;
    }

    public int getEstimatedTimeInMinutes() {
        return estimatedTimeInMinutes;
    }

    public void setEstimatedTimeInMinutes(int estimatedTimeInMinutes) {
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public static void main(String[] args) {
        Time time1 = new Time(0);

        System.out.println(time1);

    }


}
