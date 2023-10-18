package seedu.duke.commands;

import seedu.duke.data.Goal;
import seedu.duke.data.GoalList;
import seedu.duke.ui.TextUi;
import seedu.duke.data.exception.IncorrectFormatException;

public class GoalCommand extends Command {

    public GoalCommand(String cmd){
        super(cmd);
    }

    public CommandResult execute(GoalList goals) {
        try{
            goals.addGoal(this.userCommand);
            String addGoalResultMsg = TextUi.addGoalSuccessMessage(goals);
            return new CommandResult(addGoalResultMsg);

        }catch(IncorrectFormatException ife){
            ife.handleException();
        }catch(NumberFormatException nfe){
            System.out.println("Please input a valid number for calories.");
        }

        return null;
    }

}
