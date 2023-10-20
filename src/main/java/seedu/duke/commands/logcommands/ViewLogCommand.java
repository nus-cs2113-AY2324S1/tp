package seedu.duke.commands.logcommands;

import java.util.List;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.Duke;


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

    public ViewLogCommand(List<String> viewArgs) {
        super();
        switch (viewArgs.get(0)) {
        case "all":
            view = ViewType.TOTALEXERCISES;
            break;
        case "month":
            view = ViewType.TOTALEXERCISESMONTH;
            month = Integer.parseInt(viewArgs.get(1));
            break;
        case "day":
            view = ViewType.TOTALEXERCISESDAY;
            month = Integer.parseInt(viewArgs.get(1));
            day = Integer.parseInt(viewArgs.get(2));
            break;
        default:
            System.out.println("No ViewType specified");
        }
    }

    public CommandResult execute() {
        switch (view) {
        case TOTALEXERCISES:
            Duke.exerciseLog.getNumberOfExercises();
            return new CommandResult("Here are the total number of exercises you have logged!\n");
        case TOTALEXERCISESMONTH:
            Duke.exerciseLog.getNumberOfExercisesForMonth(month);
            return new CommandResult("Here are the total number of exercises for that month!\n");
        case TOTALEXERCISESDAY:
            Duke.exerciseLog.getNumberOfExercisesForDay(month, day);
            return new CommandResult("Here are the total number of exercises for that day!\n");
        default:
            return new CommandResult("Invalid exercise search type");
        }
    }
}
