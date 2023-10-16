package seedu.duke.commands.logcommands;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.exerciselog.Log;


enum ViewType {
    TOTALEXERCISES,
    TOTALEXERCISESMONTH,
    TOTALEXERCISESDAY;
}

public class ViewLogCommand extends Command {

    private ViewType view;
    private int month;
    private int day;

    public ViewLogCommand() {
        super();
    }

    public ViewLogCommand(String viewType) {
        super();
        switch (viewType) {
        case "view all":
            view = ViewType.TOTALEXERCISES;
            break;
        case "view month":
            view = ViewType.TOTALEXERCISESMONTH;
            // set month to the month to view
            break;
        case "view day":
            view = ViewType.TOTALEXERCISESDAY;
            // set month and day to the day to view
            break;
        default:
            System.out.println("No ViewType specified");
        }
    }

    public CommandResult execute(Log exerciseLog) {
        switch (view) {
        case TOTALEXERCISES:
            exerciseLog.getTotalNumberOfExercises();
            return new CommandResult("Here are the total number of exercises you have logged!\n");
        case TOTALEXERCISESMONTH:
            exerciseLog.getTotalNumberOfExercisesForMonth(month);
            return new CommandResult("Here are the total number of exercises for that month!\n");
        case TOTALEXERCISESDAY:
            exerciseLog.getTotalNumberOfExercisesForDay(month, day);
            return new CommandResult("Here are the total number of exercises for that day!\n");
        default:
            return new CommandResult("Invalid exercise search type");
        }
    }
}
