package seedu.duke.exerciselog;

import java.util.ArrayList;

public class Day {
    private final ArrayList<Exercise> exercises;

    public Day() {
        exercises = new ArrayList<>();
    }

    /**
     * Instantiates and adds a new Exercise Object into the Day.
     *
     * @param exerciseName the name of the exercise to be added to the day
     * @param caloriesBurned the number of calories burned during the exercise
     * @return a string representing the new exercise
     */
    public String addExercise(String exerciseName, int caloriesBurned) {
        Exercise newExercise = new Exercise(exerciseName, caloriesBurned);
        exercises.add(newExercise);
        return newExercise.toString();
    }

    /**
     * Attempts to remove an exercise with the specified details and returns whether or not removal was successful.
     *
     * @param exerciseName the name of the exercise to be removed
     * @param caloriesBurned the number of calories burned during the exercise
     * @return true if an exercise was successfully removed, false otherwise
     */
    public boolean removeExercise(String exerciseName, int caloriesBurned) {
        return exercises.remove(new Exercise(exerciseName, caloriesBurned));
    }

    /**
     * Returns the exercise object at the specified index for the Day.
     *
     * @param index the number of the exercise the user wants to check on
     * @return the exercise object at the specified index
     */
    public Exercise getExercise(int index) {
        return exercises.get(index - 1);
    }

    /**
     * Returns the total number of exercises logged for the day.
     *
     * @return the total number of exercises logged for the day.
     */
    public Integer getNumberOfExercises() {
        return exercises.size();
    }

    /**
     * Returns whether the Day has exercises or not
     *
     * @return true if there are exercises, false if not
     */
    public boolean containsExercises() {
        return !exercises.isEmpty();
    }

    /**
     * Returns a string representing all the exercises contained for the Day.
     *
     * @return a string representing all the exercises for the day
     */
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
