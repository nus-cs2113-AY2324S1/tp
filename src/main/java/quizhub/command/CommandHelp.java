package quizhub.command;

import quizhub.storage.Storage;
import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;

public class CommandHelp extends Command{
    public static final String COMMAND_WORD = "help";
    public CommandHelp() {
        super(CommandType.HELP);
    }

    /**
     * Displays all commands available to the user.
     * 
     * @param ui User interface for interactions with user through CLI.
     * @param questions Current question list in the program.
     * @param dataStorage Hard disk storage for storing question data.
     */
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, QuestionList questions) {
        assert questions != null && dataStorage != null && ui != null : "Invalid null parameter";
        ui.displayMessage(Ui.INVALID_COMMAND_FEEDBACK);
    }
}
