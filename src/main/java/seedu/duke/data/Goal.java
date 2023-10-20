package seedu.duke.data;

public class Goal {
    private int calories;
    private String date;

    public Goal(int calories, String date){
        this.calories = calories;
        this.date = date;
    }

    public String toString() {
        return "Consume " + this.calories + " kcal on " + this.date;
    }
}
