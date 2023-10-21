package quizhub.command;

import quizhub.questionlist.QuestionList;
import quizhub.storage.Storage;
import quizhub.ui.Ui;

public class CommandInvalid extends Command {

    public String feedback;

    public CommandInvalid(String feedback) {
        super(CommandType.INVALID);
        this.feedback = feedback;
    }

    @Override
    public void executeCommand(Ui ui, Storage dataStorage, QuestionList questions) {
        ui.showInvalidCommandHelp(feedback);
    }
}
