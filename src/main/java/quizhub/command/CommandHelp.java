package quizhub.command;

import quizhub.storage.Storage;
import quizhub.quizlist.QuizList;
import quizhub.ui.Ui;

public class CommandHelp extends Command{
    public CommandHelp() {
        super(CommandType.HELP);
    }

    @Override
    public void executeCommand(Ui ui, Storage dataStorage, QuizList tasks) {
        return;
    }
}
