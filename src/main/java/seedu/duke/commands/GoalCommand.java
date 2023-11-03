package seedu.duke.commands;

import seedu.duke.Duke;
import seedu.duke.ui.TextUi;
import seedu.duke.data.exception.IncorrectFormatException;


public class GoalCommand extends Command {

    public static final String COMMAND_WORD = "set";

    public GoalCommand(String cmd){
        super(cmd);
    }

    /**
     * The execution of goalCommand new a goal record into the goal list.
     * If error occurs in creating goal record, possibly includes
     * incorrect format of command or invalid number is input.
     * @return result of adding goal successfully message
     */
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
