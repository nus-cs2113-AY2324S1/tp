package essenmakanan.recipe;

import java.sql.Time;

public class Step {

    private String description;

    private Time time;

    private Tag tag;

    public Step(String description, Time time, Tag tag) {
        this.description = description;
        this.time = time;
        this.tag = tag;
    }


}
