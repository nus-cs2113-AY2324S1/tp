package quizhub.command;

import quizhub.storage.Storage;
import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;

public class CommandEdit extends Command {
    public CommandEdit() {
        super(CommandType.EDIT);
    }

    @Override
    public void executeCommand(Ui ui, Storage dataStorage, QuestionList questions) {
        return;
    }
}