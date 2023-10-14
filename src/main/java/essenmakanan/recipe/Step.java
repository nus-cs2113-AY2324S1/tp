package essenmakanan.recipe;

import java.sql.Time;

public class Step {

    private String description;

    private Time time;

    private TAG tag;

    public Step(String description, Time time, TAG tag) {
        this.description = description;
        this.time = time;
        this.tag = tag;
    }


}
