package quizhub.command;

import quizhub.storage.Storage;
import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;

/**
 * Command to Start the Quiz
 */
public class CommandStart extends Command{
    public CommandStart() {
        super(CommandType.START);
    }

    /**
     * Loop through the array list of questions & allow the user to answer them.
     * If the input given matches EXACTLY (v1.0), then the answer is correct
     * returns "Correct" or "Wrong"
     */
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, QuestionList questions) {
        questions.startQuiz(ui);
    }
}
