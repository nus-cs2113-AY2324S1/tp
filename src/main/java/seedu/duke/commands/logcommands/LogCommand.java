package seedu.duke.commands.logcommands;

import java.util.List;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.Duke;

public class LogCommand extends Command {
    public static final String COMMAND_WORD = "log";
    public String feedbackToUser;
    private List<String> exerciseDetails;

    public LogCommand() {
        super();
        feedbackToUser = "Successfully added exercise to log:\n\t";
    }

    public LogCommand(List<String> exerciseDetails) {
        super();
        feedbackToUser = "Successfully added exercise to log:\n\t";
        this.exerciseDetails = exerciseDetails;
    }

    /**
     * Extracts the details of the log command and logs the exercise with its specific details.
     *
     * @return CommandResult telling the user that the exercise was successfully added along with the exercise details.
     */
    public CommandResult execute() {
        int month = Integer.parseInt(exerciseDetails.get(0));
        int day = Integer.parseInt(exerciseDetails.get(1));
        String exerciseName = exerciseDetails.get(2);
        int caloriesBurned = Integer.parseInt(exerciseDetails.get(3));

        String exerciseDescription = Duke.exerciseLog.addExercise(month, day, exerciseName, caloriesBurned);

        return new CommandResult(feedbackToUser + exerciseDescription);
    }
}
