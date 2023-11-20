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

    public boolean equals(Object other) {
        if (!(other instanceof Exercise)) {
            return false;
        }
        Exercise otherExercise = (Exercise) other;
        return this.exerciseName.equals(otherExercise.getExerciseName()) &&
                this.caloriesBurned == otherExercise.getCaloriesBurned();
    }
}
