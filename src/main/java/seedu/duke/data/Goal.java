package seedu.duke.data;

import seedu.duke.data.exception.IncorrectFormatException;

public class Goal {
    private int calories;
    private Date date;

    public Goal(int calories, String date) throws IncorrectFormatException {
        this.calories = calories;
        this.date = setGoalDateTime(date);
    }

    private Date setGoalDateTime(String date) throws IncorrectFormatException {
        return new Date(date);
    }

    public String toString() {
        return "Consume " + this.calories + " kcal on " + this.date;
    }
}
