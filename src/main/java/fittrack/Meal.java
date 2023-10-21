package fittrack;

import fittrack.data.Calories;
import fittrack.data.Date;

public class Meal {
    private String name;
    private Calories calories;
    private Date date;

    public Meal(String name, Calories calories, Date date) {
        this.name = name;
        this.calories = calories;
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[M] %s (%s, %s)", name, calories, date);
    }

}
