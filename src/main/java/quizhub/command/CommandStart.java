package quizhub.command;

import quizhub.storage.Storage;
import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;

public class CommandStart extends Command{
    public CommandStart() {
        super(CommandType.START);
    }

    @Override
    public void executeCommand(Ui ui, Storage dataStorage, QuestionList questions) {
        return;
    }
}
