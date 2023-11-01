package essenmakanan.recipe;

import java.time.LocalTime;

public class Step {

    private String description;

    private LocalTime time;

    private Tag tag;

    private int estimatedDuration;

    public Step(String description) {
        this.description = description;
    }

    public Step(String description, LocalTime time, Tag tag) {
        this.description = description;
        this.time = time;
        this.tag = tag;
        this.estimatedDuration = 30;
    }

    public Step(String description, String time) {
        // the parameter time has to follow the format "hours:minutes"
        this.description = description;
        this.estimatedDuration = 30;
        this.tag = Tag.ACTUAL_COOKING;
        this.time = LocalTime.of(Integer.parseInt(time.split(":")[0]), Integer.parseInt(time.split(":")[1]));
    }

    public Step(String description, Tag tag) {
        this.description = description;
        this.tag = tag;
        time = LocalTime.now();
        estimatedDuration = 30;
    }

    public Step(String description, String time, Tag tag) {
        // the parameter time has to follow the format "12:20"
        this.description = description;
        this.estimatedDuration = 30;
        this.tag = tag;
        this.time = LocalTime.of(Integer.parseInt(time.split(":")[0]), Integer.parseInt(time.split(":")[1]));
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public int getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(int estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    @Override
    public String toString() {
        if (tag.equals(Tag.NIGHT_BEFORE)) {
            return "You need to " + getDescription() + " at "
                + time.toString() + " the night before for "
                + estimatedDuration + " minutes.";
        } else {
            return "You need to " + getDescription() + " at "
                + time.toString() + " for " + estimatedDuration + " minutes.";
        }
    }
}
