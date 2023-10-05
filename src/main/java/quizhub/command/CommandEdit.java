package quizhub.command;

import quizhub.storage.Storage;
import quizhub.quizlist.QuizList;
import quizhub.ui.Ui;

public class CommandEdit extends Command {
    public CommandEdit() {
        super(CommandType.EDIT);
    }

    @Override
    public void executeCommand(Ui ui, Storage dataStorage, QuizList tasks) {
        return;
    }
}
