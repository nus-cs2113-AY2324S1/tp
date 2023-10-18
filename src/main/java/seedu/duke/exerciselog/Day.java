package seedu.duke.exerciselog;

import java.util.ArrayList;

public class Day {
    private final ArrayList<Exercise> exercises;

    public Day() {
        exercises = new ArrayList<>();
    }

    public String addExercise(String exerciseName, int caloriesBurned) {
        Exercise newExercise = new Exercise(exerciseName, caloriesBurned);
        exercises.add(newExercise);
        return newExercise.toString();
    }

    public boolean removeExercise(String exerciseName, int caloriesBurned) {
        return exercises.remove(new Exercise(exerciseName, caloriesBurned));
    }

    public Exercise getExercise(int index) {
        return exercises.get(index);
    }

    public Integer getNumberOfExercises() {
        return exercises.size();
    }

    public boolean containsExercises() {
        return !exercises.isEmpty();
    }

    public String toString() {
        if (exercises.isEmpty()) {
            return "\tNO EXCERCISES FOR THIS DAY!\n";
        }
        String newString = "";
        for (Exercise e: exercises) {
            newString += "\t" + e.toString();
        }
        return newString;
    }
}
