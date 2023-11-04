package seedu.duke.commands.logcommands;

import java.util.List;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.Duke;
import seedu.duke.data.exception.IncorrectFormatException;


enum ViewType {
    TOTAL,
    EXERCISES;
}

enum ViewScope {
    ALL,
    MONTH,
    DAY;
}

public class ViewLogCommand extends Command {
    public static final String COMMAND_WORD = "view";
    private ViewType viewType;
    private ViewScope viewScope;
    private int month;
    private int day;

    public ViewLogCommand() {
        super();
    }

    /**
     * Assigns the view attribute a specific enum based on the scope at which the user wants to view the exercise log.
     *
     * @param viewArgs the details of the scope at which the user wants to view the exercise log.
     * @throws IncorrectFormatException when the command is not entered with the right type of parameters.
     */
    public ViewLogCommand(List<String> viewArgs) throws IncorrectFormatException {
        super();
        if (viewArgs.size() < 2) {
            throw new IncorrectFormatException("Please specify both a ViewType and ViewScope");
        }
        switch (viewArgs.get(0)) {
        case "total":
            viewType = ViewType.TOTAL;
            break;
        case "exercises":
            viewType = ViewType.EXERCISES;
            break;
        default:
            throw new IncorrectFormatException("Incorrect or no ViewType specified.");
        }

        if (viewArgs.get(1).equals("all") || viewArgs.get(1).equals("month")) {
            switch (viewArgs.size()) {
            case 3:
                try {
                    viewScope = ViewScope.MONTH;
                    month = Integer.parseInt(viewArgs.get(2));
                    if (month <= 0 || month > 12) {
                        throw new IncorrectFormatException("The month you specified does not exist.");
                    }
                    break;
                } catch (NumberFormatException e) {
                    throw new IncorrectFormatException("Please specify reasonable positive numbers in the " +
                            "month, day, and calories burned fields");
                }
            case 5:
                try {
                    viewScope = ViewScope.DAY;
                    month = Integer.parseInt(viewArgs.get(2));
                    if (month <= 0 || month > 12) {
                        throw new IncorrectFormatException("The month you specified does not exist.");
                    }
                    day = Integer.parseInt(viewArgs.get(4));
                    if (day <= 0 || day > Duke.exerciseLog.getNumberOfDays(month)) {
                        throw new IncorrectFormatException("The day you specified does not exist for the month.");
                    }
                    break;
                } catch (NumberFormatException e) {
                    throw new IncorrectFormatException("Please specify reasonable positive numbers in the " +
                            "month, day, and calories burned fields");
                }
            default:
                throw new IncorrectFormatException("Incorrect view command format.");
            }
        } else {
            throw new IncorrectFormatException("Please specify a proper ViewScope.");
        }
    }

    /**
     * A different message is returned depending on the scope at which a user wants to view their exercises.
     *
     * @return CommandResult telling the user their total number of exercises in total, for a month, or for a day.
     */
    public CommandResult execute() {
        String result;
        if (viewType == ViewType.TOTAL) {
            switch (viewScope) {
            case ALL:
                result = Integer.toString(Duke.exerciseLog.getNumberOfExercises());
                return new CommandResult("Here are the total number of exercises you have logged: " +
                        result);
            case MONTH:
                result = Integer.toString(Duke.exerciseLog.getNumberOfExercisesForMonth(month));
                return new CommandResult("Here are the total number of exercises for that month: " +
                        result);
            case DAY:
                result = Integer.toString(Duke.exerciseLog.getNumberOfExercisesForDay(month, day));
                return new CommandResult("Here are the total number of exercises for that day: " +
                        result);
            default:
                return new CommandResult("Invalid search type");
            }
        } else {
            switch (viewScope) {
            case ALL:
                result = Duke.exerciseLog.toString().stripTrailing();
                return new CommandResult("Here are all the exercises:\n" + result);
            case MONTH:
                result = Duke.exerciseLog.getMonth(month).toString().stripTrailing();
                return new CommandResult("Here are the exercises for the month:\n" + result);
            case DAY:
                result = Duke.exerciseLog.getDay(month, day).toString().stripTrailing();
                return new CommandResult("Here are the exercises for the day:\n" + result);
            default:
                return new CommandResult("Invalid search type");
            }
        }
    }
}
