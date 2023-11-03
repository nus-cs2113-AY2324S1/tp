package seedu.duke.commands.logcommands;

import java.util.List;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.Duke;

public class UpdateLogCommand extends Command {
    public static final String COMMAND_WORD = "update";
    public String feedbackToUser;
    private List<String> exerciseDetails;

    public UpdateLogCommand() {
        super();
    }

    public UpdateLogCommand(List<String> exerciseDetails) {
        super();
        this.exerciseDetails = exerciseDetails;
    }

    /**
     * Extracts the details of the update command and updates the specified exercise.
     *
     * @return CommandResult that tells the user whether an exercise was successfully updated.
     */
    public CommandResult execute() {
        int month = Integer.parseInt(exerciseDetails.get(0));
        int day = Integer.parseInt(exerciseDetails.get(1));
        String oldExerciseName = exerciseDetails.get(2);
        int oldCaloriesBurned = Integer.parseInt(exerciseDetails.get(3));
        String newExerciseName = exerciseDetails.get(4);
        int newCaloriesBurned = Integer.parseInt(exerciseDetails.get(5));

        feedbackToUser = Duke.exerciseLog.updateExercise(month, day, oldExerciseName, oldCaloriesBurned,
                newExerciseName, newCaloriesBurned) ? "Exercise successfully updated!" :
                    "Could not find exercise to update.";

        return new CommandResult(feedbackToUser);
    }
}
