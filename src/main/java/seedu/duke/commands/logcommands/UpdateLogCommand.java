package seedu.duke.commands.logcommands;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.Duke;
import seedu.duke.data.exception.IncorrectFormatException;

public class UpdateLogCommand extends Command {
    public static final String COMMAND_WORD = "updatelog";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates an exercise from the exercise log.\n"
            + "\tExample: " + COMMAND_WORD + " 1 2 Football 89" + "\n" + "\tPlease specify the new exercise details:\n"
            + "\tSquash 44";
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
     * @throws IncorrectFormatException when the command is not entered with the right type of parameters.
     */
    public CommandResult execute() throws IncorrectFormatException, IOException {
        if (exerciseDetails.size() < 4) {
            throw new IncorrectFormatException("The update log command needs to take at least 4 parameters!");
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

            String oldExerciseName = "";
            for (int i = 2; i < exerciseDetails.size() - 1; i++) {
                oldExerciseName += exerciseDetails.get(i) + " ";
            }

            int oldCaloriesBurned = Integer.parseInt(exerciseDetails.get(exerciseDetails.size() - 1));
            if (oldCaloriesBurned < 0) {
                throw new IncorrectFormatException("You cannot burn a negative number of calories.");
            }

            if (!Duke.exerciseLog.hasExercise(month, day, oldExerciseName.trim(), oldCaloriesBurned)) {
                feedbackToUser = "Could not find exercise to update.";
                return new CommandResult(feedbackToUser);
            }

            Scanner scanner = new Scanner(System.in);
            System.out.println("Please specify the new exercise details:");
            String newExerciseString = scanner.nextLine();
            String[] newExerciseDetails = newExerciseString.split(" ");

            if (newExerciseDetails.length < 2) {
                throw new IncorrectFormatException("The new exercise needs to have a name and calories burned!");
            }

            String newExerciseName = "";
            for (int i = 0; i < newExerciseDetails.length - 1; i++) {
                newExerciseName += newExerciseDetails[i] + " ";
            }

            int newCaloriesBurned = Integer.parseInt(newExerciseDetails[newExerciseDetails.length - 1]);
            if (newCaloriesBurned < 0) {
                throw new IncorrectFormatException("You cannot burn a negative number of calories.");
            }

            feedbackToUser = Duke.exerciseLog.updateExercise(month, day, oldExerciseName.trim(), oldCaloriesBurned,
                    newExerciseName.trim(), newCaloriesBurned) ? "Exercise successfully updated!" :
                    "Could not find exercise to update.";
            Duke.exerciseLogStorage.updateInStorage(month, day,
                    oldExerciseName.trim().split(" "), oldCaloriesBurned,
                    newExerciseName.trim().split(" "), newCaloriesBurned);

            return new CommandResult(feedbackToUser);
        } catch (NumberFormatException e) {
            throw new IncorrectFormatException("Please specify reasonable positive numbers in the month, day, and " +
                    "calories burned fields");
        }
    }
}
