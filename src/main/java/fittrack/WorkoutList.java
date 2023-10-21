package fittrack;

import fittrack.data.Workout;

import java.util.ArrayList;

public class WorkoutList {

    private ArrayList<Workout> workoutList;

    public WorkoutList() {
        workoutList = new ArrayList<>();
    }

    public void addToList(Workout newWorkout) {
        workoutList.add(newWorkout);
    }

    public void deleteWorkout(int workoutIndex) {
        workoutList.remove((workoutIndex - 1));
    }

    @Override
    public String toString() {
        int counter = 1;
        StringBuilder output = new StringBuilder();
        for (Workout workout : workoutList) {
            output.append(counter).append(".").append(workout.toString()).append("\n");
            counter += 1;
        }
        return output.toString();
    }

    public Workout getWorkout(int workoutIndex) {
        return workoutList.get(workoutIndex - 1);
    }

}
