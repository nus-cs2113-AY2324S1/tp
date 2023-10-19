package seedu.duke.commands;

import seedu.duke.Duke;
import seedu.duke.ui.TextUi;
import seedu.duke.data.exception.IncorrectFormatException;


public class GoalCommand extends Command {

    public static final String COMMAND_WORD = "set";

    public GoalCommand(String cmd){
        super(cmd);
    }

    public CommandResult execute() {
        try{
            Duke.goals.addGoal(this.userCommand);
            String addGoalResultMsg = TextUi.addGoalSuccessMessage(Duke.goals);
            return new CommandResult(addGoalResultMsg);

        }catch(IncorrectFormatException ife){
            ife.handleException();
        }catch(NumberFormatException nfe){
            System.out.println("Please input a valid number for calories.");
        }

        return null;
    }

}
