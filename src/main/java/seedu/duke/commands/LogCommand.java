package seedu.duke.commands;

import java.util.List;

import seedu.duke.exerciselog.Log;

public class LogCommand extends Command {

    public String feedbackToUser;

    public LogCommand() {
        super();
        feedbackToUser = "Successfully added exercise to log!";
    }

    public CommandResult execute(Log exerciseLog, List<String> exerciseDetails) {
        int month = Integer.parseInt(exerciseDetails.get(0));
        int day = Integer.parseInt(exerciseDetails.get(1));
        String exerciseName = exerciseDetails.get(2);
        int caloriesBurned = Integer.parseInt(exerciseDetails.get(3));

        exerciseLog.addExercise(month, day, exerciseName, caloriesBurned);

        return new CommandResult(feedbackToUser);
    }
}
