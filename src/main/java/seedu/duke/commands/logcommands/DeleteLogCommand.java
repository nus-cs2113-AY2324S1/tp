package seedu.duke.commands.logcommands;

import java.util.List;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.Duke;
import seedu.duke.data.exception.IncorrectFormatException;

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

    /**
     * Extracts the details of the delete command and deletes the specified exercise.
     *
     * @return CommandResult that tells the user whether an exercise was successfully removed.
     */
    public CommandResult execute() throws IncorrectFormatException {
        if (exerciseDetails.size() < 4) {
            throw new IncorrectFormatException("The delete log command needs to take at least 4 parameters!");
        }
        int month = Integer.parseInt(exerciseDetails.get(0));
        int day = Integer.parseInt(exerciseDetails.get(1));
        String exerciseName = "";
        for (int i = 2; i < exerciseDetails.size() - 1; i++) {
            exerciseName += exerciseDetails.get(i) + " ";
        }
        int caloriesBurned = Integer.parseInt(exerciseDetails.get(exerciseDetails.size() - 1));

        feedbackToUser = Duke.exerciseLog.removeExercise(month, day, exerciseName.trim(), caloriesBurned) ?
                "Successfully removed exercise!" :
                "Could not find the specified exercise!";

        return new CommandResult(feedbackToUser);
    }
}
