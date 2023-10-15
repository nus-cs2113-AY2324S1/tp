package seedu.duke.exerciselog;

import java.util.ArrayList;

public class Month {
    private ArrayList<Day> dates;

    public Month(int days) {
        dates = new ArrayList<Day>(days);
        for (int i = 0; i < days; i++) {
            dates.add(new Day());
        }
    }

    public String addExercise(int day, String exerciseName, int caloriesBurned) {
        return dates.get(day - 1).addExercise(exerciseName, caloriesBurned);
    }

    public String removeExercise(int day, String exerciseName, int caloriesBurned) {
        return dates.get(day - 1).removeExercise(exerciseName, caloriesBurned);
    }

    public int getTotalNumberOfExercises() {
        int totalExercises = 0;
        for (Day d: dates) {
            totalExercises += d.getNumberOfExercises();
        }
        return totalExercises;
    }

    public int getTotalNumberOfExercisesForDay(int day) {
        return dates.get(day - 1).getNumberOfExercises();
    }

    public ArrayList<Exercise> getExercisesForDay(int day) {
        return dates.get(day - 1).getExercises();
    }

    public ArrayList<ArrayList<Exercise>> getExercisesForMonth() {
        ArrayList<ArrayList<Exercise>> allExercisesForMonth = new ArrayList<>();
        for (int i = 0; i < dates.size(); i++) {
            allExercisesForMonth.add(this.getExercisesForDay(i));
        }
        return allExercisesForMonth;
    }
}
