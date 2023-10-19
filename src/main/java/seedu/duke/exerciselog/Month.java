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

    public String addExercise(int day, String exerciseName, int caloriesBurned) {
        return dates.get(day - 1).addExercise(exerciseName, caloriesBurned);
    }

    public boolean removeExercise(int day, String exerciseName, int caloriesBurned) {
        return dates.get(day - 1).removeExercise(exerciseName, caloriesBurned);
    }

    public int getTotalNumberOfExercises() {
        int totalExercises = 0;
        for (Day d: dates) {
            totalExercises += d.getNumberOfExercises();
        }
        return totalExercises;
    }

    public Day getDay(int day) {
        return dates.get(day - 1);
    }

    public int getTotalNumberOfExercisesForDay(int day) {
        return dates.get(day - 1).getNumberOfExercises();
    }

    public String getName() {
        return name;
    }

    public int getNumberOfDays() {
        return dates.size();
    }

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
