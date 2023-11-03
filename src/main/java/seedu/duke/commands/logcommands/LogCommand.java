package seedu.duke.commands.logcommands;

import java.util.List;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.Duke;
import seedu.duke.data.exception.IncorrectFormatException;

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
    public CommandResult execute() throws Exception {
        if (exerciseDetails.size() < 4) {
            throw new IncorrectFormatException("The log command needs to take at least 4 parameters!");
        }
        int month = Integer.parseInt(exerciseDetails.get(0));
        int day = Integer.parseInt(exerciseDetails.get(1));
        String exerciseName = "";
        for (int i = 2; i < exerciseDetails.size() - 1; i++) {
            exerciseName += exerciseDetails.get(i) + " ";
        }
        int caloriesBurned = Integer.parseInt(exerciseDetails.get(exerciseDetails.size() - 1));

        String exerciseDescription = Duke.exerciseLog.addExercise(month, day, exerciseName.strip(), caloriesBurned);

        return new CommandResult((feedbackToUser + exerciseDescription).strip());
    }
}
