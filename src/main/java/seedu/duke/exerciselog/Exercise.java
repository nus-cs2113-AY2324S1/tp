package seedu.duke.exerciselog;

public class Exercise {
    private String exerciseName;
    private int caloriesBurned;

    public Exercise(String exerciseName, int caloriesBurned) {
        this.exerciseName = exerciseName;
        this.caloriesBurned = caloriesBurned;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setExerciseName(String newName) {
        this.exerciseName = newName;
    }

    public void setCaloriesBurned(int newCalories) {
        this.caloriesBurned = newCalories;
    }

    public String toString() {
        return "Exercise: " + exerciseName + ", Calories Burned: " + Integer.toString(caloriesBurned) + " Calories\n";
    }

    public boolean equals(Exercise e) {
        return this.exerciseName.equals(e.getExerciseName()) && this.caloriesBurned == e.getCaloriesBurned();
    }
}
