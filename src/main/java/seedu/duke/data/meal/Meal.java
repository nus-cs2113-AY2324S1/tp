package seedu.duke.data.meal;

import seedu.duke.data.Date;

public class Meal {
    public String name;
    public int calories;
    public Date time;
    public Category category;

    public Meal(String name, int calories, String category, Date time) throws Exception {
        this.name = name;
        this.calories = calories;
        this.time = time;
        this.category = CategoryParser.Parse(category);
    }

    @Override
    public String toString() {
        return name + "(" + calories + " calories, " + category + ", on " + time.toString() + ")";
    }
}
