package essenmakanan.recipe;

import java.sql.Time;

public class Step {

    private String description;

    private Time time;

    private Tag tag;

    public Step(String description) {
        this.description = description;
    }

    public Step(String description, Time time, Tag tag) {
        this.description = description;
        this.time = time;
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
