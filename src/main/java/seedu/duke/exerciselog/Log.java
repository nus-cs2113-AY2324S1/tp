package seedu.duke.exerciselog;

import java.util.ArrayList;

public class Log {
    private final ArrayList<Month> exerciseLog;

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

    public String addExercise(int month, int day, String exerciseName, int caloriesBurned) {
        return exerciseLog.get(month - 1).addExercise(day, exerciseName, caloriesBurned);
    }

    public boolean removeExercise(int month, int day, String exerciseName, int caloriesBurned) {
        return exerciseLog.get(month - 1).removeExercise(day, exerciseName, caloriesBurned);
    }

    public int getTotalNumberOfExercises() {
        int totalNumber = 0;
        for (Month m: exerciseLog) {
            totalNumber += m.getTotalNumberOfExercises();
        }
        return totalNumber;
    }

    public int getTotalNumberOfExercisesForMonth(int month) {
        return exerciseLog.get(month - 1).getTotalNumberOfExercises();
    }

    public int getTotalNumberOfExercisesForDay(int month, int day) {
        return exerciseLog.get(month - 1).getTotalNumberOfExercisesForDay(day);
    }

    public ArrayList<Exercise> getExercisesForDay(int month, int day) {
        return exerciseLog.get(month - 1).getExercisesForDay(day);
    }

    public ArrayList<ArrayList<Exercise>> getExercisesForMonth(int month) {
        return exerciseLog.get(month - 1).getExercisesForMonth();
    }

    public Month getMonth(int month) {
        return exerciseLog.get(month - 1);
    }

    public Day getDay(int month, int day) {
        return exerciseLog.get(month - 1).getDay(day - 1);
    }

    public String toString() {
        String newString = "";
        for (Month m: exerciseLog) {
            newString += m.toString() + "\n";
        }
        return newString;
    }
}
