package seedu.duke.data.meal;

import seedu.duke.data.Date;

public class Meal {
    public String name;
    public int calories;
    public Date time;

    public Meal(String name, int calories, Date time) throws Exception {
        this.name = name;
        this.calories = calories;
        this.time = time;
    }

    @Override
    public String toString() {
        return name + "(" + calories + " calories, " + time.toString() + ")";
    }
}
