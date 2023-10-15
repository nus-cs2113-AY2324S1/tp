package seedu.duke.exerciselog;

import java.util.ArrayList;

public class Day {
    private ArrayList<Exercise> exercises;

    public Day() {
        exercises = new ArrayList<>();
    }

    public String addExercise(String exerciseName, int caloriesBurned) {
        Exercise newExercise = new Exercise(exerciseName, caloriesBurned);
        exercises.add(newExercise);
        return newExercise.toString();
    }

    public String removeExercise(String exerciseName, int caloriesBurned) {
        boolean removed = exercises.remove(new Exercise(exerciseName, caloriesBurned));
        return removed ? "Exercise successfully removed" : "Exercise does not exist";
    }

    public ArrayList<Exercise> getExercises() {
        return new ArrayList<>(exercises);
    }

    public Exercise getExercise(int index) {
        return exercises.get(index);
    }

    public Integer getNumberOfExercises() {
        return exercises.size();
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
