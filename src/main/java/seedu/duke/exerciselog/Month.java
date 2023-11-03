package seedu.duke.exerciselog;

import java.util.ArrayList;

public class Month {
    private final ArrayList<Day> dates;
    private String name;

    public Month(int days, String name) {
        this.name = name;
        dates = new ArrayList<Day>();
        for (int i = 0; i < days; i++) {
            dates.add(new Day());
        }
    }

    /**
     * Adds an exercise with the specified name and calories burned to the specified day.
     *
     * @param day the day to add the exercise to
     * @param exerciseName the name of the exercise
     * @param caloriesBurned the number of calories burned by the exercise
     * @return the string representation of the new exercise created
     */
    public String addExercise(int day, String exerciseName, int caloriesBurned) {
        return dates.get(day - 1).addExercise(exerciseName, caloriesBurned);
    }

    /**
     * Attempts to remove the specified exercise at the specified day of the month
     *
     * @param day the day to remove the exercise from
     * @param exerciseName the name of the exercise to be removed
     * @param caloriesBurned the number of calories burned by the exercise
     * @return true if the exercise was removed, false otherwise
     */
    public boolean removeExercise(int day, String exerciseName, int caloriesBurned) {
        return dates.get(day - 1).removeExercise(exerciseName, caloriesBurned);
    }

    /**
     * Returns the total number of exercises for the month
     *
     * @return the total number of exercises for the month
     */
    public int getTotalNumberOfExercises() {
        int totalExercises = 0;
        for (Day d: dates) {
            totalExercises += d.getNumberOfExercises();
        }
        return totalExercises;
    }

    /**
     * Returns a Day object of the day specified
     *
     * @param day the number of the day to be returned
     * @return a Day object of the specified day
     */
    public Day getDay(int day) {
        return dates.get(day - 1);
    }

    /**
     * Returns the total number of exercises for the specified day
     *
     * @param day the day to be checked
     * @return the total number of exercises for the specified day
     */
    public int getNumberOfExercisesForDay(int day) {
        return dates.get(day - 1).getNumberOfExercises();
    }

    /**
     * Given the specific details of an Exercise, finds the exercise in the log on the given day, and updates it to the
     * new specified details.
     *
     * @param day the day of the month of the exercise to be updated
     * @param oldExerciseName original name of the exercise to be updated
     * @param oldCaloriesBurned original number of calories burned by the exercise to be updated
     * @param newExerciseName the new name to update the exercise to
     * @param newCaloriesBurned the new number of calories burned to update the exercise to
     * @return true if an exercise is successfully updated, false otherwise
     */
    public boolean updateExercise(int day, String oldExerciseName, int oldCaloriesBurned, String newExerciseName,
                                  int newCaloriesBurned) {
        return dates.get(day - 1).updateExercise(oldExerciseName, oldCaloriesBurned, newExerciseName,
                newCaloriesBurned);
    }

    /**
     * Returns the name of the month
     *
     * @return the name of the month
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the number of days in the month
     *
     * @return the number of days in the month
     */
    public int getNumberOfDays() {
        return dates.size();
    }

    /**
     * Returns the string representation of the month by showing the name of the month and each of its days and
     * exercises
     *
     * @return the string representation of the month
     */
    public String toString() {
        String newString = "Month: " + name.toUpperCase() + "\n";
        for (int i = 0; i < dates.size(); i++) {
            String day = Integer.toString(i + 1);
            newString += "Day " + day + ":\n";
            newString += dates.get(i).toString();
        }
        return newString;
    }
}
