package seedu.duke.commands.logcommands;

import java.io.IOException;
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
     * @throws IncorrectFormatException when the command is not entered with the right type of parameters.
     */
    public CommandResult execute() throws IncorrectFormatException, IOException {
        if (exerciseDetails.size() < 4) {
            throw new IncorrectFormatException("The delete log command needs to take at least 4 parameters!");
        }

        try {
            int month = Integer.parseInt(exerciseDetails.get(0));
            if (month <= 0 || month > 12) {
                throw new IncorrectFormatException("The month you specified does not exist.");
            }

            int day = Integer.parseInt(exerciseDetails.get(1));
            if (day <= 0 || day > Duke.exerciseLog.getNumberOfDays(month)) {
                throw new IncorrectFormatException("The day you specified does not exist for the month.");
            }

            String exerciseName = "";
            for (int i = 2; i < exerciseDetails.size() - 1; i++) {
                exerciseName += exerciseDetails.get(i) + " ";
            }

            int caloriesBurned = Integer.parseInt(exerciseDetails.get(exerciseDetails.size() - 1));
            if (caloriesBurned < 0) {
                throw new IncorrectFormatException("You cannot burn a negative number of calories.");
            }

            feedbackToUser = Duke.exerciseLog.removeExercise(month, day, exerciseName.trim(), caloriesBurned) ?
                    "Successfully removed exercise!" :
                    "Could not find the specified exercise!";
            Duke.storage.removeExerciseFromFile(month, day, exerciseName.trim().split(" "), caloriesBurned);

            return new CommandResult(feedbackToUser);
        } catch (NumberFormatException e) {
            throw new IncorrectFormatException("Please specify reasonable positive numbers in the month, day, and " +
                    "calories burned fields");
        } catch (IOException e) {
            throw new IOException("The ExerciseLog.txt file could not be found.");
        }
    }
}
