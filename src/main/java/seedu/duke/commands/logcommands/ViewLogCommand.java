package seedu.duke.commands.logcommands;

import java.util.List;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.Duke;
import seedu.duke.data.exception.IncorrectFormatException;


enum ViewType {
    TOTALEXERCISES,
    TOTALEXERCISESMONTH,
    TOTALEXERCISESDAY;
}

public class ViewLogCommand extends Command {
    public static final String COMMAND_WORD = "view";
    private ViewType view;
    private int month;
    private int day;

    public ViewLogCommand() {
        super();
    }

    /**
     * Assigns the view attribute a specific enum based on the scope at which the user wants to view the exercise log.
     *
     * @param viewArgs the details of the scope at which the user wants to view the exercise log.
     */
    public ViewLogCommand(List<String> viewArgs) throws IncorrectFormatException {
        super();
        switch (viewArgs.get(0)) {
        case "all":
            view = ViewType.TOTALEXERCISES;
            break;
        case "month":
            if (viewArgs.size() < 2) {
                throw new IncorrectFormatException("Please specify a month.");
            }
            view = ViewType.TOTALEXERCISESMONTH;
            month = Integer.parseInt(viewArgs.get(1));
            if (month <= 0 || month > 12) {
                throw new IncorrectFormatException("The month you specified does not exist.");
            }
            break;
        case "day":
            if (viewArgs.size() < 3) {
                throw new IncorrectFormatException("Please specify a month and a day.");
            }
            month = Integer.parseInt(viewArgs.get(1));
            if (month <= 0 || month > 12) {
                throw new IncorrectFormatException("The month you specified does not exist.");
            }
            view = ViewType.TOTALEXERCISESDAY;
            day = Integer.parseInt(viewArgs.get(2));
            if (day <= 0 || day > Duke.exerciseLog.getNumberOfDays(month)) {
                throw new IncorrectFormatException("The day you specified does not exist for the month.");
            }
            break;
        default:
            throw new IncorrectFormatException("Incorrect or no VewType specified.");
        }
    }

    /**
     * A different message is returned depending on the scope at which a user wants to view their exercises.
     *
     * @return CommandResult telling the user their total number of exercises in total, for a month, or for a day.
     */
    public CommandResult execute() {
        String numberOfExercises;
        switch (view) {
        case TOTALEXERCISES:
            numberOfExercises = Integer.toString(Duke.exerciseLog.getNumberOfExercises());
            return new CommandResult("Here are the total number of exercises you have logged: " +
                    numberOfExercises);
        case TOTALEXERCISESMONTH:
            numberOfExercises = Integer.toString(Duke.exerciseLog.getNumberOfExercisesForMonth(month));
            return new CommandResult("Here are the total number of exercises for that month: " +
                    numberOfExercises);
        case TOTALEXERCISESDAY:
            numberOfExercises = Integer.toString(Duke.exerciseLog.getNumberOfExercisesForDay(month, day));
            return new CommandResult("Here are the total number of exercises for that day: " +
                    numberOfExercises);
        default:
            return new CommandResult("Invalid exercise search type");
        }
    }
}
