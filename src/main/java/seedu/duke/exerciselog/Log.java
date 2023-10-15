package seedu.duke.exerciselog;

import java.util.ArrayList;

public class Log {
    private ArrayList<Month> exerciseLog;

    public Log() {
        exerciseLog = new ArrayList<>();
        exerciseLog.add(new Month(31)); // Jan 31
        exerciseLog.add(new Month(29)); // Feb 29
        exerciseLog.add(new Month(31)); // Mar 31
        exerciseLog.add(new Month(30)); // Apr 30
        exerciseLog.add(new Month(31)); // May 31
        exerciseLog.add(new Month(30)); // Jun 30
        exerciseLog.add(new Month(31)); // Jul 31
        exerciseLog.add(new Month(31)); // Aug 31
        exerciseLog.add(new Month(30)); // Sep 30
        exerciseLog.add(new Month(31)); // Oct 31
        exerciseLog.add(new Month(30)); // Nov 30
        exerciseLog.add(new Month(31)); // Dec 31
    }

    public String addExercise(int month, int day, String exerciseName, int caloriesBurned) {
        return exerciseLog.get(month - 1).addExercise(day, exerciseName, caloriesBurned);
    }

    public String removeExercise(int month, int day, String exerciseName, int caloriesBurned) {
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
}