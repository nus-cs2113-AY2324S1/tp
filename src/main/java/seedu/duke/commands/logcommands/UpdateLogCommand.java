package seedu.duke.commands.logcommands;

import java.util.List;
import java.util.Scanner;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.Duke;
import seedu.duke.data.exception.IncorrectFormatException;

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
    public CommandResult execute() throws IncorrectFormatException {
        if (exerciseDetails.size() < 4) {
            throw new IncorrectFormatException("The update log command needs to take at least 4 parameters!");
        }
        int month = Integer.parseInt(exerciseDetails.get(0));
        int day = Integer.parseInt(exerciseDetails.get(1));
        String oldExerciseName = "";
        for (int i = 2; i < exerciseDetails.size() - 1; i++) {
            oldExerciseName += exerciseDetails.get(i) + " ";
        }
        int oldCaloriesBurned = Integer.parseInt(exerciseDetails.get(exerciseDetails.size() - 1));

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

        feedbackToUser = Duke.exerciseLog.updateExercise(month, day, oldExerciseName.trim(), oldCaloriesBurned,
                newExerciseName.trim(), newCaloriesBurned) ? "Exercise successfully updated!" :
                    "Could not find exercise to update.";

        return new CommandResult(feedbackToUser);
    }
}
