package seedu.duke.exerciselog;

import java.util.ArrayList;

public class Log {
    public static ArrayList<Month> exerciseLog;

    public Log() {
        exerciseLog = new ArrayList<>();
        exerciseLog.add(new Month(31, "jan")); // Jan 31
        exerciseLog.add(new Month(29, "feb")); // Feb 29
        exerciseLog.add(new Month(31, "mar")); // Mar 31
        exerciseLog.add(new Month(30, "apr")); // Apr 30
        exerciseLog.add(new Month(31, "may")); // May 31
        exerciseLog.add(new Month(30, "jun")); // Jun 30
        exerciseLog.add(new Month(31, "jul")); // Jul 31
        exerciseLog.add(new Month(31, "aug")); // Aug 31
        exerciseLog.add(new Month(30, "sep")); // Sep 30
        exerciseLog.add(new Month(31, "oct")); // Oct 31
        exerciseLog.add(new Month(30, "nov")); // Nov 30
        exerciseLog.add(new Month(31, "dec")); // Dec 31
    }

    /**
     * Adds an exercise to the specified month and day with the specified name and number of calories burned.
     *
     * @param month the month of the exercise to be added to the log
     * @param day the day of the month of the exercise to be added to the log
     * @param exerciseName the name of the exercise to be added to the log
     * @param caloriesBurned the number of calories burned by the exercise
     * @return the string representation of the exercise that is added to the log
     */
    public String addExercise(int month, int day, String exerciseName, int caloriesBurned) {
        return exerciseLog.get(month - 1).addExercise(day, exerciseName, caloriesBurned);
    }

    /**
     * Attempts to remove the specified exercise from the log and returns true only if removal is successful.
     *
     * @param month the month of the exercise to be deleted from the log
     * @param day the day of the month of the exercise to be deleted from the log
     * @param exerciseName the name of the exercise to be deleted from the log
     * @param caloriesBurned the number of calories burned by the exercise
     * @return true if the exercise was removed, false otherwise
     */
    public boolean removeExercise(int month, int day, String exerciseName, int caloriesBurned) {
        return exerciseLog.get(month - 1).removeExercise(day, exerciseName, caloriesBurned);
    }

    /**
     * Returns the total number of exercises logged
     *
     * @return the total number of exercises logged
     */
    public int getNumberOfExercises() {
        int totalNumber = 0;
        for (Month m: exerciseLog) {
            totalNumber += m.getTotalNumberOfExercises();
        }
        return totalNumber;
    }

    /**
     * Returns the total number of exercises logged for the specific month
     *
     * @param month the month to be checked
     * @return the total number of exercises logged for the specific month
     */
    public int getNumberOfExercisesForMonth(int month) {
        return exerciseLog.get(month - 1).getTotalNumberOfExercises();
    }

    /**
     * Returns the total number of exercises logged for the specific day of the specified month
     *
     * @param month the month to be checked
     * @param day the day of the month to be checked
     * @return the total number of exercises logged for a specific day of the month
     */
    public int getNumberOfExercisesForDay(int month, int day) {
        return exerciseLog.get(month - 1).getNumberOfExercisesForDay(day);
    }

    /**
     * Returns the Month object of the specified month.
     *
     * @param month the month to be checked
     * @return the Month object of the specified month
     */
    public Month getMonth(int month) {
        return exerciseLog.get(month - 1);
    }

    /**
     * Returns the Day object of the specified day.
     *
     * @param month the month to be checked
     * @param day the day of the month to be checked
     * @return the Day object of the specified month and day
     */
    public Day getDay(int month, int day) {
        return exerciseLog.get(month - 1).getDay(day - 1);
    }

    /**
     * The string representation of the exercise log, showing each month with their days and exercises
     *
     * @return the string representation of the exercise log
     */
    public String toString() {
        String newString = "";
        for (Month m: exerciseLog) {
            newString += m.toString() + "\n";
        }
        return newString;
    }
}
