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
     * Given the specific details of an Exercise, finds the exercise in the log and updates it to the new specified
     * details.
     *
     * @param oldExerciseName original name of the exercise to be updated
     * @param oldCaloriesBurned original number of calories burned by the exercise to be updated
     * @param newExerciseName the new name to update the exercise to
     * @param newCaloriesBurned the new number of calories burned to update the exercise to
     * @return true when an exercise is found and updated, false otherwise
     */
    public boolean updateExercise(String oldExerciseName, int oldCaloriesBurned, String newExerciseName,
                                  int newCaloriesBurned) {
        int index = exercises.indexOf(new Exercise(oldExerciseName, oldCaloriesBurned));
        if (index == -1) {
            return false;
        }

        Exercise targetExercise = exercises.get(index);
        targetExercise.setExerciseName(newExerciseName);
        targetExercise.setCaloriesBurned(newCaloriesBurned);
        return true;
    }

    public boolean hasExercise(String exerciseName, int caloriesBurned) {
        return exercises.contains(new Exercise(exerciseName, caloriesBurned));
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
