package fittrack;

import fittrack.data.Calories;
import fittrack.data.Date;

public class Workout {
    private String name;
    private Calories calories;
    private Date date;

    public Workout(String name, Calories calories, Date date) {
        assert name != null && calories != null && date != null;

        this.name = name;
        this.calories = calories;
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[W] %s (%s, %s)", name, calories, date);
    }
}
