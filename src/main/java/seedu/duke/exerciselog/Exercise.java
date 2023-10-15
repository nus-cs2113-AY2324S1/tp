package seedu.duke.exerciselog;

public class Exercise {
    private String name;
    private int caloriesBurned;

    public Exercise(String name, int caloriesBurned) {
        this.name = name;
        this.caloriesBurned = caloriesBurned;
    }

    public String getExerciseName() {
        return name;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setExerciseName(String newName) {
        this.name = newName;
    }

    public void setCaloriesBurned(int newCalories) {
        this.caloriesBurned = newCalories;
    }

    public String toString() {
        return "Exercise: " + name + ", Calories Burned: " + Integer.toString(caloriesBurned) + " Calories";
    }

    public boolean equals(Exercise e) {
        return this.name.equals(e.getExerciseName()) && this.caloriesBurned == e.getCaloriesBurned();
    }
}
