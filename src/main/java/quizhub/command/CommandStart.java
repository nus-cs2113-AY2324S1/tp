package quizhub.command;

import quizhub.storage.Storage;
import quizhub.quizlist.QuizList;
import quizhub.ui.Ui;

public class CommandStart extends Command{
    public CommandStart() {
        super(CommandType.START);
    }

    @Override
    public void executeCommand(Ui ui, Storage dataStorage, QuizList tasks) {
        return;
    }
}
