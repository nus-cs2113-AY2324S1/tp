package seedu.duke.commands.logcommands;

import java.util.List;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.Duke;

public class DeleteLogCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public String feedbackToUser;
    private List<String> exerciseDetails;

    public DeleteLogCommand() {
        super();
    }

    public DeleteLogCommand(List<String> exerciseDetails) {
        super();
        this.exerciseDetails = exerciseDetails;
    }

    public CommandResult execute() {
        int month = Integer.parseInt(exerciseDetails.get(0));
        int day = Integer.parseInt(exerciseDetails.get(1));
        String exerciseName = exerciseDetails.get(2);
        int caloriesBurned = Integer.parseInt(exerciseDetails.get(3));

        feedbackToUser = Duke.exerciseLog.removeExercise(month, day, exerciseName, caloriesBurned) ?
                "Successfully removed exercise!" :
                "Could not find the specified exercise!";

        return new CommandResult(feedbackToUser);
    }
}
