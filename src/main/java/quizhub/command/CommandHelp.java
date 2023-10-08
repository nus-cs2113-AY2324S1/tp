package quizhub.command;

import quizhub.storage.Storage;
import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;

public class CommandHelp extends Command{
    public CommandHelp() {
        super(CommandType.HELP);
    }

    @Override
    public void executeCommand(Ui ui, Storage dataStorage, QuestionList questions) {
        return;
    }
}
