package seedu.duke.data;

import seedu.duke.data.exception.InvalidDateException;


public class Goal {
    private int calories;
    private Date date;

    public Goal(int calories, String date) throws InvalidDateException {
        this.calories = calories;
        this.date = setGoalDateTime(date);
    }

    private Date setGoalDateTime(String date) throws InvalidDateException {
        return new Date(date);
    }

    public String toString() {
        return "Consume " + this.calories + " kcal on " + this.date;
    }
}
